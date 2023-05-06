package user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
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

}
