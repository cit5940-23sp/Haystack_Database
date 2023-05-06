package user;

public interface IUserPhotoNode {


    /**
     * Get key
     * 
     * @return key
     */
    int getKey();

    /**
     * Get alternateKey
     * 
     * @return alternateKey
     */
    int getAlternateKey();

    /**
     * Get filePath
     * 
     * @return filePath
     */
    String getFilename();

    /**
     * Set filePath
     */
    void setFilename(String filePath);

    /**
     * Set private photo based on decision
     * 
     * @param decision
     */
    void setPrivatePhoto(boolean decision);

    /**
     * Get private settings of photo
     * 
     * @return
     */
    boolean getPrivateSettings();
}
