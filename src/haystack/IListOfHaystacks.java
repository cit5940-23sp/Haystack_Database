package haystack;

import java.util.List;
import java.util.Map;

public interface IListOfHaystacks {

//    public static final List<Map<String, ?>> listOfHaystacks;

    /**
     * Create a new haystack
     */
    public IndexFile createNewHaystack();

    /**
     * Compaction of database by removing deleted photos
     */
    public int compressHaystacks();

    /**
     * Assign a haystack for the new photo
     * 
     * @param inputPhoto
     * @return haystackID of assigned haystack
     */
    public int assignHaystack(Photo inputPhoto);

    /**
     * Add a photo into the next available haystack
     * 
     * @param inputPhoto
     * @return a list of outputs
     */
    public List<Integer> addPhotoToHaystack(Photo inputPhoto);

    /**
     * Update a photo in the haystack
     * 
     * @param photoToUpdate
     * @param key
     * @param alternateKey
     * @return haystackID of photo
     */
    int updatePhotoInHaystack(Photo photoToUpdate, int key, int alternateKey);

    /**
     * Delete photo from one of the haystack
     * 
     * @param key
     * @param alternateKey
     */
    void deletePhotoFromHaystack(int key, int alternateKey);

}
