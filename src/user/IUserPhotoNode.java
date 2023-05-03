package user;

import java.util.Map;

public interface IUserPhotoNode {

    /**
     * Returns the metadata as a map of fields (key) and corresponding values (value)
     */
    Map<String, Integer> getMetaData();
    
    /**
     * Get key
     * @return key
     */
    int getKey();
    
    /**
     * Get alternateKey 
     * @return alternateKey 
     */
    int getAlternateKey();
    
    
    /**
     * Get filePath 
     * @return filePath
     */
    String getFilename();
    
    
    /**
     * Get filePath 
     * @return filePath
     */
    void setFilename(String filePath);
    
    /**
     * Get alternateKey 
     * @return alternateKey 
     */
    int getDeleted();
    
    /**
     * Set photo as deleted
     *  
     */

    void setDeleted();
}
