package user;

import java.util.List;

public interface IUserPhotoList {

    /**
     * Adds a UserPhotoNode into UserPhotoList 
     * @param uniqueUserID - User's uniqueUser ID used to generate key and alternate key  
     * @param cookie 
     * @param haystackID - haystack that the photo is saved in 
     */
    void addPhotoToUserList(UserPhotoNode upn);
    
    
    /**
     * Returns number of photos in UserPhotoList 
     */
    int numberOfPhotos();
    
    /**
     * Traverse through the masterPhotoList and return all photos as a list 
     */
    UserPhotoNode getPhoto(int index);
    
    /**
     * Get all photos of a user 
     * @return a list of photos of a user 
     */
    List<UserPhotoNode> getAllPhotos();
    
    /**
     * Marks a photo in UserPhotoList as deleted and increase deletedCount
     * @param upn
     */
    void deletePhotoFromUserPhotoList(UserPhotoNode upn);
    
}
