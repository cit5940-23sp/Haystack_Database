package tuple;

public class Coordinates implements Tuple {

    private int latitude;
    private int longitude;
    
    /**
     * Constructor for Coordinates 
     * @param latitude
     * @param longitude
     */
    public Coordinates(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int getLeft() {
        // TODO Auto-generated method stub
        return this.latitude;
    }

    @Override
    public int getRight() {
        // TODO Auto-generated method stub
        return this.longitude;
    }

    @Override
    public void setLeft(int latitude) {
        // TODO Auto-generated method stub
        this.latitude = latitude;
    }

    @Override
    public void setRight(int longitude) {
        // TODO Auto-generated method stub
        this.longitude = longitude;
    }
    
    
    
}
