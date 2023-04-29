package user;

import java.util.HashSet;

import graph.Coordinates;

public interface IUserLocationMap {

    void addUser(int uniqueUserID, Coordinates addressCoor);

    HashSet<Integer> getUsersInLocation(int latitude, int longitude);
    
    double distBetweenUsers(int firstLat, int firstLong, int secondLat, int secondLong);
    
}
