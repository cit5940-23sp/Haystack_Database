package user;

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
     * 
     * @param uniqueUserID
     * @return an array of the user's friends
     */
    int[] getFriends(int uniqueUserID);


    /**
     * Add a new friend
     * 
     * @param curUser
     * @param newFriend
     * @param userMap
     */
    void addNewFriend(User curUser, User newFriend, UserLocationMap userMap);
}
