package haystack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HaystackTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void appendPhotoTest() {
        Photo photo = new Photo("cat.jpeg");
        HaystackObjectStore haystack = new HaystackObjectStore("Database_test1.txt");
        haystack.appendPhoto(photo);
    }

}
