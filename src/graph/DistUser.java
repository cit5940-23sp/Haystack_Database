package graph;

public class DistUser implements Tuple {

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
        // TODO Auto-generated method stub
        return distance;
    }

    @Override
    public int getRight() {
        // TODO Auto-generated method stub
        return userUniqueID;
    }

    @Override
    public void setLeft(int leftInput) {
        // TODO Auto-generated method stub
        this.distance = leftInput;
    }

    @Override
    public void setRight(int rightInput) {
        // TODO Auto-generated method stub
        this.userUniqueID = rightInput;
    }

}
