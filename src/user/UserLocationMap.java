package user;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import tuple.Coordinates;
import tuple.DistUser;
import user.UserGraph.DistUserComparator;

public class UserLocationMap implements IUserLocationMap {

    // 2D array showing the coordinates of users
    private ArrayList<ArrayList<HashSet<Integer>>> userLocationMap;


    /**
     * Constructor for UserLocationMap
     */
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

        int latitude = addressCoor.getLeft();
        int longitude = addressCoor.getRight();

        HashSet<Integer> curSet = userLocationMap.get(latitude).get(longitude);
        curSet.add(uniqueUserID);
        

    }

    @Override
    public HashSet<Integer> getUsersInLocation(int latitude, int longitude) {

        HashSet<Integer> curSet = userLocationMap.get(latitude).get(longitude);
        return curSet;
    }

    @Override
    public int distBetweenUsers(User user1, User user2) {
        
        if (user1 == null|| user2 == null) {
            return -1;
        }
        
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

    static class DistUserComparator implements Comparator<DistUser> {

        @Override
        public int compare(DistUser o1, DistUser o2) {

            int dist1 = o1.getLeft();

            int dist2 = o2.getLeft();

            Integer d1 = dist1;

            Integer d2 = dist2;

            if (dist1 - dist2 > 0) {
                return 1;
                // else, return 1
            } else if (dist1 - dist2 < 0) {
                return -1;
            } else {
                return d1.compareTo(d2);
            }
        }
    }

    @Override
    public PriorityQueue<DistUser> getClosestUsers(int latitude,
            int longitude, ListOfUsers lou, User requestUser) {

        PriorityQueue<DistUser> queueOfClosestUsers = 
                new PriorityQueue<DistUser>(new DistUserComparator());

        TreeMap<Integer, User> listOfUsers = lou.getListOfUsers();

        for (Map.Entry<Integer, User> entry : listOfUsers.entrySet()) {

            User user = entry.getValue();
            
            if (user.getUniqueUserID() == requestUser.getUniqueUserID()) {
                continue;
            }

            int distance = distBetweenUsers(requestUser, user);

            DistUser distUser = new DistUser(distance, user.getUniqueUserID());

            queueOfClosestUsers.add(distUser);


        }

        return queueOfClosestUsers;

    }

}
