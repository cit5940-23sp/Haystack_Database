package haystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import haystack.QuadraHashMap.QuadraticHashMap;

public class IndexFile implements IIndexFile {

    private String k;
    private String v;

    QuadraticHashMap hm = new QuadraticHashMap(SIZE);

    @Override
    public void addIndex(Index newIndex) {
        // TODO Auto-generated method stub
        int key = newIndex.getKey();
        int alternateKey = newIndex.getAlternateKey();
        Map<String, Integer> flags = newIndex.getFlags();
        int offset = newIndex.getOffset();
        int size = newIndex.getSize();
        k = Integer.toString(key) + " " + Integer.toString(alternateKey);
        v = Arrays.asList(flags) + " "+ Integer.toString(offset) + " "+ Integer.toString(size);
        hm.insert(k, v);
        
    }

    @Override
    public Map<String, Integer> getFlags(String k) {
        // TODO Auto-generated method stub
        String flagsK = hm.get(k).substring(2,3); 
        String flagsV = hm.get(k).substring(4,5);        
        Map<String, Integer> flags = new HashMap<String, Integer>();
        flags.add
        return flags;
    }

}
