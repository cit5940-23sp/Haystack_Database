package user;

public class UserPhotoNode implements IUserPhotoNode {

    private int key;
    private int alternateKey;
    private String filePath;
    private boolean privatePhoto;

    /**
     * Construct a new UserPhotoNode to be added into UserPhotoList
     * 
     * @param haystackID
     * @param key
     * @param alternateKey
     */
    public UserPhotoNode(int key, int alternateKey, String filePath, boolean privatePhoto) {

        this.key = key;
        this.alternateKey = alternateKey;
        this.filePath = filePath;
        this.privatePhoto = privatePhoto;

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
    public void setPrivatePhoto(boolean decision) {
        this.privatePhoto = decision;
    }

    @Override
    public boolean getPrivateSettings() {
        return privatePhoto;
    }

}
