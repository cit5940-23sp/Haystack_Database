package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import graph.GraphL;
import photo.IPhoto;
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
        System.out.println(" 4 -- Make new friends");
        System.out.println(" 4 -- Quit the system");
        System.out.println("");
        System.out.println("Please enter your option, eg. '1'.");
        
        String Option = scan.nextLine();

        switch(Option) {
        case "1":
            insertPhoto(scan);
        case "2":
            displayPhoto(scan);
        case "3":
            deletePhoto(scan);
        case "4":
            System.out.println("Please see your new friend suggestions: ");
        case "5":
            System.out.println("Thanks for using the Haystack database!");
            
        }     
    }
    
    public void insertPhoto(Scanner scan) {
        System.out.println("Choose the Photo path you want to put in the database: ");
        String path = IPhoto.chooseFile();
        newUser.addPhoto(path, loh);
        System.out.println("Photo is added successfully, would you to add more photos? (y/n)");
        
        String answer = scan.nextLine();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            insertPhoto(scan);
        } else {
            System.out.println("Thanks for adding the photos, please see other options you can perform: ");
            userOptions(scan);
        }
    }
    
    public void displayPhoto(Scanner scan) {
        System.out.println("Please choose one of the photos in the database: ");
        newUser.displayPhotoList();
        int photo = Integer.parseInt(scan.nextLine());
        int photoAmt = newUser.getUserPhotoList().getAllPhotos().size();

        if(photo>photoAmt-1 ||photo<0) {
            displayPhoto(scan);
        }else {
            int displayKey = newUser.getUserPhotoList().getAllPhotos().get(photo).getKey();
            newUser.getPhoto(displayKey, loh);
        }
        System.out.println("Please see the photo displayed. Please see other options you can perform: ");
        userOptions(scan);   
    }
    
    public void deletePhoto(Scanner scan) {
        System.out.println("Please choose one of the photos in the database to delete: ");
        newUser.displayPhotoList();
        int photo = Integer.parseInt(scan.nextLine());
        int photoAmt = newUser.getUserPhotoList().getAllPhotos().size();

        if(photo>photoAmt-1 ||photo<0) {
            deletePhoto(scan);
        }else {
            int deleteKey = newUser.getUserPhotoList().getAllPhotos().get(photo).getKey();
            newUser.deletePhoto(deleteKey, loh);
            System.out.println("The photo has been deleted! Please see rest of the photos in the database: ");
            newUser.displayPhotoList();
        }
        System.out.println("Please see other options you can perform: ");
        userOptions(scan);   
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
