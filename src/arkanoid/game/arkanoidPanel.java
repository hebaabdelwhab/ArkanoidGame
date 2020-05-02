package arkanoid.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;



public class arkanoidPanel extends JPanel implements drawable

{
    //fields
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    

    
    //entities
    Ball theBall ;
    Paddle thePaddle;
    brickMap theBrickMap;
    
    //---for mouse
    private myMouseMotionListener themouselistener;
    
      
    
    
    public arkanoidPanel()
    {
        init();
    }
   
    
    public void init()
    {   
        theBall =new Ball();
        thePaddle=new Paddle();
        theBrickMap=new brickMap(5,5);
        
        //---for mouse
        themouselistener = new myMouseMotionListener();
        addMouseMotionListener(themouselistener);
        
        running=true;
        
        image=new BufferedImage(ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT,BufferedImage.TYPE_INT_RGB);
        
        g=(Graphics2D)image.getGraphics();
       
        /**/g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
    }
    
    
    //--for collesion
    public void checkCollisions()
    {
        Rectangle ballRect =theBall.getRect();
        Rectangle paddleRect =thePaddle.getRect();
        
        if(ballRect.intersects(paddleRect))
        {
            //theBall.setY(thePaddle.y_pos - theBall.getBallSize() - 1);	
            theBall.setSpeed_Dy(-theBall.getSpeed_Dy());
            
        }
        
        
        Brick[][] arr= theBrickMap.getMap();
        A:for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if( arr[i][j].getCollisionState()>0){
                Rectangle brickRect = arr[i][j].getRect(j*(arr[i][j].getWidth())+theBrickMap.getHOR_PAD(), i*(arr[i][j].getHeight())+theBrickMap.getVER_PAD());
                
                if(ballRect.intersects(brickRect)){
                    if(arr[i][j].getCollisionState()<3)
                    arr[i][j].setCollisionState(arr[i][j].getCollisionState()-1);
                   
                    theBall.setSpeed_Dy(-theBall.getSpeed_Dy());
                    theBall.setSpeed_dx(-theBall.getSpeed_dx());
                
                        break A;
                }
                }
            }
        }
    }
    
    @Override
    public void update()
    {
        checkCollisions();
        theBall.update();
        thePaddle.update();    
    }
    
    
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT);
        theBall.draw(g);
        thePaddle.draw(g); 
        theBrickMap.draw(g);   
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g ;
        g2.drawImage(image,0, 0,ArkanoidGame.WIDTH,ArkanoidGame.HEIGHT,null);
        g2.dispose();      
    }
    
    
    public void run()
    {
        
        while(running)
        {
            //update
            update();
            
            //draw
            draw(g);
            
            //display
            repaint();
            
            try
            {
                Thread.sleep(30);
            }
            
            catch(Exception e)
            {
             e.printStackTrace();
            }
            
            
            
        }
    }
    
    private class myMouseMotionListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) 
                
        { 
            thePaddle.mousemoved(e.getX());
            
          
        }
    }
    
    
}
