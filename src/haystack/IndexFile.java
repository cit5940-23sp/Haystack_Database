package haystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import haystack.QuadraHashMap.QuadraticHashMap;
import photo.IPhoto;

public class IndexFile implements IIndexFile {

    QuadraticHashMap hm = new QuadraticHashMap(HMMaxSize);

    @Override
    public Map<Integer, Integer> getFlags(IndexKey k) {
        IndexVal v = hm.get(k);
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
        flags = v.getFlags();
        return flags;
    }
    
    public void updateFlags(IndexKey k) {
        IndexVal v = hm.get(k);
        Map<Integer, Integer> flags = v.getFlags();
        flags.put(IPhoto.DELETED,1);
    }


    @Override
    public void addIndex(IndexKey newIndexKey, IndexVal newIndexVal) {
        hm.insert(newIndexKey, newIndexVal);
    }
    
}
