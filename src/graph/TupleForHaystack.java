package graph;

import haystack.HaystackObjectStore;
import haystack.IndexFile;

public class TupleForHaystack {

    private HaystackObjectStore haystack;
    private IndexFile index;
    
    public TupleForHaystack(IndexFile index, HaystackObjectStore haystack){
        this.haystack = haystack;
        this.index = index;
    }
    
    public HaystackObjectStore getHaystack() {
        return haystack;
    }
    
    public IndexFile getIndex() {
        return index;
    }
    
    
}
