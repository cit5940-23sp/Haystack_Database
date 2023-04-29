package haystack;

import java.util.HashMap;
import java.util.Map;

public class Index implements IIndex {
    private int key;
    private int alternateKey;
    private Map<String, Integer> flags;
    private int offset;
    private int size;
    
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
        return size;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public Photo getPhoto() {
        
        return null;
    }
    
    public void addPhoto() {
       
    }
    
    @Override
    public void updateFlag(String field, int updatedVal) {
        // TODO Auto-generated method stub
        
        
    }

}
