package user;

import java.util.HashSet;

import graph.Coordinates;

public interface IUserLocationMap {

    void addUser(int uniqueUserID, Coordinates addressCoor);

    HashSet<Integer> getUsersInLocation(int latitude, int longitude);
    
    int distBetweenUsers(User user1, User user2);
    
}
