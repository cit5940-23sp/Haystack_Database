package user;

import java.util.HashMap;
import java.util.Map;

public class UserPhotoNode implements IUserPhotoNode{

    private int haystackID; 
    private int key; 
    private int alternateKey; 
    private int deleted;
    private String filePath;
    Map<String, Integer> metaDataMap;
    
    /**
     * Construct a new UserPhotoNode to be added into UserPhotoList 
     * @param haystackID
     * @param key
     * @param alternateKey
     */
    public UserPhotoNode(int haystackID, int key, int alternateKey, String filePath) {
        
        this.haystackID = haystackID;
        this.key = key;
        this.alternateKey = alternateKey;
        this.deleted = 0;
        this.filePath = filePath;
        
        metaDataMap = new HashMap<String, Integer>();
        
        this.metaDataMap.put("HaystackID", haystackID);
        this.metaDataMap.put("Key", key);
        this.metaDataMap.put("AlternateKey", alternateKey);
        this.metaDataMap.put("Deleted", deleted);
        
        
    }
    
    /**
     * Get all metadata 
     */
    @Override
    public Map<String, Integer> getMetaData() {
        // TODO Auto-generated method stub
        return metaDataMap;
    }
    
    /**
     * Get haystackID 
     * @return haystackID
     */
    public int getHaystackID() {
        return haystackID;
    }
    
    /**
     * Get key
     * @return key
     */
    public int getKey() {
        return key;
    }
    
    /**
     * Get alternateKey 
     * @return alternateKey 
     */
    public int getAlternateKey() {
        return alternateKey;
    }

    
    
    /**
     * Get filePath 
     * @return filePath
     */
    public String getFilename() {
        return filePath;
    }
    
    
    /**
     * Get filePath 
     * @return filePath
     */
    public void setFilename(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Get alternateKey 
     * @return alternateKey 
     */
    public int getDeleted() {
        return deleted;
    }
    
    /**
     * Set photo as deleted
     *  
     */
    public void setDeleted() {
        this.deleted = 1;
    }
    
    /**
     * Set haystackID
     *  
     */
    public void setHaystackID(int haystackID) {
        this.haystackID = haystackID;
    }
}
