package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import graph.GraphL;
import haystack.ListOfHaystacks;

public class UserMain {

    //Graph of users with nodes as Users and edges as connections 

    int nextUserID;
    UserGraph graphOfConnections; 
    UserLocationMap userLocationMap;
    ListOfHaystacks lohs = new ListOfHaystacks();

    
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
    
    public void userOptions(Scanner scan) {
        
    
        System.out.println("-------------------------");
        System.out.println("Haystack Photo Management System");
        System.out.println("-------------------------");
        System.out.println(" 1 -- Insert Photo in the database");
        System.out.println(" 2 -- Display Photo");
        System.out.println(" 3 -- Delete Photo");
        System.out.println(" 4 -- Quit the system");
        System.out.println("");
        System.out.println("Please enter your option, eg. '1'.");
        
        String Option = scan.nextLine();

        switch(Option) {
        case "1":
            
        case "2":
        case "3":
            
        case "4":
            System.out.println("Thanks for using the Haystack database!");
        }     
    }
    
    public insertPhoto() {
        
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
            
            um.userOptions(scan);
            

        }
        
    }

}
