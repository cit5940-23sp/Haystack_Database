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
import java.util.Map;

import photo.IPhoto;

public class HaystackObjectStore implements IHaystackObjectStore {
    
    private File file;
    private long EOF;
    
    
    public HaystackObjectStore(String filePath) {
        this.file = new File(filePath);
        this.EOF = 0;
    }
    
    @Override
    public long appendPhoto(Photo newPhoto) {
        // TODO Auto-generated method stub
        try {
            //append write to the file
            FileOutputStream out = new FileOutputStream(this.file, true);
            
            this.EOF = this.file.length() - 1;
            
            //convert new photo to bytes and write to file
            convertPhotoToBytes(newPhoto, out);
            
            out.flush();
            out.close();
            
            return this.EOF;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
    
    /**
     * The function that convert Photo object's content to byte[] and write to out file
     * @param p photo object
     * @param out out file 
     * @throws IOException
     */
    public void convertPhotoToBytes(Photo p, FileOutputStream out) throws IOException {
        //convert MAGIC NUMBER to byte & write
        BigInteger magic = BigInteger.valueOf(IPhoto.HEADER_MAGIC_NUMBER);      
        byte[] magicB = magic.toByteArray();
        out.write(magicB);
        System.out.println("magic number size:" + magicB.length);
        
        //get photo key & convert to byte & write
        int key = p.getKey();
        BigInteger keyInt = BigInteger.valueOf(key);      
        byte[] keyB = keyInt.toByteArray();
        out.write(keyB);
        
                
        //get photo alternate key & convert to byte & write
        int alternateKey = p.getAlternateKey();
        BigInteger alternateKeyInt = BigInteger.valueOf(alternateKey);      
        byte[] alternateKeyB = alternateKeyInt.toByteArray();
        out.write(alternateKeyB);
        
        //parse flags, convert to binary and write
        Map<Integer, Integer> flags = p.getFlags();
        for(Map.Entry<Integer, Integer> e : flags.entrySet()) {
            //flag keys
            BigInteger flagKey = BigInteger.valueOf(e.getKey());  
            byte[] flagKeyB = flagKey.toByteArray();
            out.write(flagKeyB);
            
            BigInteger flagVal = BigInteger.valueOf(e.getValue());  
            byte[] flagValB = flagVal.toByteArray();
            out.write(flagValB);
        }
        
        //get size & convert to byte & write
        //size is 32 bit/ 4 bytes long
        int size = p.getKey();
        BigInteger sizeInt = BigInteger.valueOf(size);      
        byte[] sizeB = sizeInt.toByteArray();
        
        //add padding to make it 4bytes
        byte[] paddedBytes = new byte[4];
        System.arraycopy(sizeB, 0, paddedBytes, 4 - sizeB.length, sizeB.length);
        out.write(paddedBytes);
        
        //write data bytes[] to out
        out.write(p.getData());
        
//        //write padding
//        out.write(p.getPadding());
        
        
        
    }

    @Override
    public byte[] getPhoto(long offset, int size) {
        try {
            RandomAccessFile rand = new RandomAccessFile(this.file, "r");
            
            rand.seek(offset);
            System.out.print(offset);
            //read things
            //read magic number for 4 bytes
            byte[] magic= new byte[4];
            rand.read(magic);
            int magicNumber = ByteBuffer.wrap(magic).getInt();
            System.out.println(magicNumber);
            //check if the header is the same magic number
            //if it is not the same format, retrieve fail -1
            if(magicNumber != IPhoto.HEADER_MAGIC_NUMBER) {
                rand.close();
                System.out.println("Not the same Magic number");
                return null;
            }
            
            //read key for 2 bytes
            byte[] key = new byte[2];
            rand.read(key);
            
            //read alternate key for 2 bytes
            byte[] alternateKey = new byte[2];
            rand.read(alternateKey);
            
            //read flags <key 1 byte> <value 1 byte>
            byte[] flag0 = new byte[2];
            rand.read(flag0);
            
            byte[] flag1 = new byte[2];
            rand.read(flag1);
            
            //if flag DELETED's value is 1, meaning photo is deleted
            //can't access deleted photo, return -1
            if(flag1[1] == 0x01) {
                rand.close();
                return null;
            }
            //read the size of 4 bytes
            byte[] sizeRead = new byte[4];
            rand.read(sizeRead);
            
            //read photo data
            //return byte[] of the photo to caller
            byte[] data = new byte[size];
            int i = 0;
            while(size > 0) {
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
    public int deletePhoto(int offset) {
        try {
            RandomAccessFile rand = new RandomAccessFile(this.file, "rw");
            
            rand.seek(offset);
            
            //read things
            //read magic number for 4 bytes
            byte[] magic= new byte[4];
            rand.read(magic);
            int magicNumber = ByteBuffer.wrap(magic).getInt();
            
            //check if the header is the same magic number
            //if it is not the same format, retrieve fail -1
            if(magicNumber != IPhoto.HEADER_MAGIC_NUMBER) {
                rand.close();
                return -1;
            }
            
            //read key for 2 bytes
            byte[] key = new byte[2];
            rand.read(key);
            
            //read alternate key for 2 bytes
            byte[] alternateKey = new byte[2];
            rand.read(alternateKey);
            
            //read flags <key 1 byte> <value 1 byte>
            byte[] flag0 = new byte[2];
            rand.read(flag0);
            
            byte[] flag1Key = new byte[1];
            rand.read(flag1Key);
            
            //change the flag by overwriting the value of the flag1
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

//    @Override
//    public void setPublicPrivate(int offset) {
//
//    }

}
