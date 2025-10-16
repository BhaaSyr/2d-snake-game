
package mgame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MGame {
    
   static JFrame win ;
public static void swit(JPanel newp) {
        
        win.setVisible(false);
        win.getContentPane().remove(win.getContentPane());

        win.setContentPane(newp);

        win.setVisible(true);
        win.validate();
        win.repaint();
    }
    public static void main(String[] args) {
          win = new JFrame();
          Menup men =new Menup();
          
        win.setSize(panel.win_width+50, panel.win_hghit+70);
        //win.setLocationRelativeTo(null);
        win.setTitle("SnakeGame");
        win.setResizable(false);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
        win.getContentPane().add(men);
        System.out.println("");
        int t =6;
        String ss=(t%2==1)? "tek":"cift";
        
       // men.setBounds(-20, -10, 1310, 700);
    }
    
}
 


