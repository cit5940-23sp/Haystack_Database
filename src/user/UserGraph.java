package user;

import graph.GraphL;

public class UserGraph implements IUserGraph {

    GraphL graphOfConnections;
    
    UserGraph(){
        this.graphOfConnections = new GraphL();
    }

    @Override
    public void addNewUser() {
        // TODO Auto-generated method stub
        graphOfConnections.addNode();
        
    }

    @Override
    public void getFriends(int uniqueUserID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getFriendsOfFriends(int uniqueUserID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getFriendRecommondation(int uniqueUserID) {
        // TODO Auto-generated method stub
        
    }
    
    
    
    
}
