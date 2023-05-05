package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.FactoryConfigurationError;

import graph.DistUser;
import graph.GraphL;
import photo.IPhoto;
import photo.photoDisplay;
import haystack.ListOfHaystacks;

public class UserMain {

    //Graph of users with nodes as Users and edges as connections 

    int nextUserID;
//    UserGraph graphOfConnections; 
//    UserLocationMap userLocationMap;
    ListOfUsers lou;
    ListOfHaystacks loh = new ListOfHaystacks();
    User curUser;
    
    public UserMain() {

        this.lou = new ListOfUsers();
        
        nextUserID = 0;
//        this.graphOfConnections = lou.getGraphOfConnections()
//        this.userLocationMap = lou.getUserLocationMap();
        
        lou.addUser("Amy", 30, 180);
        lou.addUser("Tom", 30, 180);
        lou.addUser("Jim", 90, 180);
        lou.addUser("Kim", 30, 80);
        lou.addUser("Tim", 30, 90);
        
        
        User testUser = lou.getUser(0);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);        
        
    }
    
    public void createNewUser(Scanner scan) {
        
        System.out.println("What is your name?");
        
        String userName = scan.nextLine();
        
        System.out.println("What is your address (latitude)?");
        int latitude = scan.nextInt();
        
        System.out.println("What is your address (longitude)?");
        int longitude = scan.nextInt();
        
        int uniqueUserID = lou.addUser(userName, latitude, longitude);
        
        curUser = lou.getUser(uniqueUserID);

        
    }
    
    public void userOptions(Scanner scan) {
        
    
        System.out.println("--------------------------------");
        System.out.println("Haystack Photo Management System");
        System.out.println("--------------------------------");
        System.out.println(" 1 -- Insert Photo in the database");
        System.out.println(" 2 -- Display Photo");
        System.out.println(" 3 -- Delete Photo");
        System.out.println(" 4 -- Update Photo");
        System.out.println(" 5 -- Make new friends");
        System.out.println(" 6 -- See photos of other users");
        System.out.println(" 7 -- Exit out of the system");
        System.out.println("");
        System.out.println("Please enter your option, eg. '1'.");
        
        String Option = scan.nextLine();

        switch(Option) {
        case "1":
            insertPhoto(scan);
            break;
        case "2":
            displayPhoto(scan);
            break;
        case "3":
            deletePhoto(scan);
            break;
        case "4":
            friendSuggestions(scan);
            break;
            
        case "5":
            displayOtherUserPhotos(scan);
            break;
            
        case "6":
            System.out.println("Thanks for using the Haystack database!");
            break;
        }     
    }
    
    int counter = 0;

    public void insertPhoto(Scanner scan) {
        System.out.println("Choose the Photo path you want to put in the database: ");
        StringBuilder sb = new StringBuilder();
        String path = sb.append(Integer.toString(counter)).toString();
        counter++;
        JFileChooser j = null;
        path = IPhoto.chooseFile(j);
        curUser.addPhoto(path, loh, false);
        
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
        curUser.displayPhotoList();
        int photo = Integer.parseInt(scan.nextLine());
        int photoAmt = curUser.getUserPhotoList().getAllPhotos().size();


//            int displayKey = curUser.getUserPhotoList().getAllPhotos().get(photo).getKey();
            curUser.getPhoto(photo, loh, curUser);
        System.out.println("Please see the photo displayed. Please see other options you can perform: ");
        userOptions(scan);   
    }
    
    public void deletePhoto(Scanner scan) {
        System.out.println("Please choose one of the photos in the database to delete: ");
        curUser.displayPhotoList();
        int photo = Integer.parseInt(scan.nextLine());
        int photoAmt = curUser.getUserPhotoList().getAllPhotos().size();

//        if(photo>photoAmt-1 ||photo<0) {
//            deletePhoto(scan);
//        }else {
//            int deleteKey = curUser.getUserPhotoList().getAllPhotos().get(photo).getKey();
            curUser.deletePhoto(photo, loh);
            System.out.println("The photo has been deleted! Please see rest of the photos in the database: ");
            curUser.displayPhotoList();
//        }
        System.out.println("Please see other options you can perform: ");
        userOptions(scan);   
    }

    public void friendSuggestions(Scanner scan) {
        
        System.out.println("Make a new friend. Please see your new friend suggestions: ");
        
        UserGraph graphOfConnections = lou.getGraphOfConnections();
        
        UserLocationMap userLocationMap = lou.getUserLocationMap();
        
        graphOfConnections.getFriendRecommondation(curUser.getUniqueUserID(), lou, userLocationMap, 3);
        
        System.out.println("Who do you want to become friends with (enter the ID): ");
        
        int friendID = Integer.parseInt(scan.nextLine());
        
        curUser.addFriend(friendID, lou);
        
        System.out.println("Congrats you made a new friend! Here is your current list of friends");
        
        HashSet<User> friendList = curUser.getFriendsList();
        
        for (User user : friendList) {
            System.out.print(user.getUserName() + " || ");

        }
        System.out.println();
        
        userOptions(scan);   
       
    }

    public void displayOtherUserPhotos(Scanner scan) {
        
        System.out.println("Whose photo do you want to see (enter the ID): ");
        
        TreeMap<Integer, User> listOfUsers = lou.getListOfUsers();
        
        for (Map.Entry<Integer, User> entry : listOfUsers.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getUserName());
        }
        
        int userID = Integer.parseInt(scan.nextLine());
        
        User userToSeePhoto = lou.getUser(userID);
        
        System.out.println("Please choose one of the photos in the database: ");
        userToSeePhoto.displayPhotoList();
        
        int photo = Integer.parseInt(scan.nextLine());
        
        userToSeePhoto.getPhoto(photo, loh, curUser);
        
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
