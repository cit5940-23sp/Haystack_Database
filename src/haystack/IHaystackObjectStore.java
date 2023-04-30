package haystack;
import photo.IPhoto;

public interface IHaystackObjectStore {

    //private int haystackID; 
    //private List<String> haystackObject; 
    public static final int MAXIMUM_BYTES = Integer.MAX_VALUE; 
    
    /**
     * Write to the end of disk file
     * @param newPhoto
     * @return offset (starting of the newly appended file)
     */
    public long appendPhoto(Photo newPhoto);
    
    /**
     * Retrieve photo from current haystack using offset.
     * @param offset file position in haystack
     * @return 0 if photo is found and accessible,
     * return -1 if photo is not found or not accessible
     */
    public int getPhoto(int offset); 
    
    /**
     * Delete photo by changing the flag
     * @param offset photo position in haystack
     * @return -1 if fail to delete(photo not found, invalid offset/magic number), return 0 on success
     */
    public int deletePhoto(int offset);
    
//    /**
//     * Set the photo as public or private 
//     */
//    void setPublicPrivate(int offset);
    
    
}
