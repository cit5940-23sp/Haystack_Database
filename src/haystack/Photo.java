package haystack;

import java.util.Map;

import photo.IPhoto;

public class Photo implements IPhoto {
    private int key;
    private int alternateKey;
    private Map<String, Integer> flags;
    private int size; 
    private Byte[] data;
    private Byte padding;
    
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
    
    public void setFlags(Map<String, Integer> flags) {
        this.flags = flags;
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
    
    public Byte getPadding() {
        return padding;
    }
    
    public void setPadding(Byte padding) {
        this.padding = padding;
    }
}
