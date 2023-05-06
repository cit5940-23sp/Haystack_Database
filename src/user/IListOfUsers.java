package user;

import java.util.List;
import java.util.TreeMap;
import tuple.DistUser;

public interface IListOfUsers {

    /**
     * Add new user
     * 
     * @param userName
     * @param latitude
     * @param longitude
     */
    int addUser(String userName, int latitude, int longitude);

    /**
     * Get User
     * 
     * @param uniqueUserID
     * @return User object
     */
    User getUser(int uniqueUserID);
    
    /**
     * Adds a friendship bond between two users
     * @param uniqueFriendID
     * @param requestUserID
     */
    void addFriend(int uniqueFriendID, int requestUserID);

    /**
     * Get graphOfConnections
     * 
     * @return graphOfConnections
     */
    UserGraph getGraphOfConnections();

    /**
     * Get userLocationMap
     * 
     * @return userLocationMap
     */
    UserLocationMap getUserLocationMap();

    /**
     * Gets list of users 
     * @return list of users 
     */
    TreeMap<Integer, User> getListOfUsers();
    

    /**
     * Gets recommendation of friends based on friends of friends and location
     * 
     * @param uniqueUserID
     * @return priorityqueue with top three recommendation of friends based on
     *         location
     */
    List<DistUser> getFriendRecommondation(int uniqueUserID, int numOfRec);
}
