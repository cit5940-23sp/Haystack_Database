package user;

import java.util.Comparator;

import graph.GraphL;
import tuple.DistUser;

public class UserGraph implements IUserGraph {

    GraphL graphOfConnections;

    /**
     * Constructor for UserGraph()
     */
    public UserGraph() {
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

        graphOfConnections.addEdge(curUser.getUniqueUserID(),
                newFriend.getUniqueUserID(), distance);
        graphOfConnections.addEdge(newFriend.getUniqueUserID(),
                curUser.getUniqueUserID(), distance);

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
    public GraphL getGOC() {
        return graphOfConnections;
    }

}
