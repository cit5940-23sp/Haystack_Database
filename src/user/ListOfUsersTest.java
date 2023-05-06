package user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListOfUsersTest {

    ListOfUsers lou = new ListOfUsers();
    
    @Test
    void testAddUser() {

        lou.addUser("ada", 90, 90);
        lou.addUser("alette", 66, 66);
        lou.addUser("elena", 88, 88);
        
        assertEquals(lou.getListOfUsers().size(),3);

    }
    
    @Test 
    void testAddFriend() {
        
        lou.addUser("ada", 90, 90);
        lou.addUser("alette", 66, 66);
        lou.addUser("elena", 88, 88);

        lou.addFriend(0, 1);
        lou.addFriend(2, 1);
        lou.addFriend(1, 0);
        
        assertEquals(6, lou.getGraphOfConnections().getGOC().edgeCount());
        

    }

    
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

        goc.addNewFriend(lou.getUser(0), lou.getUser(1), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(0), lou.getUser(2), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(1), lou.getUser(3), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(2), lou.getUser(3), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(5), lou.getUser(3), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(6), lou.getUser(3), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(7), lou.getUser(3), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(8), lou.getUser(3), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(9), lou.getUser(3), lou.getUserLocationMap());

        assertEquals(3, lou.getFriendRecommondation(1, 3).size());

        assertEquals(4, lou.getFriendRecommondation(1, 4).size());
    }


}
