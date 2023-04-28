package user;

import java.util.HashSet;


public interface IUser {

// private int uniqueUserID;
// private String userName;
// private UserPhotoList userPhotoList; 
// private HashSet <User> friendsList;
    
    
    /**
     * Get User's photo list 
     * @return User's photo list 
     */
    UserPhotoList getUserPhotoList();
    

    /**
     * Get User's friend list 
     * @return User's friend list 
     */
    HashSet<User> getUserFriendsList();
    
    
    /**
     * Add a friend into User's friend list 
     */
    void addFriend(int uniqueFriendID);
    
    
    /**
     * Delete friend from User's friend list 
     */
    void deleteFriend(int uniqueFriendID);
    
    
    /**
     * User adds a new photo 
     */
    void addPhoto(String filePath);
    
    
}
