package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import tuple.Coordinates;
import tuple.DistUser;
import user.UserLocationMap.DistUserComparator;

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
    public void addFriend(int uniqueFriendID, int requestUserID) {
        // TODO Auto-generated method stub

        User curUser = getUser(requestUserID);
        User friend = getUser(uniqueFriendID);

        graphOfConnections.addNewFriend(curUser, friend, userLocationMap);
        curUser.addFriend(friend);
        friend.addFriend(curUser);

    }

    
    private PriorityQueue<DistUser> getClosestUsers(int latitude,
            int longitude, User requestUser) {

        PriorityQueue<DistUser> queueOfClosestUsers = 
                new PriorityQueue<DistUser>(new DistUserComparator());

      
        for (Map.Entry<Integer, User> entry : listOfUsers.entrySet()) {

            User user = entry.getValue();
            
            if (user.getUniqueUserID() == requestUser.getUniqueUserID()) {
                continue;
            }

            int distance = userLocationMap.distBetweenUsers(requestUser, user);
            
            if (distance > -1) {
                DistUser distUser = new DistUser(distance, user.getUniqueUserID());

                queueOfClosestUsers.add(distUser);                
            }

        }

        return queueOfClosestUsers;

    }


    private PriorityQueue<DistUser> getFriendsOfFriends(int uniqueUserID) {


        // initialize a set of vertices that have been visited
        HashSet<Integer> verticesVisited = new HashSet<Integer>();

        // initialize a queue to keep track of current generation nodes
        Queue<Integer> vertexQueue = new LinkedList<Integer>();

        // initialize a queue to keep track of next generation nodes
        Queue<Integer> tempQueue = new LinkedList<Integer>();

        // initialize a priority queue to keep track of top 3 friend recommendations
        PriorityQueue<DistUser> priorityQueue = 
                new PriorityQueue<DistUser>(new DistUserComparator());

        // add seed into vertices visited
        verticesVisited.add(uniqueUserID);

        // add seed into vertexQueue
        vertexQueue.add(uniqueUserID);

        int curLevel = 0;

        // while vertexQueue is not empty
        while (vertexQueue.size() > 0) {

            curLevel++;
            // while vertexQueue is not empty
            while (vertexQueue.size() > 0) {

                // remove vertex from queue
                int curVertex = vertexQueue.poll();

                // get neighbors of curVertex
                int[] neighbors = graphOfConnections.getFriends(curVertex);

                // go through each neighbor
                for (int i = 0; i < neighbors.length; i++) {

                    // get neighbor
                    int vertexToAdd = neighbors[i];
                    // if vertex has not been visited
                    if (!verticesVisited.contains(vertexToAdd)) {

                        // add vertex into verticesVisited
                        verticesVisited.add(vertexToAdd);

                        // add vertex into tempQueue
                        tempQueue.add(vertexToAdd);
                        if (curLevel == 2) {

                            User user1 = getUser(vertexToAdd);
                            User user2 = getUser(uniqueUserID);
                            int distance = userLocationMap.distBetweenUsers(user1, user2);

                            DistUser distUser = new DistUser(distance, vertexToAdd);

                            priorityQueue.add(distUser);
                        }

                    }

                }

            }
            // go through each vertex in tempQueue and add into vertexQueue
            while (tempQueue.size() > 0 && curLevel == 1) {
                vertexQueue.add(tempQueue.poll());
            }
        }

        return priorityQueue;

    }

    @Override
    public List<DistUser> getFriendRecommondation(int uniqueUserID, int numOfRec) {
        // TODO Auto-generated method stub

        PriorityQueue<DistUser> setOfFof = getFriendsOfFriends(uniqueUserID);

        HashSet<User> friendsList = getUser(uniqueUserID).getFriendsList();

        HashSet<Integer> finalListCheck = new HashSet<Integer>();
        
        User curUser = getUser(uniqueUserID);
        Coordinates userCoor = curUser.getUserCoor();

        int latitude = userCoor.getLeft();
        int longitude = userCoor.getRight();
        
        PriorityQueue<DistUser> closestDist = getClosestUsers(latitude, longitude, curUser);
        

        List<DistUser> finalList = new ArrayList<DistUser>();

        while (finalList.size() < numOfRec) {

            if (setOfFof.size() == 0 ) {
                break;
            }
            
            DistUser curDistUser = setOfFof.remove();

            User friendUser = getUser(curDistUser.getRight());

            if (!friendsList.contains(friendUser)) {
                if (!finalListCheck.contains(curDistUser.getRight())) {
                    finalList.add(curDistUser);
                    finalListCheck.add(curDistUser.getRight());
                }

            }


        }
        
        while (finalList.size() < numOfRec) {

            if (closestDist.size() == 0) {
                break;
            }
            
            DistUser curDistUser = closestDist.remove();

            User friendUser = getUser(curDistUser.getRight());

            if (!friendsList.contains(friendUser)) {
                if (!finalListCheck.contains(curDistUser.getRight())) {
                    finalList.add(curDistUser);
                    finalListCheck.add(curDistUser.getRight());
                }

            }

        }

        return finalList;
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
