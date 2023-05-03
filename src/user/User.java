package user;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;

import graph.Coordinates;
import graph.GraphL;
import haystack.ListOfHaystacks;
import haystack.Photo;
import photo.IPhoto;
import photo.photoDisplay;


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

        //add user into userLocationMap 
        userLocationMap.addUser(uniqueUserID, addressCoor);
        
        //add user into graphOfConnections 
        graphOfConnections.addNewUser();
        
    }
    
    
    @Override
    public UserPhotoList getUserPhotoList() {
        // TODO Auto-generated method stub
        return userPhotoList;
    }

    @Override
    public void addPhoto(String filePath, ListOfHaystacks loh) {
        
        //create photo object to be appended 
        Photo photoToAdd = new Photo(filePath);
        
        //add photo to list of haystacks and get return values 
        List<Integer> returnVal = loh.addPhotoToHaystack(photoToAdd);
        
        //extract return values 
        int haystackID = returnVal.get(0);
        int key = returnVal.get(1);
        int alternateKey = returnVal.get(2);

        //create a new user photo node with return values 
        UserPhotoNode upn = new UserPhotoNode(key, alternateKey, filePath);

        //add user photo node into userPhotoList 
        userPhotoList.addPhotoToUserList(upn);
      
        System.out.println("Added into haystack: " + haystackID);
        
    }
    
    @Override
    public Image getPhoto(int key, ListOfHaystacks loh) {
        
        //get user photo node from user photo list 
        UserPhotoNode upn = userPhotoList.getPhoto(key);
        
        //get alternate key and haystackID 
        int alternateKey = upn.getAlternateKey();
        
        //get bytes of the photo from list of haystacks 
        byte[] imageByte = loh.getPhotoFromHaystack(key, alternateKey);
        
        //if byte array is empty, notify that image cannot be found 
        if (imageByte == null) {
            System.out.println("Image cannot be found.");
            return null;
        }
        
        //convert byte array into Image and return to caller 
        Image returnImg = IPhoto.bytesToImage(imageByte, key);
        
        photoDisplay m = new photoDisplay(returnImg);
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(1000, 1000);
        f.setVisible(true);
        
        return returnImg;
        
    }

    
    @Override
    public void displayPhotoList() {
        
        //get all non-deleted photos of user 
        List<UserPhotoNode> listOfPhotoNodes = userPhotoList.getAllPhotos();
        
        //go through the list of photos and print available photos 
        for (int i = listOfPhotoNodes.size()-1; i > -1; i--) {
            UserPhotoNode curNode = listOfPhotoNodes.get(i);
            System.out.print(curNode.getKey() + ": ");
            String split [] = curNode.getFilename().split("/");
            String ans = split[split.length-1];
            
            System.out.println(ans);
        }
    }
    
    @Override 
    public void deletePhoto(int key, ListOfHaystacks loh) {
        
        //get photo from user photo list using key 
        UserPhotoNode upn = userPhotoList.getPhoto(key);
        
        //get alternate key and haystackID 
        int alternateKey = upn.getAlternateKey();

        //delete photo from list of haystacks 
        loh.deletePhotoFromHaystack(key, alternateKey);
        
        //set the user photo node as deleted 
        upn.setDeleted();
        
    }
    
    
    @Override 
    public void updatePhoto(String filePath, int key, ListOfHaystacks loh) {
        
        //create photo object to be appended 
        Photo photoToUpdate = new Photo(filePath);
        
        //get photo from user photo list using key 
        UserPhotoNode upn = userPhotoList.getPhoto(key);
        
        //get alternateKey and haystackID 
        int alternateKey = upn.getAlternateKey();
        
        //update photo in list of haystack, getting the new haystackID 
        loh.updatePhotoInHaystack(photoToUpdate, key, alternateKey);
        
        
        //reassign filename of user photo node 
        upn.setFilename(filePath);
        
    }
   

    @Override
    public void addFriend(int uniqueFriendID, ListOfUsers lou) {
        // TODO Auto-generated method stub
        
        UserGraph goc = lou.getGraphOfConnections();
        UserLocationMap ulm = lou.getUserLocationMap();
        User curUser = lou.getUser(uniqueUserID);
        User friend = lou.getUser(uniqueFriendID);
        
        goc.addNewFriend(curUser, friend, ulm);
        friendsList.add(friend);
        friend.addFriendToFriendList(curUser);
    }

    @Override
    public void addFriendToFriendList(User friend) {
        friendsList.add(friend);
    }
    
    @Override
    public void deleteFriend(int uniqueFriendID) {
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
    
    @Override
    public Coordinates getUserCoor() {
        return addressCoor;
    }
    

}
