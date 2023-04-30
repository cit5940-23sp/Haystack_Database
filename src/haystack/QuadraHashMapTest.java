package haystack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haystack.QuadraHashMap.QuadraticHashMap;

class QuadraHashMapTest {
    IndexKey k;
    IndexVal v;
    @BeforeEach
    void setUp() throws Exception {
        k = new IndexKey(0, 1);
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
        flags.put(2, 3);
        v = new IndexVal(4, 5);
    }

    @Test
    void test() {

        QuadraticHashMap qpht = new QuadraticHashMap(1000);
        
        qpht.insert(k, v);
        assertEquals(qpht.getSize(),1);
        assertEquals(qpht.get(k).getOffset(),4);

        qpht.makeEmpty();
        assertEquals(qpht.getSize(),0);

   
    }

}
