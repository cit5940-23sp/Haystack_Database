package user;

import java.util.HashSet;

import photo.Photo;


public class User implements IUser {

   
  private int uniqueUserID;
  private String userName;
  private UserPhotoList userPhotoList; 
  private HashSet <User> friendsList;
    
    public User(String userName, int uniqueUserID) {
        
        this.uniqueUserID = uniqueUserID;
        this.userName = userName;
        userPhotoList = new UserPhotoList();
        friendsList = new HashSet<User>();
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
        
        int haystackID = photoToAdd.getHaystackID();
        int key = photoToAdd.getKey();
        int alternateKey = photoToAdd.getAlternateKey();
        
        UserPhotoNode upn = new UserPhotoNode(haystackID, key, alternateKey);
        userPhotoList.addPhotoToUserList(upn);
        
        loh.addPhoto(photoToAdd);
        
        // TODO Auto-generated method stub
        
    }

    public HashSet<User> getUserFriendsList() {
        // TODO Auto-generated method stub
        return null;
    }

}
