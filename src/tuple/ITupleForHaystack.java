package tuple;

import haystack.HaystackObjectStore;
import haystack.IndexFile;

public interface ITupleForHaystack {

    /**
    * Get indexFile 
    * @return indexFile
    */
    IndexFile getIndex();
    
    /**
    * Get haystack object store
    * @return haystack object store
    */
    HaystackObjectStore getHaystack();

    
}
