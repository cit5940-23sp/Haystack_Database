package user;

import java.util.ArrayList;
import java.util.HashSet;

import graph.Coordinates;

public class UserLocationMap implements IUserLocationMap{

  //2D array showing the coordinates of users 
    private ArrayList<ArrayList<HashSet<Integer>>> userLocationMap;
    
    
    public UserLocationMap() {
        
        userLocationMap = new ArrayList<ArrayList<HashSet<Integer>>>();
        
        for (int i = 0; i < 181; i++) {
            
            ArrayList<HashSet<Integer>> lat = new ArrayList<HashSet<Integer>>();
            for (int j = 0; j < 361; j++) {
                HashSet<Integer> setToAdd = new HashSet<Integer>();
                lat.add(setToAdd);
            }
            
            userLocationMap.add(lat);
        }
        
    }


    @Override
    public void addUser(int uniqueUserID, Coordinates addressCoor) {
        // TODO Auto-generated method stub
        int latitude = addressCoor.getLeft();
        int longitude = addressCoor.getRight();
        
        HashSet<Integer> curSet = userLocationMap.get(latitude).get(longitude);
        curSet.add(uniqueUserID);
        
    }


    @Override
    public HashSet<Integer> getUsersInLocation(int latitude, int longitude) {
        // TODO Auto-generated method stub
        HashSet<Integer> curSet = userLocationMap.get(latitude).get(longitude);
        return curSet;
    }


    @Override
    public int distBetweenUsers(User user1, User user2) {
     
        
        Coordinates user1Coor = user1.getUserCoor();
        
        Coordinates user2Coor = user2.getUserCoor();
        
        int firstLat = user1Coor.getLeft();
        
        int secondLat = user2Coor.getLeft();
        
        int firstLong = user1Coor.getRight();
        
        int secondLong = user2Coor.getRight();
        
        double yDistDiff = (double) secondLong - firstLong;
        
        double xDistDiff = (double) secondLat - firstLat;
        
        return (int) Math.sqrt(((yDistDiff * yDistDiff) + (xDistDiff * xDistDiff)));
    }
    
    
    
}
