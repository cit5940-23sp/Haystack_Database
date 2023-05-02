package user;

import java.util.HashSet;
import java.util.PriorityQueue;

import graph.DistUser;

public interface IUserGraph {

    void addNewUser();
    
    int[] getFriends(int uniqueUserID);
    
    PriorityQueue<DistUser> getFriendsOfFriends(int uniqueUserID, 
            UserLocationMap userMap, ListOfUsers lou);
    
    void getFriendRecommondation(int uniqueUserID, ListOfUsers lou, UserLocationMap userMap);
    
    void addNewFriend(User curUser, User newFriend, UserLocationMap userMap);
}
