package haystack;
import photo.IPhoto;

public interface IHaystackObjectStore {

    //private int haystackID; 
    //private List<String> haystackObject; 
    public static final int MAXIMUM_CAPACITY = 200; 
    
    /**
     * Add a new photo into the haystack 
     */
    public void appendPhoto(Photo newPhoto);
    
    /**
     * Retrieve photo from current haystack using offset.
     * @param offset file position in haystack
     * @return 0 if photo is found and accessible,
     * return 1 if photo is not found or not accessible
     */
    public int getPhoto(int offset); 
    
    /**
     * Delete photo by changing the flag
     */
    public void deletePhoto(int offset);
    
//    /**
//     * Set the photo as public or private 
//     */
//    void setPublicPrivate(int offset);
    
    
}
