package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import graph.Coordinates;
import graph.GraphL;
import haystack.ListOfHaystacks;
import haystack.Photo;


public class User implements IUser {

   
  private int uniqueUserID;
  private String userName;
  private UserPhotoList userPhotoList; 
  private HashSet <User> friendsList;
  private Coordinates addressCoor;
    
    /**
     * Constructor for a User object.
     * @param userName, which is an input from User 
     * @param uniqueUserID, which is generated systematically 
     * @param latitude, which is an input from User
     * @param longitude, which is an input from User 
     */
    public User(String userName, int uniqueUserID, 
            int latitude, int longitude, 
            UserLocationMap userLocationMap, UserGraph graphOfConnections) {
        
        //initialize unique user ID and username 
        this.uniqueUserID = uniqueUserID;
        this.userName = userName;
        
        //initialize empty photo list and friends list 
        userPhotoList = new UserPhotoList();
        friendsList = new HashSet<User>();
        
        //initialize the address coordinates of User 
        addressCoor = new Coordinates(latitude, longitude);

        userLocationMap.addUser(uniqueUserID, addressCoor);
        graphOfConnections.addNewUser();
        
    }
    
    
    @Override
    public UserPhotoList getUserPhotoList() {
        // TODO Auto-generated method stub
        
        return userPhotoList;
    }

    @Override
    public void addFriend(int uniqueFriendID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteFriend(int uniqueFriendID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addPhoto(String filePath, ListOfHaystacks loh) {
        
        Photo photoToAdd = new Photo(filePath);
        
//        int haystackID = photoToAdd.getHaystackID();
        int key = photoToAdd.getKey();
        int alternateKey = photoToAdd.getAlternateKey();
        
        
        int haystackID = loh.addPhotoToHaystack(photoToAdd);

        UserPhotoNode upn = new UserPhotoNode(haystackID, key, alternateKey);
        userPhotoList.addPhotoToUserList(upn);

        // TODO Auto-generated method stub
        
    }

    public HashSet<User> getUserFriendsList() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        return this.userName;
    }


    @Override
    public int getUniqueUserID() {
        // TODO Auto-generated method stub
        return this.uniqueUserID;
    }


    @Override
    public HashSet<User> getFriendsList() {
        // TODO Auto-generated method stub
        return this.friendsList;
    }
    

}
