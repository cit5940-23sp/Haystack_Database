package user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import graph.DistUser;
import graph.GraphL;
import haystack.ListOfHaystacks;

class UserGraphTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }

    @Test
    void testAddNewUser() {
//        
        UserGraph goc = new UserGraph();

        goc.addNewUser();

        GraphL graphL = goc.getGOC();

        assertEquals(1, graphL.nodeCount());

    }

    @Test
    void testAddNewFriend() {

        UserGraph goc = new UserGraph();

        UserLocationMap ulm = new UserLocationMap();

        User curUser = new User("Amy", 0, 90, 90, ulm, goc);

        User newFriend = new User("Tom", 1, 100, 100, ulm, goc);

        goc.addNewFriend(curUser, newFriend, ulm);

        GraphL graphL = goc.getGOC();

        assertEquals(2, graphL.edgeCount());
    }

    @Test
    void testGetFriends() {

        UserGraph goc = new UserGraph();

        UserLocationMap ulm = new UserLocationMap();

        User curUser = new User("Amy", 0, 90, 90, ulm, goc);

        User newFriend = new User("Tom", 1, 100, 100, ulm, goc);

        goc.addNewFriend(curUser, newFriend, ulm);

        assertEquals(1, goc.getFriends(0).length);

    }

    @Test
    void testGetFriendsOfFriends() {

        ListOfUsers lou = new ListOfUsers();

        lou.addUser("Amy", 30, 90);
        lou.addUser("Tom", 30, 100);
        lou.addUser("Jim", 90, 180);
        lou.addUser("Kim", 10, 180);
        lou.addUser("Tim", 20, 180);
        lou.addUser("Jane", 30, 70);

        UserGraph goc = lou.getGraphOfConnections();
        UserLocationMap ulm = lou.getUserLocationMap();

        goc.addNewFriend(lou.getUser(0), lou.getUser(1), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(1), lou.getUser(2), lou.getUserLocationMap());
        goc.addNewFriend(lou.getUser(1), lou.getUser(3), lou.getUserLocationMap());

        goc.getFriendsOfFriends(0, ulm, lou);

        assertEquals(2, goc.getFriendsOfFriends(0, ulm, lou).size());

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

        assertEquals(3, goc.getFriendRecommondation(1, lou, ulm, 3).size());

        assertEquals(4, goc.getFriendRecommondation(1, lou, ulm, 4).size());
    }

}
