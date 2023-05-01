package haystack;

import java.util.List;
import java.util.Map;


public interface IListOfHaystacks {

//    public static final List<Map<String, ?>> listOfHaystacks;
    
    /**
     * Create a new haystack 
     */
    public void createNewHaystack();
    
    /**
     * Compaction of database by removing deleted photos 
     */
    public void compressHaystacks();
    
    public int assignHaystack(Photo inputPhoto);
    
    public List<Integer> addPhotoToHaystack(Photo inputPhoto);
    
    int updatePhotoInHaystack(Photo photoToUpdate, int key, int alternateKey, int haystackID);
    
    void deletePhotoFromHaystack(int key, int alternateKey, int haystackID);
}
