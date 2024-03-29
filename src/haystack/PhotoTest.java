package haystack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import photo.IPhoto;

class PhotoTest {

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    void loadImageRasterTest() {
        // Create an ImageIcon from the image
        byte[] image = IPhoto.loadImageToBytes("cat.jpeg");
        ImageIcon icon = new ImageIcon(image);

        // Create a JLabel to display the image
        JLabel label = new JLabel(icon);

        // Create a JFrame to hold the label and set its properties
        JFrame frame = new JFrame();
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
