package haystack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

class IndexFileTest {


    IndexKey k;
    IndexVal v;
    IndexFile f = new IndexFile(0);

    @BeforeEach
    void setUp() throws Exception {
        k = new IndexKey(0, 1);
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
        v = new IndexVal(4, 5);

    }

    @Test
    void testAddIndex() {
        
        f.addIndex(k, v);
        Map<Integer, Integer> updatedFlags = new HashMap<Integer, Integer>();
        updatedFlags.put(0,0);
        updatedFlags.put(1,0);
        assertEquals(f.getFlags(k),updatedFlags);
    }
    

    @Test
    void testAddPhoto() {
        Photo photo = new Photo("cat.jpeg");
       
    }

}
