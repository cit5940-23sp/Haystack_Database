package haystack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import photo.IPhoto;

class PhotoTest {

    @BeforeEach
    void setUp() throws Exception {
        
    }

    @Test
    void loadImageRasterTest() {
//        Photo photo = new Photo("cat.jpeg");
        Photo.loadImageRaster("cat.jpeg");
    }

}
