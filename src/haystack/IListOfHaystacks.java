package haystack;

public interface IListOfHaystacks {

    List <HaystackObjectStore> listOfHaystacks;
    
    /**
     * Create a new haystack 
     */
    void createNewHaystack();
    
    /**
     * Compaction of database by removing deleted photos 
     */
    void compressHaystacks();
    
}
