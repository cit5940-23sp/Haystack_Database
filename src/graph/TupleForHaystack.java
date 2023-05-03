package graph;

import haystack.HaystackObjectStore;
import haystack.IndexFile;

public class TupleForHaystack {

    private HaystackObjectStore haystack;
    private IndexFile index;
    
    /**
     * 
     * @param index
     * @param haystack
     */
    public TupleForHaystack(IndexFile index, HaystackObjectStore haystack){
        this.haystack = haystack;
        this.index = index;
    }
    
    /**
     * Get haystack
     * @return haystack
     */
    public HaystackObjectStore getHaystack() {
        return haystack;
    }
    
    /**
     * Get index
     * @return index
     */
    public IndexFile getIndex() {
        return index;
    }
    
    
}
