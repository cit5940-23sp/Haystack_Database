package user;

import java.util.TreeMap;

import graph.Coordinates;

public class ListOfUsers implements IListOfUsers {

    TreeMap<Integer, User> listOfUsers;
    UserLocationMap userLocationMap;
    UserGraph graphOfConnections;

    public ListOfUsers() {
        listOfUsers = new TreeMap<Integer, User>();
        userLocationMap = new UserLocationMap();
        graphOfConnections = new UserGraph();

    }

    @Override
    public int addUser(String userName, int latitudeGiven, int longitudeGiven) {


        int latitude = latitudeGiven + IUserLocationMap.LATITUDE_ADD;
        int longitude = longitudeGiven + IUserLocationMap.LONGITUDE_ADD;
        
        if (latitude > 180 || latitude < 0 || longitude < 0 || longitude > 360) {
            return -1;
        }
        
        User newUser = new User(userName, listOfUsers.size(), latitude,
                longitude);
        
        listOfUsers.put(newUser.getUniqueUserID(), newUser);

        Coordinates addressCoor = new Coordinates(latitude, longitude);
        
        userLocationMap.addUser(newUser.getUniqueUserID(), addressCoor);
        graphOfConnections.addNewUser();

        return newUser.getUniqueUserID();

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

    @Override
    public TreeMap<Integer, User> getListOfUsers() {
        return listOfUsers;
    }

}
