package user;

import java.util.ArrayList;
import java.util.HashSet;

import graph.Coordinates;

public class UserLocationMap implements IUserLocationMap{

  //2D array showing the coordinates of users 
    private ArrayList<ArrayList<HashSet<Integer>>> userLocationMap;
    
    
    public UserLocationMap() {
        
        userLocationMap = new ArrayList<ArrayList<HashSet<Integer>>>();
        
        for (int i = 0; i < 180; i++) {
            
            ArrayList<HashSet<Integer>> lat = new ArrayList<HashSet<Integer>>();
            for (int j = 0; j < 360; j++) {
                HashSet<Integer> setToAdd = new HashSet<Integer>();
                lat.add(setToAdd);
            }
            
            userLocationMap.add(lat);
        }
        
        System.out.println(userLocationMap.get(0).size());
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
    public double distBetweenUsers(int firstLat, int firstLong, int secondLat, int secondLong) {
        // TODO Auto-generated method stub
        
        //y2-y1 
        double yDistDiff = (double) secondLong - firstLong;
        
        double xDistDiff = (double) secondLat - firstLat;
        
        return Math.sqrt(((yDistDiff * yDistDiff) + (xDistDiff * xDistDiff)));
    }
    
    
    
}
