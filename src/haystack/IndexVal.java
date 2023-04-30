package haystack;

import java.util.HashMap;
import java.util.Map;

public class IndexVal {
    private Map<String, Integer> flags;
    private int offset;
    private int size;
    

    public Map<String, Integer> getFlags() {
        return flags;
    }
    
    public void setFlags(HashMap<String, Integer> flags) {
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
    
    public void updateFlag(String fs, int fi) {
        Map<String, Integer> updatedFlag = new HashMap<String, Integer>();
        updatedFlag.put(fs, fi);
        flags = updatedFlag;
    }
    
    public void getPhoto() {
        int off = getOffset();
        
    }

}
