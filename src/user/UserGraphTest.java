package user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import graph.GraphL;


class UserGraphTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }

    @Test
    void testAddNewUser() {

        UserGraph goc = new UserGraph();

        goc.addNewUser();

        GraphL graphL = goc.getGOC();

        assertEquals(1, graphL.nodeCount());

    }

    @Test
    void testAddNewFriend() {

        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser("Amy", 90, 90);
        lou.addUser("Tom", 70, 70);

        lou.addFriend(0, 1);

        assertEquals(2, lou.getGraphOfConnections().getGOC().edgeCount());


    }

    @Test
    void testGetFriends() {
        
        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser("Amy", 90, 90);
        lou.addUser("Tom", 88, 88);

        lou.addFriend(0, 1);

        assertEquals(1, lou.getGraphOfConnections().getFriends(0).length);

    }

  
}
