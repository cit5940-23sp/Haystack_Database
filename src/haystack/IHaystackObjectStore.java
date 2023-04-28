package haystack;
import photo.IPhoto;

public interface IHaystackObjectStore {

    //private int haystackID; 
    //private List<String> haystackObject; 
    public static final int MAXIMUM_CAPACITY = 200; 
    
    /**
     * Add a new photo into the haystack 
     */
    void appendPhoto(Photo newPhoto);
    
    /**
     * Retrieve photo from current haystack using offset  
     */
    void getPhoto(int offset); 
    
    /**
     * Delete photo by changing the flag
     */
    void deletePhoto(int offset);
    
    /**
     * Set the photo as public or private 
     */
    void setPublicPrivate(int offset);
    
    
}
