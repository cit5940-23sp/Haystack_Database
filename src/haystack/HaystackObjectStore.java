package haystack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import photo.IPhoto;

public class HaystackObjectStore implements IHaystackObjectStore {

    private File file;
    private long EOF;
    private long curPointer;
    private int curBytes;

    public HaystackObjectStore(String filePath) {
        this.file = new File(filePath);
        this.EOF = 0;
    }

    public File getFile() {
        return this.file;
    }

    @Override
    public long appendPhoto(Photo newPhoto) {
        // TODO Auto-generated method stub
        try {
            // append write to the file
            FileOutputStream out = new FileOutputStream(this.file, true);

            if (this.file.length() != 0) {
                this.EOF = this.file.length();
            }

            // convert new photo to bytes and write to file
            int success = convertPhotoToBytes(newPhoto, out);

            out.flush();
            out.close();

            if (success == -1) {
                return -1;
            }

            return this.EOF;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

//    public long appendPhoto(byte[] incomingPhoto) {
//        //append write to the file
//        try {
//            FileOutputStream out = new FileOutputStream(this.file, true);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//      
//        
//    }

    /**
     * The function that convert Photo object's content to byte[] and write to out
     * file
     * 
     * @param p   photo object
     * @param out out file
     * @throws IOException
     */
    public int convertPhotoToBytes(Photo p, FileOutputStream out) throws IOException {
        // check if adding this file will max out the database
        int afterWriteSize = IPhoto.META_DATA_LENGTH + p.getSize() + this.curBytes;
        System.out.println("======afterWRITE" + afterWriteSize);
        if (afterWriteSize > IHaystackObjectStore.MAXIMUM_BYTES) {
            System.out.println("Cant add photo, this haystack don't have enough space!");
            return -1;
        }
        // convert MAGIC NUMBER to byte & write
        BigInteger magic = BigInteger.valueOf(IPhoto.HEADER_MAGIC_NUMBER);
        byte[] magicB = magic.toByteArray();
        out.write(magicB);
//        System.out.println("magic number size:" + magicB.length);

        // get photo key & convert to byte & write
        int key = p.getKey();
        BigInteger keyInt = BigInteger.valueOf(key);
        byte[] keyB = keyInt.toByteArray();
        out.write(keyB);

        // get photo alternate key & convert to byte & write
        int alternateKey = p.getAlternateKey();
        BigInteger alternateKeyInt = BigInteger.valueOf(alternateKey);
        byte[] alternateKeyB = alternateKeyInt.toByteArray();
        out.write(alternateKeyB);

        // parse flags, convert to binary and write
        Map<Integer, Integer> flags = p.getFlags();
        for (Map.Entry<Integer, Integer> e : flags.entrySet()) {
            // flag keys
            BigInteger flagKey = BigInteger.valueOf(e.getKey());
            byte[] flagKeyB = flagKey.toByteArray();
            out.write(flagKeyB);

            BigInteger flagVal = BigInteger.valueOf(e.getValue());
            byte[] flagValB = flagVal.toByteArray();
            out.write(flagValB);
        }

        // get size & convert to byte & write
        // size is 32 bit/ 4 bytes long
        int size = p.getSize();
        System.out.println("wirte size: " + size);
        BigInteger sizeInt = BigInteger.valueOf(size);
        byte[] sizeB = sizeInt.toByteArray();

        // add padding to make it 4bytes
        byte[] paddedBytes = new byte[4];
        System.arraycopy(sizeB, 0, paddedBytes, 4 - sizeB.length, sizeB.length);
        out.write(paddedBytes);

        // write data bytes[] to out
        out.write(p.getData());

        return 0;

    }

    /**
     * Parse incoming data to read key and alternate Key Convert byte[] to a new
     * photo (delete the meta)
     * 
     * @param incomingData
     * @return Photo object containing byte[] data, previous key and previous
     *         alternate key
     */
    public Photo convertBytesToPhoto(byte[] incomingData) {
        // add padding to make it 4bytes
        byte[] paddedKey = new byte[4];
        Arrays.fill(paddedKey, (byte) 0);
        System.arraycopy(incomingData, 4, paddedKey, 2, 1);
        int key = ByteBuffer.wrap(paddedKey).getInt();

        // add padding to make it 4bytes
        byte[] paddedAlterKey = new byte[4];
        Arrays.fill(paddedAlterKey, (byte) 0);
        System.arraycopy(incomingData, 5, paddedAlterKey, 2, 1);
        int alterKey = ByteBuffer.wrap(paddedAlterKey).getInt();

        byte[] photoData = new byte[incomingData.length - IPhoto.META_DATA_LENGTH];
        for (int i = 0; i < photoData.length; i++) {
            photoData[i] = incomingData[i + IPhoto.META_DATA_LENGTH];
        }
        Photo res = new Photo(photoData, key, alterKey);
        return res;
    }

    @Override
    public byte[] getPhoto(long offset, int size) {
        try {
            RandomAccessFile rand = new RandomAccessFile(this.file, "r");

            rand.seek(offset);
            System.out.print(offset);
            // read things
            // read magic number for 4 bytes
            byte[] magic = new byte[4];
            rand.read(magic);
            int magicNumber = ByteBuffer.wrap(magic).getInt();
            System.out.println(magicNumber);
            // check if the header is the same magic number
            // if it is not the same format, retrieve fail -1
            if (magicNumber != IPhoto.HEADER_MAGIC_NUMBER) {
                rand.close();
                System.out.println("Not the same Magic number");
                return null;
            }

            // read key for 1 bytes
            byte[] key = new byte[1];
            rand.read(key);

            // read alternate key for 1 bytes
            byte[] alternateKey = new byte[1];
            rand.read(alternateKey);

            // read flags <key 1 byte> <value 1 byte>
            byte[] flag0 = new byte[2];
            rand.read(flag0);

            byte[] flag1 = new byte[2];
            rand.read(flag1);

            byte[] flag2 = new byte[2];
            rand.read(flag2);
            
            // if flag DELETED's value is 1, meaning photo is deleted
            // can't access deleted photo, return -1
            if (flag1[1] == 0x01) {
                rand.close();
                return null;
            }

            this.readSize(rand);

            // read photo data
            // return byte[] of the photo to caller
            byte[] data = new byte[size];
            int i = 0;
            while (size > 0) {
                byte read = rand.readByte();
                data[i] = read;
                i++;
                size--;
            }

            rand.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deletePhoto(long offset) {
        try {
            RandomAccessFile rand = new RandomAccessFile(this.file, "rw");

            rand.seek(offset);

            // read things
            this.checkMagicNumber(rand);

            this.checkKey(rand);

            // read flags <key 1 byte> <value 1 byte>
            byte[] flag0 = new byte[2];
            rand.read(flag0);

            byte[] flag1Key = new byte[1];
            rand.read(flag1Key);

            // change the flag by overwriting the value of the flag1
            byte delete = 0x01;
            rand.write(delete);

            rand.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;

    }

    @Override
    public int updatePhoto(long offset) {
        try {
            RandomAccessFile rand = new RandomAccessFile(this.file, "rw");

            rand.seek(offset);

            // read things
            this.checkMagicNumber(rand);

            this.checkKey(rand);

            // read flags <key 1 byte> <value 1 byte>
            byte[] flag0Key = new byte[1];
            rand.read(flag0Key);

            // change the flag by overwriting the value of the flag1
            byte edit = 0x01;
            rand.write(edit);

            rand.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

//    @Override
//    public long migrate() {
//        try {
//            RandomAccessFile og = new RandomAccessFile(this.file, "r");
//            readMetaData(og);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return 0;
//    }

//    @Override
//    public int readMetaData(RandomAccessFile rand) throws IOException {
//        checkMagicNumber(rand);
//        checkKey(rand);
//        checkFlags(rand);
//        readSize(rand);
//        return 0;
//    }

    @Override
    public int checkMagicNumber(RandomAccessFile rand) throws IOException {
        // read magic number for 4 bytes
        byte[] magic = new byte[4];
        rand.read(magic);
        int magicNumber = ByteBuffer.wrap(magic).getInt();

        // check if the header is the same magic number
        // if it is not the same format, retrieve fail -1
        if (magicNumber != IPhoto.HEADER_MAGIC_NUMBER) {
            rand.close();
            return -1;
        }
        return 0;
    }

    @Override
    public int checkKey(RandomAccessFile rand) throws IOException {
        // read key for 1 bytes
        byte[] key = new byte[1];
        rand.read(key);

        // read alternate key for 1 bytes
        byte[] alternateKey = new byte[1];
        rand.read(alternateKey);
        return 0;
    }

    @Override
    public int readSize(RandomAccessFile rand) throws IOException {
        // read the size of 4 bytes
        byte[] sizeRead = new byte[4];
        rand.read(sizeRead);
        int size = ByteBuffer.wrap(sizeRead).getInt();
        System.out.println("READ SIZE: " + size);
        return size;
    }

    @Override
    public int compress(IndexFile newIndex) {
        // read entire haystack and find the non-deleted photo and
        // copy old to new index and new haystack in new index
        try {
            RandomAccessFile oldFile = new RandomAccessFile(this.file, "r");
//            File newFilePath = newIndex.getHaystack().getFile();
//            RandomAccessFile newFile = new RandomAccessFile(newFilePath, "w");

            while (true) {
                this.curPointer = oldFile.getFilePointer();
                System.out.println("000000000000000============="+curPointer);
                byte[] magic = new byte[4];
                oldFile.read(magic);
                int magicNumber = ByteBuffer.wrap(magic).getInt();
                if (magicNumber != IPhoto.HEADER_MAGIC_NUMBER) {
                    oldFile.close();
                    System.out.println("Not the same Magic number");
                    return -1;
                }
//                this.checkKey(oldFile);
                
                byte[] key = new byte[1];
                oldFile.read(key);
                
                byte[] alternateKey = new byte[1];
                oldFile.read(alternateKey);

                // read flags <key 1 byte> <value 1 byte>
                byte[] flag0 = new byte[2];
                oldFile.read(flag0);

                // read flags <key 1 byte> <value 1 byte>
                byte[] flag1 = new byte[2];
                oldFile.read(flag1);

                byte[] flag2 = new byte[2];
                oldFile.read(flag2);
                
                System.out.println("----------" + (flag2[1]));
                int size = this.readSize(oldFile);

                // if this photo is not deleted, move the entire thing there
                if (flag1[1] == 0x00) {
//                    oldFile.seek(curPointer);
//                    byte[] fullPhoto = new byte[IPhoto.META_DATA_LENGTH + size];
//                    oldFile.read(fullPhoto);
//                    newFile.write(fullPhoto);
                    byte[] photoData = new byte[size];
                    oldFile.read(photoData);
                    Photo newPhoto = this.convertBytesToPhoto(photoData);
                    if (newIndex.addPhoto(newPhoto) == -1) {
                        oldFile.close();
                        return -1;
                    }
                } else {
                    // if this photo is delete, read past this photo
                    oldFile.seek(oldFile.getFilePointer() + size);
                    // set the pointer to the start of next photo
                    this.curPointer = oldFile.getFilePointer();
                }
                
            }
//            oldFile.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int migrate() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int readMetaData(RandomAccessFile rand) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean checkIsDeleted(RandomAccessFile rand) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

    public long appendPhoto(byte[] photoData) {
        // TODO Auto-generated method stub
        return 0;
    }

}
