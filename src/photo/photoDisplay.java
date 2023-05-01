package photo;

import java.awt.*;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import haystack.ListOfHaystacks;
import user.User;
import user.UserGraph;
import user.UserLocationMap;

public class photoDisplay extends Canvas {
    private Image img;

    public photoDisplay(Image img) {
        this.img = img;
    }

    public void paint(Graphics g) {

        Toolkit t = Toolkit.getDefaultToolkit();
//        Image i=t.getImage("cat.jpeg");  
        g.drawImage(img, 120, 100, this);

    }
    public static String chooseFile() {
        JFileChooser j = new JFileChooser();
        j.setDialogType(JFileChooser.OPEN_DIALOG);
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        j.setCurrentDirectory(new File("/Users/lala/eclipse-workspace/594Java/Haystack_Database"));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Format(.jpg/png/jpeg)"
                ,"jpg", "png", "jpeg");
        j.setFileFilter(filter);
        
        int response = j.showOpenDialog(null);
        
        if(response == JFileChooser.APPROVE_OPTION) {
            return j.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static void main(String[] args) {
        // Using this process to invoke the constructor,
        // JFileChooser points to user's default directory
        String file = chooseFile();
        
        ListOfHaystacks loh = new ListOfHaystacks();

        String userName = "Tim";

        int nextUserID = 1;

        int latitude = 90;

        int longitude = 90;

        UserGraph graphOfConnections = new UserGraph();

        UserLocationMap userLocationMap = new UserLocationMap();

        User newUser = new User(userName, nextUserID, latitude, longitude, userLocationMap, graphOfConnections);


        newUser.addPhoto(file, loh);
        Image res = newUser.getPhoto(0, loh);

        photoDisplay m = new photoDisplay(res);
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(1000, 1000);
        f.setVisible(true);
    }

}