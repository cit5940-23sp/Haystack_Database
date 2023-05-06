package user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graph.Coordinates;
import graph.DistUser;
import haystack.ListOfHaystacks;

class UserLocationMapTest {

    UserLocationMap ulm;
    List<UserPhotoNode> listOfPhotoNodes;
    Coordinates addressCoor;
    Coordinates addressCoor2;
    Coordinates addressCoor3;
    User curUser;
    User curUser1;
    User curUser2;

    @BeforeEach
    void setUp() throws Exception {

        ulm = new UserLocationMap();
        addressCoor = new Coordinates(90, 90);
        addressCoor2 = new Coordinates(30, 30);
        addressCoor3 = new Coordinates(90, 90);

    }

    @Test
    void testAddUserGetUsersInLocation() {

        ulm.addUser(0, addressCoor);
        ulm.addUser(1, addressCoor2);

        assertEquals(ulm.getUsersInLocation(90, 90).size(), 1);
        assertEquals(ulm.getUsersInLocation(30, 30).size(), 1);
        ulm.addUser(2, addressCoor3);
        assertEquals(ulm.getUsersInLocation(90, 90).size(), 2);

    }

    @Test
    void testDistBetweenUsers() {
        UserGraph goc = new UserGraph();
        curUser = new User("Alette", 0, 90, 90, ulm, goc);
        curUser1 = new User("Elena", 0, 66, 66, ulm, goc);
        curUser2 = new User("Ada", 0, 88, 88, ulm, goc);
        assertEquals(ulm.distBetweenUsers(curUser, curUser1), 33);
        assertEquals(ulm.distBetweenUsers(curUser, curUser2), 2);

    }

    @Test
    void getClosestUsers() {

        ListOfUsers lou = new ListOfUsers();
        lou.addUser("Alette", 90, 90);
        lou.addUser("Elena", 66, 66);
        lou.addUser("Ada", 100, 100);

        UserLocationMap userLM = lou.getUserLocationMap();

        assertEquals(2, userLM.getClosestUsers(90, 70, lou, lou.getUser(0)).size());

    }

}
