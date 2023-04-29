package haystack;

import java.util.Map;

public interface IIndexFile {
    int SIZE = 100;
    //private List<Map<String, ?>> indexFile;
    
    /**
     * Add a new index record 
     */
    void addIndex(Index newIndex);
    
    /**
     * Get an existing index record 
     */
    Map<String, Integer> getFlags(String k);
    
    
}
