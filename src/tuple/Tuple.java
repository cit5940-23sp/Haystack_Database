package tuple;

public interface Tuple {
    
    /**
     * Get the first item in the tuple 
     * @return first item in the tuple 
     */
    int getLeft();
    
    /**
     * Get the second item in the tuple 
     * @return second item in the tuple 
     */
    int getRight();

    /**
     * Set first item in the tuple 
     * @param leftInput
     */
    void setLeft(int leftInput);
    
    /**
     * Set second item in the tuple 
     * @param rightInput
     */
    void setRight(int rightInput);
    
    
}
