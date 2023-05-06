package user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserPhotoListTest {

    List<UserPhotoNode> masterPhotoList;
    int deletedPhotoCount;
    UserPhotoList upl;
    UserPhotoNode upn;
    UserPhotoNode upn2;

    private int key;
    private int alternateKey;
    private String filePath;
    private int key2;
    private int alternateKey2;
    private String filePath2;

    @BeforeEach
    void setUp() throws Exception {
        upl = new UserPhotoList();
        key = 0;
        alternateKey = 1;
        filePath = "cat.jpeg";
        upn = new UserPhotoNode(key, alternateKey, filePath, false);
        key2 = 1;
        alternateKey2 = 1;
        filePath2 = "Puppy.jpeg";
        upn2 = new UserPhotoNode(key2, alternateKey2, filePath2, false);

    }

    @Test
    void testGetPhoto() {
        upl.addPhotoToUserList(upn);
        upl.addPhotoToUserList(upn2);

        List<UserPhotoNode> masterPhotoList = new ArrayList<UserPhotoNode>();
        masterPhotoList.add(upn);
        masterPhotoList.add(upn2);

        assertEquals(upl.getPhoto(0), upn);

        assertEquals(upl.getPhoto(1), upn2);

    }

    @Test
    void testGetAllPhoto() {
        upl.addPhotoToUserList(upn);
        List<UserPhotoNode> allPhotos = new ArrayList<UserPhotoNode>();
        allPhotos.add(upn);

        assertEquals(upl.getAllPhotos(), allPhotos);
    }
    
    
    @Test 
    void testDeletePhotoFromUserPhotoList() {
        
        upl.addPhotoToUserList(upn);
        assertEquals(upl.numberOfPhotos(), 1);
        assertEquals(upl.getAllPhotos().size(), 1);
        
        upl.deletePhotoFromUserPhotoList(upn);

        assertEquals(upl.numberOfPhotos(), 0);
        assertEquals(upl.getAllPhotos().size(), 0);
        
    }
}
