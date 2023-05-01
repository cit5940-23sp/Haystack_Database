package user;

import java.awt.Image;
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

        UserPhotoNode upn = new UserPhotoNode(haystackID, key, alternateKey, filePath);

        userPhotoList.addPhotoToUserList(upn);

        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Image getPhoto(int key, ListOfHaystacks loh) {
        
        UserPhotoNode upn = userPhotoList.getPhoto(key);
        
        int alternateKey = upn.getAlternateKey();
        int haystackID = upn.getHaystackID();
        
        byte[] imageByte = loh.getPhotoFromHaystack(key, alternateKey, haystackID);
        
        Image returnImg = IPhoto.bytesToImage(imageByte);
        
        return returnImg;
        
    }

    
    @Override
    public void displayPhotoList() {
        
        List<UserPhotoNode> listOfPhotoNodes = userPhotoList.getAllPhotos();
        
        for (int i = listOfPhotoNodes.size(); i > -1; i--) {
            UserPhotoNode curNode = listOfPhotoNodes.get(i);
            System.out.print(curNode.getKey() + ": ");
            System.out.println(curNode.getFilename());
        }
    }
    
    @Override 
    public void deletePhoto(int key, ListOfHaystacks loh) {
        
        UserPhotoNode upn = userPhotoList.getPhoto(key);
        
        int alternateKey = upn.getAlternateKey();
        int haystackID = upn.getHaystackID();
        
        loh.deletePhotoFromHaystack(key, alternateKey, haystackID);
        
        upn.setDeleted();
        
    }
    
    
    @Override 
    public void updatePhoto(String filePath, int key, ListOfHaystacks loh) {
        
        Photo photoToAdd = new Photo(filePath);
        
        UserPhotoNode upn = userPhotoList.getPhoto(key);
        
        int alternateKey = upn.getAlternateKey();
        int haystackID = upn.getHaystackID();
        
        int newHaystackID = loh.updatePhotoFromHaystack(key, alternateKey, haystackID);
        
        upn.setHaystackID(newHaystackID);
        upn.setFilename(filePath);
        
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
