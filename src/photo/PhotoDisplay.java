package photo;

import java.awt.*;

public class PhotoDisplay extends Canvas {
    private static final long serialVersionUID = 1L;
    private Image img;

    public PhotoDisplay(Image img) {
        this.img = img;
    }

    public void paint(Graphics g) {

        Toolkit t = Toolkit.getDefaultToolkit();
        g.drawImage(img, 120, 100, this);

    }

}