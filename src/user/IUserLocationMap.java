package user;

import java.util.HashSet;
import java.util.PriorityQueue;

import graph.Coordinates;
import graph.DistUser;

public interface IUserLocationMap {

    public static final int LATITUDE_ADD = 90;
    public static final int LONGITUDE_ADD = 180;
    
    /**
     * Adds a new user into the 2D array
     * 
     * @param uniqueUserID
     * @param addressCoor
     */
    void addUser(int uniqueUserID, Coordinates addressCoor);

    /**
     * Get users in a location
     * 
     * @param latitude
     * @param longitude
     * @return a hashset of users in a particular location
     */
    HashSet<Integer> getUsersInLocation(int latitude, int longitude);

    /**
     * Calculate distance between two users
     * 
     * @param user1
     * @param user2
     * @return distance
     */
    int distBetweenUsers(User user1, User user2);

    PriorityQueue<DistUser> getClosestUsers(int latitude,
            int longitude, ListOfUsers lou, User requestUser);

}
