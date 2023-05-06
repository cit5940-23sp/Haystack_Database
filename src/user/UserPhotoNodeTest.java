package user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserPhotoNodeTest {

    @Test
    void testGetKey() {
      UserPhotoNode upn = new UserPhotoNode(1, 0, "./cat.jpeg", true);
      assertEquals(1, upn.getKey());
    }

    @Test
    void testGetAlternateKey() {
        UserPhotoNode upn = new UserPhotoNode(1, 0, "./cat.jpeg", true);
        assertEquals(0, upn.getAlternateKey());
    }

    @Test
    void testGetFilename() {
        UserPhotoNode upn = new UserPhotoNode(1, 0, "./cat.jpeg", true);
        assertEquals("./cat.jpeg", upn.getFilename());
    }

    @Test
    void testSetFilename() {
        UserPhotoNode upn = new UserPhotoNode(1, 0, "./cat.jpeg", true);
        upn.setFilename("./cat1.jpeg");
        assertEquals("./cat1.jpeg", upn.getFilename());
    }


    @Test
    void testSetPrivatePhoto() {
        UserPhotoNode upn = new UserPhotoNode(1, 0, "./cat.jpeg", true);
        upn.setPrivatePhoto(false);
        assertEquals(false, upn.getPrivateSettings());
    }

    @Test
    void testGetPrivateSettings() {
        UserPhotoNode upn = new UserPhotoNode(1, 0, "./cat.jpeg", true);
        assertEquals(true, upn.getPrivateSettings());
    }

}
