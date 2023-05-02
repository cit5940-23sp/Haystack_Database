package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
    LinkedList<String> photoCaption;
    
    public UserMain() {

        nextUserID = 0;
        this.graphOfConnections = new UserGraph();
        this.userLocationMap = new UserLocationMap();
        this.photoCaption = new LinkedList<String>();
        
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
        System.out.println("Choose the Photo path you want to put in the database: ");
        //wait for the filePath from fileFinder in photoDisplay
        newUser.addPhoto("./cat.jpeg", loh);
        System.out.println("What would you like to call the picture?");
        //maybe add a case when photo is too big
        String caption = scan.nextLine();
        photoCaption.add(caption);
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
    
    public void displayPhoto(Scanner scan) {
        System.out.println("Please choose one of the photos in the database: ");
        int counter=1;
        for(int i= 0; i<photoCaption.size();i++) {
            System.out.print(counter);
            System.out.println(" :"+ photoCaption.get(i));
        }
        String Option = scan.nextLine();

       
        System.out.println();
        
        
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
