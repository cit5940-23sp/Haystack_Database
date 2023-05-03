package user;

import java.util.TreeMap;

import graph.Coordinates;

public class ListOfUsers implements IListOfUsers {
    
    TreeMap<Integer, User> listOfUsers;
    UserLocationMap userLocationMap;
    UserGraph graphOfConnections;

    public ListOfUsers(){
        listOfUsers = new TreeMap<Integer, User>();
        userLocationMap = new UserLocationMap();
        graphOfConnections = new UserGraph();
        
    }

    @Override
    public void addUser(String userName, int latitude, int longitude) {
        
        User newUser = new User(userName, listOfUsers.size(), latitude, 
                longitude, userLocationMap, graphOfConnections);
        
        listOfUsers.put(newUser.getUniqueUserID(), newUser);
        Coordinates addressCoor = new Coordinates(latitude, longitude);
        userLocationMap.addUser(newUser.getUniqueUserID(), addressCoor);
        graphOfConnections.addNewUser();
        
    }
    
    @Override
    public User getUser(int uniqueUserID) {
        return listOfUsers.get(uniqueUserID);
    }
    
    @Override
    public UserGraph getGraphOfConnections() {
        return graphOfConnections;
    }
    
    @Override
    public UserLocationMap getUserLocationMap() {
        return userLocationMap;
    }
}
