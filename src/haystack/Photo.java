package haystack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import photo.IPhoto;

public class Photo implements IPhoto {
    private int key;
    private int alternateKey;
    private Map<String, Integer> flags;
    private int size;
    private Byte[] data;
    private Byte padding;

    public Photo(String filePath) {
        loadImageRaster(filePath);
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

    public Map<String, Integer> getFlags() {
        return flags;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public void setPadding(Byte padding) {
        this.padding = padding;
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
