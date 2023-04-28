package user;

import java.util.List;

import haystack.Photo;

public interface IUserPhotoList {

// private List<UserPhotoNode> masterPhotoList;
// int deletedPhotoCount;     
    
    /**
     * Adds a UserPhotoNode into UserPhotoList 
     * @param uniqueUserID - User's uniqueUser ID used to generate key and alternate key  
     * @param cookie 
     * @param haystackID - haystack that the photo is saved in 
     */
    void addPhotoToUserList(User uniqueUserID, int key, int alternateKey, 
            int cookie, int haystackID);
    
    void deletePhotoFromUserList(User uniqueUserID, int key, int alternateKey);
    
    void updatePhotoInUserList(User uniqueUserID, int key, int alternateKey);
    
    /**
     * Returns number of photos in UserPhotoList 
     */
    int numberOfPhotos();
    
    /**
     * Traverse through the masterPhotoList and return all photos as a list 
     */
    List<Photo> getPhotos();
    
    
}
