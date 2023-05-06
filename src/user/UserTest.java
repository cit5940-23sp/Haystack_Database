package user;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import haystack.ListOfHaystacks;

class UserTest {

    String filePath = null;
    String filePath2 = null;
    String updateFile = null;

    ListOfHaystacks loh;
    User curUser;
    List<UserPhotoNode> listOfPhotoNodes;

    @BeforeEach
    void setUp() throws Exception {

        UserGraph goc = new UserGraph();
        UserLocationMap ulm = new UserLocationMap();
        curUser = new User("Amy", 0, 90, 90, ulm, goc);
        filePath = "cat.jpeg";
        filePath2 = "DogOrange.jpeg";
        updateFile = "Puppy.jpeg";

        loh = new ListOfHaystacks();

    }

    @Test
    void testAddPhoto() {

        curUser.addPhoto(filePath, loh, false);

        assertEquals(curUser.getUserPhotoList().numberOfPhotos(), 1);

        curUser.addPhoto(filePath2, loh, false);

        assertEquals(curUser.getUserPhotoList().numberOfPhotos(), 2);

    }

    @Test
    void testDeletePhoto() {
        curUser.addPhoto(filePath, loh, false);
        curUser.addPhoto(filePath2, loh, false);
        curUser.deletePhoto(0, loh);
        assertEquals(curUser.getUserPhotoList().numberOfPhotos(), 2);

        int deletedCount = 0;
        assertEquals(curUser.getUserPhotoList().getPhoto(deletedCount).getDeleted(), 1);

    }

    @Test
    void testUpdatePhoto() {
        curUser.addPhoto(filePath, loh, false);
        curUser.addPhoto(filePath2, loh, false);

        curUser.updatePhoto(updateFile, 0, loh);

        UserPhotoNode upn = curUser.getUserPhotoList().getPhoto(0);
        upn.setFilename(updateFile);

        assertEquals(curUser.getUserPhotoList().getPhoto(0).getFilename(), updateFile);

    }
}
