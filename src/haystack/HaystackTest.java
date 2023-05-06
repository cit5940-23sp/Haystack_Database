package haystack;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import photo.IPhoto;
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
        
        int nextUserID = 1;
        
        int latitude = 90;
        
        int longitude = 90;
        
        UserGraph graphOfConnections = new UserGraph();
        
        UserLocationMap userLocationMap = new UserLocationMap();
        
        User newUser = new User(userName, nextUserID, latitude, 
                longitude, userLocationMap, graphOfConnections );
        
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);
        
        System.out.println("Photo added");
        
        newUser.getPhoto(11, loh, newUser);
        
    }
    
    @Test
    void deletePhotoTest() {
        
        ListOfHaystacks loh = new ListOfHaystacks();
        
        String userName = "Tim";
        
        int nextUserID = 1;
        
        int latitude = 90;
        
        int longitude = 90;
        
        UserGraph graphOfConnections = new UserGraph();
        
        UserLocationMap userLocationMap = new UserLocationMap();
        
        User newUser = new User(userName, nextUserID, latitude, 
                longitude, userLocationMap, graphOfConnections );
        
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);

        
        System.out.println("Photo added");
        
        newUser.deletePhoto(3, loh);
        
        System.out.println("Photo deleted");
        
        newUser.getPhoto(3, loh, newUser);
        
        
    }
    
    @Test
    void updatePhotoTest() {
    
        ListOfHaystacks loh = new ListOfHaystacks();
        
        String userName = "Tim";
        
        int nextUserID = 1;
        
        int latitude = 90;
        
        int longitude = 90;
   
        
        UserGraph graphOfConnections = new UserGraph();
        
        UserLocationMap userLocationMap = new UserLocationMap();
        
        User newUser = new User(userName, nextUserID, latitude, 
                longitude, userLocationMap, graphOfConnections );
        
//        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./Puppy.jpeg", loh, false);
        
//        newUser.addPhoto("./cat.jpeg", loh, false);
//        newUser.addPhoto("./cat.jpeg", loh, false);
//        newUser.addPhoto("./cat.jpeg", loh, false);


        
        System.out.println("Photo added");
        
//        newUser.deletePhoto(0, loh);
        newUser.deletePhoto(1, loh);
//        newUser.deletePhoto(2, loh);
//        newUser.deletePhoto(3, loh);
        
//        newUser.getPhoto(0, loh, newUser);

        System.out.println("Photo updated");
        

        System.out.println("Compress photo");
        
        
       
        loh.compressHaystacks();
        
//        assertEquals(0, newUser.getUserPhotoList().getPhoto(0).getKey());
//        assertEquals(1, newUser.getUserPhotoList().getAllPhotos().size());
        
    }

}
