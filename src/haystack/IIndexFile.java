package haystack;

import java.util.HashMap;
import java.util.Map;

import photo.IPhoto;

public interface IIndexFile {
    int HMMaxSize = 1000;
    //private List<Map<String, ?>> indexFile;
    
    /**
     * Add a new index record 
     */
    void addIndex(IndexKey newIndexKey, IndexVal newIndexVal);

    /**
     * Get the flag from the key to check if the photo has been deleted
     */
    Map<Integer, Integer> getFlags(IndexKey k);   
    
    /**
     * Get the offset from the key
     */
    long getOffset(IndexKey k);
    
    /**
     * Get the size from the key
     */
    long getSize(IndexKey k);
    
    /**
     * Get the byte[] for the photo from the key
     */
    byte[] getPhoto(int key, int alternateKey);
    
    /**
     * Update the flags to delete:1 from the key
     */
    void deletePhoto(int key, int alternateKey);
    
    /**
     * Add the photo in haystack
     */
    int addPhoto(Photo inputPhoto);
    
    /**
     * Update the photo in different haystack
     */
    void updatePhoto(int key, int alternateKey);
    
    /**
     * Update the photo in the same haystack
     */
    void updatePhotoSame(int key, int alternateKey, Photo inputPhoto);

    /**
     * Move all not deleted photo in the next database
     */
    int compress(IndexFile newIndexFile);



}