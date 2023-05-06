package tuple;

public class DistUser implements ITuple {

    int distance; 
    int userUniqueID;
    
    /**
     * Constructor for DistUser
     * @param distance
     * @param userUniqueID
     */
    public DistUser(int distance, int userUniqueID) {
        
        this.distance = distance;
        this.userUniqueID = userUniqueID;
        
    }
    
    @Override
    public int getLeft() {
        return distance;
    }

    @Override
    public int getRight() {
        return userUniqueID;
    }

    @Override
    public void setLeft(int leftInput) {
        this.distance = leftInput;
    }

    @Override
    public void setRight(int rightInput) {
        this.userUniqueID = rightInput;
    }

}
