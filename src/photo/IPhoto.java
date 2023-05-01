package photo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

public interface IPhoto {
    
    public static final int MAXIMUM_BYTES_DATA = 10000; 
    public static final int HEADER_MAGIC_NUMBER = 123456789;
    
    public static final int EDITED = 0;
    public static final int DELETED = 1;
    
    public static final int META_DATA_LENGTH = 14;
    
    public static byte[] loadImageToBytes(String file_path) {
        File input = new File(file_path);
        BufferedImage buf_image;
        try {
            buf_image = ImageIO.read(input);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(buf_image, "jpg", bos );
            
            byte [] data = bos.toByteArray();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * User will call this method and output a local file of jpg
     * @param data byte[]
     */
    public static Image bytesToImage(byte[] data, int uniqueID) {
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        try {
            BufferedImage res = ImageIO.read(input);
            ImageIO.write(res, "jpg", new File("output" + uniqueID + ".jpg") );
            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    

}
