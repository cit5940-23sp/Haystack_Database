package haystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import photo.IPhoto;

public class Photo implements IPhoto {
    private int key;
    private int alternateKey;
    private Map<Integer, Integer> flags;
    private int size;
    private byte[] data;

    public Photo(String filePath) {
        this.data = IPhoto.loadImageToBytes(filePath);
        this.flags = new HashMap<Integer, Integer>();
        this.size = this.data.length;
        this.flags.put(IPhoto.EDITED, 0);
        this.flags.put(IPhoto.DELETED, 0);
        this.flags.put(IPhoto.NEXT, 0);
    }

    public Photo(byte[] incomingData, int key, int alternateKey) {
        this.data = incomingData;
        this.flags = new HashMap<Integer, Integer>();
        this.size = this.data.length;
        this.flags.put(IPhoto.EDITED, 0);
        this.flags.put(IPhoto.DELETED, 0);
        this.flags.put(IPhoto.NEXT, 0);
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

    @Override
    public boolean equals(Object o) {

        if (this.size != ((Photo) o).getSize()) {
            return false;
        }
        if (!Arrays.equals(this.data, ((Photo) o).getData())) {
            return false;
        }
        return true;
    }

}
