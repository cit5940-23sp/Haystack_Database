package user;

import java.awt.Image;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;

import haystack.ListOfHaystacks;
import haystack.Photo;
import photo.IPhoto;
import photo.PhotoDisplay;
import tuple.Coordinates;

public class User implements IUser {

    private int uniqueUserID;
    private String userName;
    private UserPhotoList userPhotoList;
    private HashSet<User> friendsList;
    private Coordinates addressCoor;

    /**
     * Constructor for a User object.
     * 
     * @param userName,     which is an input from User
     * @param uniqueUserID, which is generated systematically
     * @param latitude,     which is an input from User
     * @param longitude,    which is an input from User
     */
    public User(String userName, int uniqueUserID, 
            int latitude, int longitude) {

        // initialize unique user ID and username
        this.uniqueUserID = uniqueUserID;
        this.userName = userName;

        // initialize empty photo list and friends list
        setUserPhotoList(new UserPhotoList());
        friendsList = new HashSet<User>();

        // initialize the address coordinates of User
        addressCoor = new Coordinates(latitude, longitude);

    }

    @Override
    public UserPhotoList getUserPhotoList() {
        return userPhotoList;
    }

    @Override
    public boolean addPhoto(String filePath, ListOfHaystacks loh, boolean privatePhoto) {

        // create photo object to be appended
        Photo photoToAdd = new Photo(filePath);

        // check photo size
        if (photoToAdd.getSize() > IPhoto.MAXIMUM_BYTES_DATA) {
            return false;
        }

        // add photo to list of haystacks and get return values
        List<Integer> returnVal = loh.addPhotoToHaystack(photoToAdd);

        // extract return values
        int key = returnVal.get(1);
        int alternateKey = returnVal.get(2);

        // create a new user photo node with return values
        UserPhotoNode upn = new UserPhotoNode(key, alternateKey, filePath, privatePhoto);

        // add user photo node into userPhotoList
        getUserPhotoList().addPhotoToUserList(upn);

        return true;

    }

    private boolean isFriend(User requestUser) {

        if (requestUser.getUniqueUserID() == uniqueUserID) {
            return true;
        }

        if (friendsList.contains(requestUser)) {
            return true;
        }

        return false;

    }

    @Override
    public Image getPhoto(int key, ListOfHaystacks loh, User requestUser) {

        boolean showIfPrivate = isFriend(requestUser);

        // get user photo node from user photo list
        UserPhotoNode upn = getUserPhotoList().getPhoto(key);

        if (upn == null) {
            System.out.println("Image cannot be found: Invalid user ownership.");
            return null;
        }

        if (upn.getPrivateSettings()) {
            if (!showIfPrivate) {
                System.out.println("Private photo, for friends only.");
                return null;
            }
        }

        // get alternate key and haystackID
        int alternateKey = upn.getAlternateKey();

        // get bytes of the photo from list of haystacks
        byte[] imageByte = loh.getPhotoFromHaystack(key, alternateKey);

        // if byte array is empty, notify that image cannot be found
        if (imageByte == null) {
            System.out.println("Image cannot be found: Photo no longer available.");
            return null;
        }

        // convert byte array into Image and return to caller
        Image returnImg = IPhoto.bytesToImage(imageByte, key);

        PhotoDisplay m = new PhotoDisplay(returnImg);
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(1000, 1000);
        f.setVisible(true);

        return returnImg;

    }

    public Photo getPhoto(int key, ListOfHaystacks loh) {
        
        // get user photo node from user photo list
        UserPhotoNode upn = getUserPhotoList().getPhoto(key);
        System.out.println("------" +  key);
        
        // if photo does not belong to user, print message
        if (upn == null) {
            System.out.println("Image cannot be found: Invalid user ownership.");
            return null;
        }

        // get alternate key and haystackID
        int alternateKey = upn.getAlternateKey();

        // get bytes of the photo from list of haystacks
        byte[] imageByte = loh.getPhotoFromHaystack(key, alternateKey);

        // if byte array is empty, notify that image cannot be found
        if (imageByte == null) {
            System.out.println("Image cannot be found: Photo no longer available.");
            return null;
        }

        Photo resPhoto = new Photo(imageByte, key, alternateKey);
        return resPhoto;
    }

    @Override
    public void displayPhotoList() {

        // get all non-deleted photos of user
        List<UserPhotoNode> listOfPhotoNodes = getUserPhotoList().getAllPhotos();

        // go through the list of photos and print available photos
        for (int i = listOfPhotoNodes.size() - 1; i > -1; i--) {
            UserPhotoNode curNode = listOfPhotoNodes.get(i);
            System.out.print(curNode.getKey() + ": ");
            String[] split = curNode.getFilename().split("/");
            String ans = split[split.length - 1];

            System.out.println(ans);
        }
    }

    @Override
    public void deletePhoto(int key, ListOfHaystacks loh) {

        
        UserPhotoList upl = getUserPhotoList();
        
        // get photo from user photo list using key
        UserPhotoNode upn = upl.getPhoto(key);

        if (upn == null) {
            System.out.println("This is not your photo. You cannot delete it!");
            return;
        }

        // get alternate key and haystackID
        int alternateKey = upn.getAlternateKey();

        // delete photo from list of haystacks
        loh.deletePhotoFromHaystack(key, alternateKey);
        
        upl.deletePhotoFromUserPhotoList(upn);

    }

    @Override
    public void updatePhoto(String filePath, int key, ListOfHaystacks loh) {

        // create photo object to be appended
        Photo photoToUpdate = new Photo(filePath);

        // get photo from user photo list using key
        UserPhotoNode upn = getUserPhotoList().getPhoto(key);

        // get alternateKey and haystackID
        int alternateKey = upn.getAlternateKey();

        // update photo in list of haystack, getting the new haystackID
        loh.updatePhotoInHaystack(photoToUpdate, key, alternateKey);

        // reassign filename of user photo node
        upn.setFilename(filePath);

    }

    
    @Override
    public void addFriend(User friend) {
        // TODO Auto-generated method stub
 
        friendsList.add(friend);

    }

    public HashSet<User> getUserFriendsList() {
        return friendsList;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public int getUniqueUserID() {

        return this.uniqueUserID;
    }

    @Override
    public HashSet<User> getFriendsList() {

        return this.friendsList;
    }

    @Override
    public Coordinates getUserCoor() {
        return addressCoor;
    }

    public void setUserPhotoList(UserPhotoList userPhotoList) {
        this.userPhotoList = userPhotoList;
    }

}
