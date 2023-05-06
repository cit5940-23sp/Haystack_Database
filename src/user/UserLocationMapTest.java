package user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tuple.Coordinates;

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
        
        ListOfUsers lou = new ListOfUsers();
        
        lou.addUser("Alette", 90, 90);
        lou.addUser("Elena", 66, 66);
        lou.addUser("Ada", 88, 88);

        UserLocationMap ulm = lou.getUserLocationMap();
        
        assertEquals(ulm.distBetweenUsers(lou.getUser(0), lou.getUser(1)), 33);
        assertEquals(ulm.distBetweenUsers(lou.getUser(0), lou.getUser(2)), 2);
        assertEquals(ulm.distBetweenUsers(lou.getUser(3), lou.getUser(2)), -1);

    }

    @Test
    void getClosestUsers() {

        ListOfUsers lou = new ListOfUsers();
        lou.addUser("Alette", 90, 90);
        lou.addUser("Elena", 66, 66);
        lou.addUser("Ada", 88, 88);

        UserLocationMap userLM = lou.getUserLocationMap();

        assertEquals(2, userLM.getClosestUsers(90, 70, lou, lou.getUser(0)).size());

    }

}
