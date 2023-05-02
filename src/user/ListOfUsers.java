package user;

import java.util.TreeMap;

public class ListOfUsers implements IListOfUsers {
    
    TreeMap<Integer, User> listOfUsers;

    public ListOfUsers(){
        listOfUsers = new TreeMap<Integer, User>();
    }

    public void addUser(String userName, int latitude, int longitude, 
            UserLocationMap userLocationMap, UserGraph graphOfConnections) {
        
        User newUser = new User(userName, listOfUsers.size(), latitude, 
                longitude, userLocationMap, graphOfConnections);
        
        listOfUsers.put(newUser.getUniqueUserID(), newUser);
    }
    
    public User getUser(int uniqueUserID) {
        return listOfUsers.get(uniqueUserID);
    }
}
