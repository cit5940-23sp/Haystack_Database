package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import graph.GraphL;
import photo.photoDisplay;
import haystack.ListOfHaystacks;

public class UserMain {

    //Graph of users with nodes as Users and edges as connections 

    int nextUserID;
    UserGraph graphOfConnections; 
    UserLocationMap userLocationMap;
    ListOfHaystacks loh = new ListOfHaystacks();
    User newUser;
    
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
        
        newUser = new User(userName, nextUserID, latitude, 
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
            insertPhoto(scan);
        case "2":
        case "3":
            
        case "4":
            System.out.println("Thanks for using the Haystack database!");
        }     
    }
    
    public void insertPhoto(Scanner scan) {
        System.out.println("Choose the Photo path you want to put in the database");
        //wait for the filePath from fileFinder in photoDisplay
        newUser.addPhoto("./cat.jpeg", loh);
        //maybe add a case when photo is too big
        System.out.println("Photo is added successfully, would you to add more photos? (y/n)");
        
        String answer = scan.nextLine();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            insertPhoto(scan);
        } else {
            System.out.println("Thanks for adding the photos, please see other options you can perform.");
            userOptions(scan);
        }
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
            if(answer.equals("exit")) {
                break;
            }

        }
        
    }

}
