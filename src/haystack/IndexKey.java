package haystack;


public class IndexKey {
    private int key;
    private int alternateKey;

    public IndexKey(int key, int alternateKey) {
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

}
