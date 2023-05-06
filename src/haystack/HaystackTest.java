package haystack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.ListOfUsers;
import user.User;


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

        newUser.deletePhoto(3, loh);

        Photo got = newUser.getPhoto(3, loh);

        assertNull(got);

    }

    @Test
    void updateAndCompressPhotoTest() {

        ListOfHaystacks loh = new ListOfHaystacks();

        String userName = "Tim";


        int latitude = 90;

        int longitude = 90;

        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser(userName, latitude, longitude);
        
        User newUser = lou.getUser(0);
        
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);
        newUser.addPhoto("./facebook-logo.jpeg", loh, false);
        newUser.addPhoto("./panda.jpeg", loh, false);


        newUser.deletePhoto(1, loh);
        newUser.deletePhoto(0, loh);
        newUser.deletePhoto(2, loh);


        loh.compressHaystacks();
        
        Photo got = newUser.getPhoto(1, loh);
        assertNull(got);

        Photo got0 = newUser.getPhoto(3, loh);
        Photo zero = new Photo("./panda.jpeg");

        assertEquals(zero, got0);

    }

}
