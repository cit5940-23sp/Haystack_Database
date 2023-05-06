package user;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFileChooser;

import photo.IPhoto;
import tuple.DistUser;
import haystack.ListOfHaystacks;

public class UserMain {

    int nextUserID;
    ListOfUsers lou;
    ListOfHaystacks loh = new ListOfHaystacks();
    User curUser;

    public UserMain() {

        this.lou = new ListOfUsers();

        nextUserID = 0;

        lou.addUser("Amy", 30, 180);
        lou.addUser("Tom", 30, 180);
        lou.addUser("Jim", 90, 180);
        lou.addUser("Kim", 30, 80);
        lou.addUser("Tim", 30, 90);

        User testUser = lou.getUser(0);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, true);
        testUser.addPhoto("./cat.jpeg", loh, false);
        testUser.addPhoto("./cat.jpeg", loh, true);

        lou.addFriend(1, 0);
        lou.addFriend(2, 0);

    }

    public void createNewUser(Scanner scan) {

        System.out.println("What is your name?");

        String userName = scan.nextLine();

        int uniqueUserID = -1;

        while (uniqueUserID == -1) {

            System.out.println("What is your address (latitude: -90 to 90)?");
            int latitude = scan.nextInt();

            System.out.println("What is your address (longitude: -180 to 180)?");
            int longitude = scan.nextInt();

            uniqueUserID = lou.addUser(userName, latitude, longitude);

            if (uniqueUserID == -1) {
                System.out
                        .println("Latitude must be between -90 to 90," + " and longitude must be between -180 to 180");
            }
        }

        curUser = lou.getUser(uniqueUserID);

    }

    public void userOptions(Scanner scan) {

        System.out.println("--------------------------------");
        System.out.println("Haystack Photo Management System");
        System.out.println("--------------------------------");
        System.out.println(" 1 -- Insert Photo into the database");
        System.out.println(" 2 -- Display own photo(s)");
        System.out.println(" 3 -- Delete photo");
        System.out.println(" 4 -- Update photo");
        System.out.println(" 5 -- Make new friends");
        System.out.println(" 6 -- Display photos of other users");
        System.out.println(" 7 -- Exit out of the system");
        System.out.println("");
        System.out.println("Please enter your option, eg. '1'.");

        String option = scan.nextLine();

        switch (option) {
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
            updatePhoto(scan);
            break;
        case "5":
            if ((curUser.getFriendsList().size() + 1) == lou.getListOfUsers().size()) {
                System.out.println("You are already friends with everyone.");
            } else {
                friendSuggestions(scan);
            }

            break;

        case "6":
            displayOtherUserPhotos(scan);
            break;

        case "7":
            System.out.println("Thanks for using the Haystack database!");
            break;
        default:
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

        System.out.println("Do you want to set this photo as private (for friends only)? (Y/N)");

        boolean setToPrivate;

        while (true) {

            String privateInput = scan.nextLine();

            if (privateInput.charAt(0) == 'y' || privateInput.charAt(0) == 'Y') {
                setToPrivate = true;
                break;
            } else if (privateInput.charAt(0) == 'n' || privateInput.charAt(0) == 'N') {
                setToPrivate = false;
                break;
            } else {
                System.out.println("Please type either Y or N");
            }

        }

        boolean successfulAdd = curUser.addPhoto(path, loh, setToPrivate);

        if (successfulAdd) {
            System.out.println("Photo added successfully. " + "Would you like to add more photos? (y/n)");
        } else {
            System.out.println("Photo too big to add! Please choose a photo that is less than 12kb,"
                    + " would you like to add more photos? (y/n)");
        }

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
        curUser.getPhoto(photo, loh, curUser);
        System.out.println("Please see the photo displayed. Please see other options you can perform: ");
        userOptions(scan);
    }

    public void deletePhoto(Scanner scan) {
        System.out.println("Please choose one of the photos in the database to delete: ");
        curUser.displayPhotoList();
        int photo = Integer.parseInt(scan.nextLine());
        int photoAmt = curUser.getUserPhotoList().getAllPhotos().size();

        curUser.deletePhoto(photo, loh);
        System.out.println("The photo has been deleted! Please see rest of the photos in the database: ");
        curUser.displayPhotoList();
        System.out.println("Please see other options you can perform: ");
        userOptions(scan);
    }

    public void friendSuggestions(Scanner scan) {

        System.out.println("Make a new friend. Please see your new friend suggestions: ");

        List<DistUser> finalList = lou.getFriendRecommondation(curUser.getUniqueUserID(), 3);

        System.out.println("Recommended friend (ID) : distance from you");

        for (DistUser ele : finalList) {

            User curUser = lou.getUser(ele.getRight());

            System.out.println(curUser.getUserName() + " (" + curUser.getUniqueUserID() + ") : " + ele.getLeft());

        }

        System.out.println("Who do you want to become friends with (enter the ID): ");

        int friendID = Integer.parseInt(scan.nextLine());

        HashSet<User> friendList = curUser.getFriendsList();

        if (friendList.contains(lou.getUser(friendID))) {
            System.out.println("You are already friends with this person. " + "Here is your current list of friends");
        } else if (friendID == curUser.getUniqueUserID()) {
            System.out.println(
                    "It's great that you want to be friends with yourself! " + "Here is your current list of friends");

        } else {
            lou.addFriend(friendID, curUser.getUniqueUserID());

            System.out.println("Congrats you made a new friend! " + "Here is your current list of friends");

        }

        friendList = curUser.getFriendsList();

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

    public void updatePhoto(Scanner scan) {
        System.out.println("Please choose one of the photos in the database to update: ");
        curUser.displayPhotoList();
        int photo = Integer.parseInt(scan.nextLine());
        System.out.println("Choose the Photo path you would like to use to replace the selected photo: ");
        StringBuilder sb = new StringBuilder();
        String path = sb.append(Integer.toString(counter)).toString();
        JFileChooser j = null;
        path = IPhoto.chooseFile(j);

        curUser.updatePhoto(path, photo, loh);
        System.out.println("Your photo is updated! ");
        System.out.println("Please see other options you can perform: ");
        userOptions(scan);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        UserMain um = new UserMain();

        Scanner scan = new Scanner(System.in);
        // Create a new User
        while (true) {

            System.out.println("Create a new user? Y/N");
            String answer = scan.nextLine();
            answer = answer.trim().toLowerCase();
            if (answer.startsWith("y")) {
                um.createNewUser(scan);
                scan.nextLine();
                um.userOptions(scan);
            } else if (answer.startsWith("n")) {
                System.out.println("Alright! No harsh feelings! Goodbye!");
                break;
            } else if (answer.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid Input! Please enter Y/N.");
            }

        }

    }

}
