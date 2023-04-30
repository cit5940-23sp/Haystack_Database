package graph;

import haystack.HaystackObjectStore;
import haystack.IndexFile;

public class TupleForHaystack {

    private HaystackObjectStore haystack;
    private IndexFile index;
    
    TupleForHaystack(IndexFile index, HaystackObjectStore haystack){
        haystack = haystack;
        index = index;
    }
    
    HaystackObjectStore getHaystack() {
        return haystack;
    }
    
    IndexFile getIndex() {
        return index;
    }
    
    
}
