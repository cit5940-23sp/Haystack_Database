package haystack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.ListOfUsers;
import user.User;
import user.UserGraph;
import user.UserLocationMap;

class HaystackTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void appendPhotoTest() {
        ListOfHaystacks loh = new ListOfHaystacks();

        String userName = "Tim";

        int latitude = 90;

        int longitude = 90;
        
        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser(userName, latitude, longitude);
        
        User newUser = lou.getUser(0);

        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);

        System.out.println("Photo added");

        Photo p1 = newUser.getPhoto(1, loh);
        Photo zero = new Photo("./Puppy.jpeg");
        assertEquals(zero, p1);

    }

    @Test
    void deletePhotoTest() {

        ListOfHaystacks loh = new ListOfHaystacks();

        String userName = "Tim";


        int latitude = 90;

        int longitude = 90;

        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser(userName, latitude, longitude);
        
        User newUser = lou.getUser(0);
        
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);

        System.out.println("Photo added");

        newUser.deletePhoto(3, loh);

        System.out.println("Photo deleted");

        Photo got = newUser.getPhoto(3, loh);

        assertEquals(null, got);

    }

    @Test
    void updatePhotoTest() {

        ListOfHaystacks loh = new ListOfHaystacks();

        String userName = "Tim";


        int latitude = 90;

        int longitude = 90;

        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser(userName, latitude, longitude);
        
        User newUser = lou.getUser(0);
        
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);


        System.out.println("Photo added");
        newUser.deletePhoto(1, loh);

        System.out.println("Photo updated");

        System.out.println("Compress photo");

        loh.compressHaystacks();

        Photo got = newUser.getPhoto(1, loh);
        assertEquals(null, got);
        Photo got0 = newUser.getPhoto(0, loh);
        Photo zero = new Photo("./cat.jpeg");
        assertEquals(zero, got0);

    }

}
