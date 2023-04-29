package user;

public interface IUserGraph {

    void addNewUser();
    
    void getFriends(int uniqueUserID);
    
    void getFriendsOfFriends(int uniqueUserID);
    
    void getFriendRecommondation(int uniqueUserID);
    
}
