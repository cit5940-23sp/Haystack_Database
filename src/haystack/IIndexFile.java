package haystack;

public interface IIndexFile {

    //private List<Map<String, ?>> indexFile;
    
    /**
     * Add a new index record 
     */
    void addIndex(Index newIndex);
    
    /**
     * Get an existing index record 
     */
    Index getIndex(int alternateKey);
    
    
}
