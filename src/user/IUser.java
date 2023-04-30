package user;

import java.util.HashSet;

import haystack.ListOfHaystacks;


public interface IUser {


    /**
     * Get User's photo list 
     * @return User's photo list 
     */
    UserPhotoList getUserPhotoList();
    
    void addPhoto(String filePath, ListOfHaystacks loh);

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
    
    
//    /**
//     * User adds a new photo 
//     */
//    void addPhoto(String filePath, ListOfHaystacks loh);
    
    /**
     * Returns name of user  
     */
    String getUserName();

    /**
     * Returns unique userID of user 
     */
    int getUniqueUserID();
    
    /**
     * Returns friends list of user 
     */
    HashSet <User> getFriendsList();
    
}
