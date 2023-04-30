package haystack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.Map;

public class HaystackObjectStore implements IHaystackObjectStore {

    /**
     * Write to the end of disk file
     */
    @Override
    public void appendPhoto(Photo newPhoto) {
        // TODO Auto-generated method stub
        try {
            //append write to the file
            FileOutputStream out = new FileOutputStream("Database.txt", true);
            
            //convert new photo to bytes and write to file
            convertPhotoToBytes(newPhoto, out);
            
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * The function that convert Photo object's content to byte[] and write to out file
     * @param p photo object
     * @param out out file 
     * @throws IOException
     */
    public void convertPhotoToBytes(Photo p, FileOutputStream out) throws IOException {
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
        Map<String, Integer> flags = p.getFlags();
        for(Map.Entry<String, Integer> e : flags.entrySet()) {
            //flag keys
            String flagKey = e.getKey();
            byte[] flagKeyB = flagKey.getBytes();
            out.write(flagKeyB);
            
            BigInteger flagVal = BigInteger.valueOf(e.getValue());  
            byte[] flagValB = flagVal.toByteArray();
            out.write(flagValB);
        }
        
        //get size & convert to byte & write
        int size = p.getKey();
        BigInteger sizeInt = BigInteger.valueOf(size);      
        byte[] sizeB = sizeInt.toByteArray();
        out.write(sizeB);
        
        //write data bytes[] to out
        out.write(p.getData());
        
        //write padding
        out.write(p.getPadding());
    }

    @Override
    public void getPhoto(int offset) {
        File file = new File("Database.txt");
        try {
            RandomAccessFile rand = new RandomAccessFile(file, "r");
            
            rand.seek(offset);
            
            //read things
            
            
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhoto(int offset) {
        // TODO Auto-generated method stub

    }

//    @Override
//    public void setPublicPrivate(int offset) {
//        // TODO Auto-generated method stub
//
//    }

}
