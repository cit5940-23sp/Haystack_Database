package user;

import java.awt.Image;
import java.util.HashSet;

import graph.Coordinates;
import haystack.ListOfHaystacks;


public interface IUser {


    /**
     * Get User's photo list 
     * @return User's photo list 
     */
    UserPhotoList getUserPhotoList();
    
    /**
     * Add a photo into the haystack 
     * @param filePath - name of file 
     * @param loh - list of haystacks 
     */
    void addPhoto(String filePath, ListOfHaystacks loh);

    /**
     * Get a photo from the haystack 
     * @param key - key of photo 
     * @param loh - list of haystacks 
     * @return an Image of the photo requested; null if photo 
     * cannot be found 
     */
    Image getPhoto(int key, ListOfHaystacks loh);
       
    /**
     * Delete photo from the haystack
     * @param key - key of photo 
     * @param loh - list of haystacks 
     */
    void deletePhoto(int key, ListOfHaystacks loh);
    
    /**
     * Update photo in haystack with new photo 
     * @param filePath - file of new image 
     * @param key - key of photo 
     * @param loh - list of haystacks 
     */
    void updatePhoto(String filePath, int key, ListOfHaystacks loh);

    /**
     * Display all photos of User through a print statement 
     */
    void displayPhotoList();

    /**
     * Get User's friend list 
     * @return User's friend list 
     */
    HashSet<User> getUserFriendsList();
    
    /**
     * Add a friend into User's friend list 
     */
    void addFriend(int uniqueFriendID, ListOfUsers lou);
    
    /**
     * Add a friend to a User's friend list 
     * @param friend
     */
    void addFriendToFriendList(User friend);
    
    /**
     * Delete friend from User's friend list 
     */
    void deleteFriend(int uniqueFriendID);
    
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
    
    /**
     * Get user's Coordinates on UserLocationMap
     * @return
     */
    public Coordinates getUserCoor();
    
}
