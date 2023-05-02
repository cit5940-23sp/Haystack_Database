package haystack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import photo.IPhoto;

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
        this.data = IPhoto.loadImageToBytes(filePath);
        this.flags = new HashMap<Integer, Integer>();
        this.size = this.data.length;
        this.flags.put(IPhoto.EDITED, 0);
        this.flags.put(IPhoto.DELETED, 0);
    }
    
    public Photo(byte[] incomingData, int key, int alternateKey) {
        this.data = incomingData;
        this.flags = new HashMap<Integer, Integer>();
        this.size = this.data.length;
        this.flags.put(IPhoto.EDITED, 0);
        this.flags.put(IPhoto.DELETED, 0);
        this.key = key;
        this.alternateKey = alternateKey;
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


}
