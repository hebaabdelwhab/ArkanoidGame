package arkanoid.game;

import javax.swing.JFrame;

public class ArkanoidGame {

    public static final int WIDTH= 900,HEIGHT=800;
    public static void main(String[] args) {
        
        JFrame arkanoidFrame=new JFrame("Classic Arkanoid");
        
        arkanoidFrame.setSize(WIDTH, HEIGHT);
        
        arkanoidFrame.setLocationRelativeTo(null);
        
        arkanoidFrame.setResizable(false);
        
        arkanoidPanel ap=new arkanoidPanel();
                
        arkanoidFrame.add(ap);
        
    
        arkanoidFrame.setVisible(true);
        arkanoidFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ap.run();
        
    }
    
}
