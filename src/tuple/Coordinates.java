package tuple;

public class Coordinates implements ITuple {

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
        return this.latitude;
    }

    @Override
    public int getRight() {
        return this.longitude;
    }

    @Override
    public void setLeft(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setRight(int longitude) {
        this.longitude = longitude;
    }
    
    
    
}
