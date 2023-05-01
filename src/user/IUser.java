package user;

import java.awt.Image;
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
    
    Image getPhoto(int key, ListOfHaystacks loh);
    
    /**
     * Add a friend into User's friend list 
     */
    void addFriend(int uniqueFriendID);
    
    
    void deletePhoto(int key, ListOfHaystacks loh);
    
    void updatePhoto(String filePath, int key, ListOfHaystacks loh);

    void displayPhotoList();
    
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
