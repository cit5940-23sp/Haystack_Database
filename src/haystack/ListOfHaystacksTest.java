package haystack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import photo.IPhoto;

class ListOfHaystacksTest {
    ListOfHaystacks lohs = new ListOfHaystacks();

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testAddPhotoToHaystack() {
        String filePath = "cat.jpeg";
        Photo photoToAdd = new Photo(filePath);

        List<Integer> returnVal = new ArrayList<Integer>();
        returnVal.add(0);
        returnVal.add(0);
        returnVal.add(0);

        assertEquals(returnVal, lohs.addPhotoToHaystack(photoToAdd));
    }

    @Test
    void testGetPhotoFromHaystack() {
        String filePath = "cat.jpeg";
        Photo photoToAdd = new Photo(filePath);
        lohs.addPhotoToHaystack(photoToAdd);
        assertEquals(lohs.getPhotoFromHaystack(0, 0).length, 14406);

    }

    @Test
    void testDeletePhotoFromHaystack() {
        String filePath = "cat.jpeg";
        Photo photoToAdd = new Photo(filePath);
        lohs.addPhotoToHaystack(photoToAdd);
        lohs.deletePhotoFromHaystack(0, 0);

        assertEquals(lohs.getPhotoFromHaystack(0, 0), null);
    }

    @Test
    void testUpdatePhotoInHaystack() {

        String filePath = "cat.jpeg";
        Photo photoToAdd = new Photo(filePath);

        String filePathUpdate = "Puppy.jpeg";
        Photo photoToAddUpdate = new Photo(filePathUpdate);

        lohs.addPhotoToHaystack(photoToAdd);
        lohs.updatePhotoInHaystack(photoToAddUpdate, 0, 0);
        assertEquals(lohs.getPhotoFromHaystack(0, 0).length, 11894);
    }

    @Test
    void testAssignHaystack() {
        String filePath = "cat.jpeg";
        Photo photoToAdd = new Photo(filePath);
        lohs.addPhotoToHaystack(photoToAdd);

        assertEquals(lohs.assignHaystack(photoToAdd), 0);

    }

    @Test
    void testCreateNewHaystack() {
        String filePath = "cat.jpeg";
        Photo photoToAdd = new Photo(filePath);
        lohs.addPhotoToHaystack(photoToAdd);
        lohs.createNewHaystack();
        assertEquals(lohs.listOfHaystacks.size(), 2);

    }

}
