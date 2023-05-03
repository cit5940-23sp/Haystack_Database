package user;

import graph.Coordinates;

public interface IListOfUsers {

    /**
     * Add new user
     * @param userName
     * @param latitude
     * @param longitude
     */
     void addUser(String userName, int latitude, int longitude);
    
    /**
     * Get User 
     * @param uniqueUserID
     * @return User object 
     */
     User getUser(int uniqueUserID);
    
    /**
     * Get graphOfConnections
     * @return graphOfConnections
     */
     UserGraph getGraphOfConnections();
    
    /**
     * Get userLocationMap
     * @return userLocationMap
     */
     UserLocationMap getUserLocationMap();
    
}
