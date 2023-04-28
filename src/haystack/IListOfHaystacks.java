package haystack;

import java.util.List;

public interface IListOfHaystacks {

    public static final List<HaystackObjectStore> listOfHaystacks = null;
    
    /**
     * Create a new haystack 
     */
    void createNewHaystack();
    
    /**
     * Compaction of database by removing deleted photos 
     */
    void compressHaystacks();
    
}
