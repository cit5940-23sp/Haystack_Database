package haystack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import photo.IPhoto;
import user.User;
import user.UserGraph;
import user.UserLocationMap;

class HaystackTest {

    @BeforeEach
    void setUp() throws Exception {
    }

//    @Test
//    void appendPhotoTest() {
////        Photo photo = new Photo("cat.jpeg");
////        HaystackObjectStore haystack = new HaystackObjectStore("Database_test1.txt");
//        ListOfHaystacks loh = new ListOfHaystacks();
//        
//        String userName = "Tim";
//        
//        int nextUserID = 1;
//        
//        int latitude = 90;
//        
//        int longitude = 90;
//        
//        UserGraph graphOfConnections = new UserGraph();
//        
//        UserLocationMap userLocationMap = new UserLocationMap();
//        
//        User newUser = new User(userName, nextUserID, latitude, 
//                longitude, userLocationMap, graphOfConnections );
//        
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        
//        System.out.println("Photo added");
//        
//        newUser.getPhoto(11, loh);
//
////        
////        long offset = haystack.appendPhoto(photo);
////        byte[] data = haystack.getPhoto(offset, photo.getSize());
////        IPhoto.bytesToImage(data);
//        
//    }
    
//    @Test
//    void deletePhotoTest() {
//        
//        ListOfHaystacks loh = new ListOfHaystacks();
//        
//        String userName = "Tim";
//        
//        int nextUserID = 1;
//        
//        int latitude = 90;
//        
//        int longitude = 90;
//        
//        UserGraph graphOfConnections = new UserGraph();
//        
//        UserLocationMap userLocationMap = new UserLocationMap();
//        
//        User newUser = new User(userName, nextUserID, latitude, 
//                longitude, userLocationMap, graphOfConnections );
//        
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./cat.jpeg", loh);
//        newUser.addPhoto("./Puppy.jpeg", loh);
//        newUser.addPhoto("./cat.jpeg", loh);
//
//        
//        System.out.println("Photo added");
//        
//        newUser.deletePhoto(3, loh);
//        
//        System.out.println("Photo deleted");
//        
//        newUser.getPhoto(3, loh);
//        
//    }
    
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
        
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./cat.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);
        newUser.addPhoto("./DogOrange.jpeg", loh, false);

        
        System.out.println("Photo added");
        
        newUser.updatePhoto("./Puppy.jpeg", 3, loh);
        
        System.out.println("Photo updated");
        

        System.out.println("Get photo");
        
        newUser.getPhoto(3, loh, newUser);
        
    }

}
