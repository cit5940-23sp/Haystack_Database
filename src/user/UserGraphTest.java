package user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import haystack.ListOfHaystacks;

class UserGraphTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

  
    }

//    @Test
//    void testUserGraph() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testAddNewUser() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testAddNewFriend() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetFriends() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetFriendsOfFriends() {
//        fail("Not yet implemented");
//    }

    @Test
    void testGetFriendRecommondation() {
        
        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser("Amy", 30, 180);
        lou.addUser("Tom", 30, 180);
        lou.addUser("Jim", 90, 180);
        lou.addUser("Kim", 30, 180);
        lou.addUser("Tim", 30, 180);
        lou.addUser("Jane", 30, 70);
        lou.addUser("Brian", 30, 180);
        lou.addUser("Wes", 0, 30);
        lou.addUser("Annie", 30, 180);
        lou.addUser("Amber", 30, 100);
        lou.addUser("Mike", 30, 180);
        lou.addUser("Mickey", 30, 180);
        
        
        UserGraph goc = lou.getGraphOfConnections();
        UserLocationMap ulm = lou.getUserLocationMap();
        
        goc.addNewFriend(lou.getUser(0), lou.getUser(1), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(0), lou.getUser(2), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(1), lou.getUser(3), lou.getUserLocationMap());     
        goc.addNewFriend(lou.getUser(2), lou.getUser(3), lou.getUserLocationMap());     
        goc.addNewFriend(lou.getUser(5), lou.getUser(3), lou.getUserLocationMap());    
        goc.addNewFriend(lou.getUser(6), lou.getUser(3), lou.getUserLocationMap());   
        goc.addNewFriend(lou.getUser(7), lou.getUser(3), lou.getUserLocationMap());  
        goc.addNewFriend(lou.getUser(8), lou.getUser(3), lou.getUserLocationMap());  
        goc.addNewFriend(lou.getUser(9), lou.getUser(3), lou.getUserLocationMap());   
        
        goc.getFriendRecommondation(1, lou, ulm);
        
        
        
    }

}
