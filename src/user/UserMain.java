package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import graph.GraphL;
import photo.photoDisplay;

public class UserMain {

    //Graph of users with nodes as Users and edges as connections 

    int nextUserID;
    UserGraph graphOfConnections; 
    UserLocationMap userLocationMap;
    
    public UserMain() {

        nextUserID = 0;
        this.graphOfConnections = new UserGraph();
        this.userLocationMap = new UserLocationMap();
        
    }
    
    public void createNewUser(Scanner scan) {
        
        System.out.println("What is your name?");
        
        String userName = scan.nextLine();
        
        System.out.println("What is your address (latitude)?");
        int latitude = scan.nextInt();
        
        System.out.println("What is your address (longitude)?");
        int longitude = scan.nextInt();
        
        User newUser = new User(userName, nextUserID, latitude, 
                longitude, userLocationMap, graphOfConnections );
        
        nextUserID ++;
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        UserMain um = new UserMain();
       
        Scanner scan = new Scanner(System.in);
        //Create a new User 
        while (true) {
            
            System.out.println("Create a new user? Y/N");
            String answer = scan.nextLine();
            answer = answer.toLowerCase();
            if (answer.equals("y")) {
                um.createNewUser(scan);
                scan.nextLine();
            }
            if(answer.equals("exit")) {
                break;
            }

        }
        
    }

}
