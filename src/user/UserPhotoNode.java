package user;

import java.util.HashMap;
import java.util.Map;

public class UserPhotoNode implements IUserPhotoNode{

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
    public UserPhotoNode(int key, int alternateKey, String filePath) {
        
        this.key = key;
        this.alternateKey = alternateKey;
        this.deleted = 0;
        this.filePath = filePath;
        
        metaDataMap = new HashMap<String, Integer>();
        
        this.metaDataMap.put("Key", key);
        this.metaDataMap.put("AlternateKey", alternateKey);
        this.metaDataMap.put("Deleted", deleted);

    }
    

    @Override
    public Map<String, Integer> getMetaData() {
        // TODO Auto-generated method stub
        return metaDataMap;
    }
    
    
    @Override 
    public int getKey() {
        return key;
    }
    
    @Override 
    public int getAlternateKey() {
        return alternateKey;
    }

    @Override 
    public String getFilename() {
        return filePath;
    }
    
    @Override 
    public void setFilename(String filePath) {
        this.filePath = filePath;
    }
    
    @Override 
    public int getDeleted() {
        return deleted;
    }
    
    @Override 
    public void setDeleted() {
        this.deleted = 1;
    }   

}
