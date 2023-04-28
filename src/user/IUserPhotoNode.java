package user;

import java.util.Map;

public interface IUserPhotoNode {

   //private int haystackID 
    //private int key 
    //private int alternateKey 
    //private int cookie 
    
    /**
     * Returns the metadata as a map of fields (key) and corresponding values (value)
     */
    Map<String, Integer> getMetaData();
    
    
}
