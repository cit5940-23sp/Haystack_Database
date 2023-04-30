package haystack;

import java.util.HashMap;
import java.util.Map;

public class IndexVal {
    private Map<Integer, Integer> flags;
    private int offset;
    private int size;
    
    public IndexVal(Map<Integer, Integer> flags, int offset, int size) {
        this.flags = flags;
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
    
    public int getOffset() {
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
        int off = getOffset();
        
    }

}
