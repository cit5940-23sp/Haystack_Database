package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import graph.Coordinates;
import graph.GraphL;
import haystack.ListOfHaystacks;
import haystack.Photo;
import photo.IPhoto;


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
        
        List<Integer> returnVal = loh.addPhotoToHaystack(photoToAdd);
        
        int haystackID = returnVal.get(0);
        int key = returnVal.get(1);
        int alternateKey = returnVal.get(2);

        UserPhotoNode upn = new UserPhotoNode(haystackID, key, alternateKey);
        System.out.println("Key added in upn: " + upn.getKey());
        System.out.println("AltKey added in upn: " + upn.getAlternateKey());
        userPhotoList.addPhotoToUserList(upn);

        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void getPhoto(int idx, ListOfHaystacks loh) {
        
        UserPhotoList upl = getUserPhotoList();
        
        UserPhotoNode upn = upl.getPhoto(idx);
        
        int key = upn.getKey();
        int alternateKey = upn.getAlternateKey();
        int haystackID = upn.getHaystackID();
        
        byte[] imageByte = loh.getPhotoFromHaystack(key, alternateKey, haystackID);
        
        IPhoto.bytesToImage(imageByte);
        
    }

    
    @Override 
    public void deletePhoto(int idx, ListOfHaystacks loh) {
        
        UserPhotoList upl = getUserPhotoList();
        
        UserPhotoNode upn = upl.getPhoto(idx);
        
        int key = upn.getKey();
        int alternateKey = upn.getAlternateKey();
        int haystackID = upn.getHaystackID();
        
        loh.deletePhotoFromHaystack(key, alternateKey, haystackID);
        
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
