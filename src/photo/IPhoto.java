package photo;

public interface IPhoto {
    
    //private int headerMagicNumber 
    //private int cookie 
    //private int key 
    //private int alternateKey 
    //private Map<String, Integer> flags;
    //private int size; 
    //private bytes[] data;
    //private int footerMagicNumber;
    //private bytes[] padding; 
    
    public static final int MAXIMUM_BYTES_DATA = 10000; 
    public static final int headerMagicNumber = 123456789;
    public static final int footerMagicNumber = 987654321;
    
    /**
     * Function to generate key and alternate key 
     */
    void generateKeys();
    
    /**
     * Delete photo by setting flags "Deleted" as "1" 
     */
    void deletePhoto();
    
    void displayPhoto(); 
    

}
