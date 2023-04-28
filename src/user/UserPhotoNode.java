package user;

import java.util.Map;

public class UserPhotoNode implements IUserPhotoNode{

    private int haystackID; 
    private int key; 
    private int alternateKey; 
    Map<String, Integer> metaDataMap;
    
    /**
     * Construct a new UserPhotoNode to be added into UserPhotoList 
     * @param haystackID
     * @param key
     * @param alternateKey
     */
    public UserPhotoNode(int haystackID, int key, int alternateKey) {
        
        this.haystackID = haystackID;
        this.key = key;
        this.alternateKey = alternateKey;
        
        this.metaDataMap.put("HaystackID", haystackID);
        this.metaDataMap.put("Key", key);
        this.metaDataMap.put("AlternateKey", alternateKey);
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

}
