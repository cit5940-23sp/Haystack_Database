package haystack;

import java.util.HashMap;
import java.util.Map;

import photo.IPhoto;

public class IndexVal {
    private Map<Integer, Integer> flags;
    private long offset;
    private int size;
    
    public IndexVal(long offset, int size) {
        this.flags = new HashMap<Integer, Integer>();
        flags.put(IPhoto.EDITED, 0);
        flags.put(IPhoto.DELETED, 0);
        this.offset = offset;
        this.size = size;
    }

    public Map<Integer, Integer> getFlags() {
        return flags;
    }
    
    public void setFlags(HashMap<Integer, Integer> flags) {
        this.flags = flags;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public long getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public void updateFlag(int fs, int fi) {
        Map<Integer, Integer> updatedFlag = new HashMap<Integer, Integer>();
        updatedFlag.put(fs, fi);
        flags = updatedFlag;
    }
    
    public void getPhoto() {
        long off = getOffset();
        
    }

}
