package haystack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import photo.IPhoto;

public class Photo implements IPhoto {
    private int key;
    private int alternateKey;
    private Map<Integer, Integer> flags;
    private int size;
    private byte[] data;
    private byte padding;

    public Photo(String filePath) {
        loadImageRaster(filePath);
        this.flags = new HashMap<Integer, Integer>();
        this.flags.put(IPhoto.EDITED, 0);
        this.flags.put(IPhoto.DELETED, 0);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getAlternateKey() {
        return alternateKey;
    }

    public void setAlternateKey(int alternateKey) {
        this.alternateKey = alternateKey;
    }

    public Map<Integer, Integer> getFlags() {
        return flags;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setPadding(byte padding) {
        this.padding = padding;
    }
    
    public byte getPadding() {
        return this.padding;
    }

    public Raster loadImageRaster(String file_path) {
        File input = new File(file_path);
        BufferedImage buf_image;
        try {
            buf_image = ImageIO.read(input);
            buf_image = binarizeImage(buf_image);
            return buf_image.getData(); // return raster
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage binarizeImage(BufferedImage img_param) {
        BufferedImage image = new BufferedImage(img_param.getWidth(), img_param.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics g = image.getGraphics();
        g.drawImage(img_param, 0, 0, null);
        g.dispose();

        return image;
    }

}
