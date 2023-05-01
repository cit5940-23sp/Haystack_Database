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
    
    public int addPhotoToHaystack(Photo inputPhoto);
    
    void deletePhotoFromHaystack(int key, int alternateKey, int haystackID);
}
