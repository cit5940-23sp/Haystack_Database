package haystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import haystack.QuadraHashMap.QuadraticHashMap;

public class IndexFile implements IIndexFile {

    QuadraticHashMap hm = new QuadraticHashMap(HMMaxSize);
    
    //EXAMPLE 
    int indexID;
    HaystackObjectStore haystack;
    
    IndexFile(int indexID){
 
        this.indexID = indexID;
        
        String file_path = "Database_" + String.valueOf(indexID) + ".txt";

        haystack = new HaystackObjectStore(file_path); 
        
    }


    @Override
    public Map<Integer, Integer> getFlags(IndexKey k) {
        IndexVal v = hm.get(k);
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
        flags = v.getFlags();
        return flags;
    }


    @Override
    public void addIndex(IndexKey newIndexKey, IndexVal newIndexVal) {
        hm.insert(newIndexKey, newIndexVal);
    }
    
    
    public void addPhoto(Photo inputPhoto) {
        
      
        long offset = haystack.appendPhoto(inputPhoto);
        
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
        
        IndexVal indexVal = new IndexVal(offset, inputPhoto.getSize());
        
        IndexKey indexKey = new IndexKey(inputPhoto.getKey(), inputPhoto.getAlternateKey());
      
        addIndex(indexKey, indexVal);
        
    }

}
