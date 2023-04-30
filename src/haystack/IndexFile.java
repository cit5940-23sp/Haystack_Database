package haystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import haystack.QuadraHashMap.QuadraticHashMap;

public class IndexFile implements IIndexFile {

    QuadraticHashMap hm = new QuadraticHashMap(HMMaxSize);


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
    

}
