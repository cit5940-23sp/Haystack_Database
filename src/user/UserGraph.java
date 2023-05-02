package user;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.AbstractMap.SimpleEntry;

import graph.DistUser;
import graph.GraphL;

public class UserGraph implements IUserGraph {

    GraphL graphOfConnections;
    
    public UserGraph(){
        this.graphOfConnections = new GraphL();
    }

    @Override
    public void addNewUser() {
        // TODO Auto-generated method stub
        graphOfConnections.addNode();
        
    }
    
    @Override
    public void addNewFriend(User curUser, User newFriend, UserLocationMap userMap) {
        // TODO Auto-generated method stub
        
        int distance = userMap.distBetweenUsers(curUser, newFriend);
        
        System.out.println("distance: " + distance);
        
        graphOfConnections.addEdge(curUser.getUniqueUserID(), newFriend.getUniqueUserID(), distance);
        graphOfConnections.addEdge(newFriend.getUniqueUserID(), curUser.getUniqueUserID(), distance);
        
    }

    @Override
    public int[] getFriends(int uniqueUserID) {
        // TODO Auto-generated method stub
        int[] neighbors = graphOfConnections.neighbors(uniqueUserID);
        
        return neighbors; 
        
    }


    static class DistUserComparator implements Comparator<DistUser> {

        @Override
        public int compare(DistUser o1, DistUser o2) {

            int dist1 = o1.getLeft();
            
            int dist2 = o2.getLeft();
            
            Integer d1 = dist1;
            
            Integer d2 = dist2;
            
            if (dist1 - dist2 > 0) {
                return -1;
            //else, return 1 
            } else if (dist1 - dist2 < 0) {
                return 1;
            } else {
                return d1.compareTo(d2);
            }
        }
    }
    
    @Override
    public PriorityQueue<DistUser> getFriendsOfFriends(int uniqueUserID, 
            UserLocationMap userMap, ListOfUsers lou) {
        // TODO Auto-generated method stub
        //if threshold is less than zero, return edge case -1 
        
        //initialize a set of vertices that have been visited 
        HashSet <Integer> verticesVisited = new HashSet <Integer>();
        
        //initialize a queue to keep track of current generation nodes
        Queue <Integer> vertexQueue = new LinkedList <Integer>();
        
        
        //initialize a queue to keep track of next generation nodes 
        Queue <Integer> tempQueue = new LinkedList <Integer>();
        

        //initialize a priority queue to keep track of top 3 friend recommendations 
        PriorityQueue<DistUser> priorityQueue = new PriorityQueue<DistUser>(new DistUserComparator());
        
        //add seed into vertices visited 
        verticesVisited.add(uniqueUserID);

        //add seed into vertexQueue 
        vertexQueue.add(uniqueUserID);
        
        int curLevel = 0;
        

            //while vertexQueue is not empty 
            while (vertexQueue.size() > 0) {
               
                curLevel ++;
                //while vertexQueue is not empty 
                while (vertexQueue.size() > 0) {
                
       
                //remove vertex from queue 
                int curVertex = vertexQueue.poll();

                //get neighbors of curVertex 
                int[] neighbors = getFriends(curVertex);
                
                //go through each neighbor 
                for (int i = 0; i < neighbors.length; i++) {
                    
                    //get neighbor 
                    int vertexToAdd = neighbors[i];
//                    System.out.println("Vertex neighbor: " + vertexToAdd);
                    //if vertex has not been visited 
                    if (!verticesVisited.contains(vertexToAdd)) {
                        
                        //add vertex into verticesVisited 
                        verticesVisited.add(vertexToAdd);
//                        System.out.println("VertexToAdd: " + vertexToAdd);
                        
                        //add vertex into tempQueue 
                        tempQueue.add(vertexToAdd);
//                        System.out.println("curLevel: " + curLevel);
                        if (curLevel == 2) {
                            
                            
                            User user1 = lou.getUser(vertexToAdd);
                            User user2 = lou.getUser(uniqueUserID);
                            int distance = userMap.distBetweenUsers(user1, user2);

                            DistUser distUser = new DistUser(distance, vertexToAdd);
                            
                            priorityQueue.add(distUser);                            
                        }

                        
                    }
                    
                }
                

            }
                //go through each vertex in tempQueue and add into vertexQueue 
                while (tempQueue.size() > 0 && curLevel == 1) {
                    vertexQueue.add(tempQueue.poll());
                }
        }
            
//            
        while (priorityQueue.size() > 3) {
            priorityQueue.remove();
        }
                
        return priorityQueue;
        
    }

    
   
    @Override
    public void getFriendRecommondation(int uniqueUserID, ListOfUsers lou, UserLocationMap userMap) {
        // TODO Auto-generated method stub
        
        PriorityQueue<DistUser> setOfFof = getFriendsOfFriends(uniqueUserID, userMap, lou);
        
        System.out.println(setOfFof.size());
        
        for (DistUser ele : setOfFof) {
            
            User curUser = lou.getUser(ele.getRight());
            
            System.out.println(curUser.getUserName());
            System.out.println(ele.getLeft());
            
        }
        
    }
    
}
