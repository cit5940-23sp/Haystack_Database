package user;

import java.util.HashSet;
import java.util.PriorityQueue;

import graph.DistUser;

public interface IUserGraph {

    void addNewUser();
    
    int[] getFriends(int uniqueUserID);
    
    PriorityQueue<DistUser> getFriendsOfFriends(int uniqueUserID);
    
    void getFriendRecommondation(int uniqueUserID, ListOfUsers lou);
    
    void addNewFriend(User curUser, User newFriend, UserLocationMap userMap);
}
