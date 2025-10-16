
package mgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static mgame.direction.up;


enum direction{up,down,left,right}
public class panel extends JPanel implements KeyListener,Runnable{
     static int win_width = 800;
     static int win_hghit = 500;
    private static final int space = 20;
    private static final int width_point = win_width/space;
    private static final int hghit_point = win_hghit/space;
    private int len = 20;
    private int speed = 100;
    private int score = 0;
    int tile =4 ;
    boolean alive = true;
    boolean press = false;
    ImageIcon img = new ImageIcon("src\\imgs\\apple_740922(1).png");
    ImageIcon img2 = new ImageIcon("src\\imgs\\Vector_20180606_0221(1).jpg");
    ImageIcon img3 = new ImageIcon("src\\imgs\\pixil-frame-0 (R2).png");;
    private direction input=direction.right;
    private ArrayList<Point> loc;
    private Point pre = new Point(len,0);
    private Point temp = new Point(0,0);
    private Point apple = new Point(0,0);
    private short fix=-25;
    private short fixy=-18;
    Thread th;
    panel(){
        
        this.setSize(win_width, win_hghit);
        this.setBackground(new Color(24,62,12));
        loc = new ArrayList<Point>();
        
        for (int i = 0; i <= len; i++) {
            Point temp = new Point(len-i,0);
            loc.add(temp);
        }
        rendomApple();
        this.grabFocus();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
         th = new Thread( this);
        alive = true;
        th.start();
        
    }
    public void resume(){
       alive = true;
       
    }
    public void stop(){
        alive = false;
    }
    public void w8(){
        alive = false;
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 =  (Graphics2D) g;
        
        
        
         g2.setColor(new Color(171,54,45));
         g2.drawImage(img2.getImage(), 0, 0,null);
         g2.drawImage(img2.getImage(),win_width , 0,null);
         
         g2.setColor(new Color(240,134,80));
        for (int i = 0; i < win_width/space; i++) {
            g.drawLine(i*space, 0, i*space, win_hghit);
            if (i<win_hghit/space) {
                g.drawLine(0, i*space, win_width, i*space);
            }
        }
        
         g2.drawImage(img.getImage(), apple.x*space, apple.y*space,null);
        //g2.fillOval(apple.x*space, a
//            tile+=5;
//            System.out.println(tile);
//            g2.fillArc(-(win_width/2),-(win_hghit/2), win_width+200, win_width+200, 0, tile);
//            if (tile==360) {
//                tile=6;
//            }
//        }
       // g2.setColor(new Color(58,6,3));
            
       // g2.setColor(new Color(112,154,209));
        g2.setColor(new Color(83,97,48));
            for (int i = 0; i < loc.size(); i++) {   
            if (i!=0) {
                temp.x = loc.get(i).x;
                temp.y = loc.get(i).y;
                loc.get(i).x = pre.x;
                loc.get(i).y = pre.y;
                
                pre.x = temp.x;
                pre.y = temp.y;
            }
            if (i==0) {
                    g2.drawImage(img3.getImage(),loc.get(i).x*space+fix , loc.get(i).y*space+fixy,null);
                       
               // g2.fillRect(loc.get(i).x*space-5, loc.get(i).y*space-5, space+10, space+10); 
            }else if(i>len-5){
                g2.fillRect(loc.get(i).x*space+(tile/2), loc.get(i).y*space+(tile/2), space-tile, space-tile); 
                tile+=2;
            }else{
                    if (i!=1) {
                    g2.fillRect(loc.get(i).x*space, loc.get(i).y*space, space, space);
                }
                    
                    if (loc.getFirst().equals(loc.get(i+1))) {
                        System.out.println("ffffff");
                        alive=false;
                        break;
                }
            }
        }
            tile = 4;
        
        
        
    }
    
    public void move(direction input){
         pre.x = loc.getFirst().x;
        pre.y = loc.getFirst().y;
        switch (input){
            case up:
                
                loc.getFirst().y--;
                        break;
            case down:
                loc.getFirst().y++;
                        break;
            case left:
                loc.getFirst().x--;
                        break;
             case right:
                 loc.getFirst().x++;
                        break;
            default:
                  
                    ;}
        
    }
    private void chekApple(){
        
             if (loc.getFirst().equals(apple)) {
                 score++;
            len =len+4;
            for (int i = 1; i <= 4; i++) {
                Point temp = new Point(0,0);
                loc.add(temp);
                
            }
            rendomApple();
        }

    }

public void rendomApple(){
            apple.x=(int)(Math.random()*width_point);
            apple.y=(int)(Math.random()*hghit_point);
    for (int i = 0; i < loc.size(); i++) {
        if (apple.equals(loc.get(i))) {
            rendomApple();
            break;
        }
    }
            
}
public void chekWall(){
   
    if (loc.getFirst().x>width_point) {
        loc.getFirst().x = 0;
    }else if (loc.getFirst().x<0) {
        loc.getFirst().x = width_point;
    }else if (loc.getFirst().y>hghit_point) {
        loc.getFirst().y = 0;
    }else if (loc.getFirst().y<0) {
        loc.getFirst().y = hghit_point;
    }
        
//        if ((loc.getFirst().x>width_point||loc.getFirst().x<0)||(loc.getFirst().y>hghit_point||loc.getFirst().y<0)) {
//            alive=false;//the tip 1
//         
//        }
            
}
    @Override
    public void run() {
        try{
            
        while(true){
            Thread.sleep(speed);
            if (alive) {
                 
            move(input);
            chekApple();
            chekWall();
            repaint();
            press=false;
            }
            
        }
        
           }catch(Exception e){System.out.println(e); }
}

    @Override
    public void keyTyped(KeyEvent e) {
            }

    @Override
    public void keyPressed(KeyEvent e) {
      
        if (!press) {
            switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                if (input!=direction.down) {
                    input = direction.up;
                    
                    img3 = new ImageIcon("src\\imgs\\pixil-frame-0 (up2).png");
                    fix = -23;
                     fixy=-15;
                }
                        break;
            case KeyEvent.VK_DOWN:
                if (input!=direction.up) {
                    input = direction.down;
                     img3=new ImageIcon("src\\imgs\\pixil-frame-0 (D).png");
                     fix = -22;
                     fixy=-25;
                }
                
                        break;
            case KeyEvent.VK_LEFT:
                if (input!=direction.right) {
                    input = direction.left;
                    img3 = new ImageIcon("src\\imgs\\pixil-frame-0 (5).png");
                    fix = -15;
                     fixy=-19;
                }
                        break;
             case KeyEvent.VK_RIGHT:
                 if (input!=direction.left) {
                    input = direction.right;
                    img3 = new ImageIcon("src\\imgs\\pixil-frame-0 (R2).png");
                    fix = -25;
                    fixy=-18;
                }
                        break;
            default:
                    
                    ;}
        }
        
         press=true;
         
        if (e.getKeyCode()== KeyEvent.VK_ESCAPE) {
            System.out.println(alive);
                 if (alive) {
                     
                     stop();
                 }else{
                 resume();
                    }
        }
          
         
        
     }

    @Override
    public void keyReleased(KeyEvent e) {
        
         }
}
class menu{

    public menu(){
    
    }
    
    
}