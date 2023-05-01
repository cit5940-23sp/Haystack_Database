package photo;

import java.awt.*;  
import javax.swing.JFrame;  

public class photoDisplay extends Canvas{  
      
    public void paint(Graphics g) {  
  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("cat.jpeg");  
        g.drawImage(i, 120,100,this);  
          
    }  
    
    public static void main(String[] args) {  
        photoDisplay m=new photoDisplay();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(1000,1000);  
        f.setVisible(true);  
    }  
  
}  