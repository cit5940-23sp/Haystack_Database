package haystack;

import java.util.HashMap;
import java.util.Map;

public class IndexKey implements IIndex {
    private int key;
    private int alternateKey;

    
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


}
