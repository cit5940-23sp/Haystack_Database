package haystack;

import java.util.Map;

public interface IIndexFile {
    int HMMaxSize = 1000;
    //private List<Map<String, ?>> indexFile;
    
    /**
     * Add a new index record 
     */
    void addIndex(IndexKey newIndexKey, IndexVal newIndexVal);
    
    /**
     * Get an existing index record 
     */
    Map<Integer, Integer> getFlags(IndexKey k);
    
    
}
