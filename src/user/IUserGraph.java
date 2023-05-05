package user;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import graph.DistUser;

public interface IUserGraph {

    /**
     * Adds a new user into the graph 
     */
    void addNewUser();
    
    /**
     * Gets the friends of a user 
     * @param uniqueUserID
     * @return an array of the user's friends 
     */
    int[] getFriends(int uniqueUserID);
    
    /**
     * Gets the list of friends of friends of a user 
     * @param uniqueUserID
     * @param userMap
     * @param lou
     * @return a list of Users
     */
    PriorityQueue<DistUser> getFriendsOfFriends(int uniqueUserID, 
            UserLocationMap userMap, ListOfUsers lou);
    
    /**
     * Gets recommendation of friends based on location 
     * @param uniqueUserID
     * @param lou
     * @param userMap
     * @return priorityqueue with top three recommendation of friends based on location 
     */
    List<DistUser> getFriendRecommondation(int uniqueUserID, ListOfUsers lou, UserLocationMap userMap, int numOfRec);
    
    /**
     * Add a new friend 
     * @param curUser
     * @param newFriend
     * @param userMap
     */
    void addNewFriend(User curUser, User newFriend, UserLocationMap userMap);
}
