package arkanoid.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
public class PowerUp {
    //private  int idPowerUp, duration;
    //private int retrievalState;
    //________________________fields____________________________________________
    private  int x,y,dy,type,width,height;
    private  boolean isOnscreen;
    private  Color color;
    public  final static  int WidePaddle=4;
    public  final static int FastBall=5;
    public  static final Color Widecolor=Color.GREEN;
    public  static  final Color fastColor=Color.RED;
    //________________________constractor________________________________________
    public  PowerUp(int xStart,int yStart,int theType,int theWidth,int theHeight)
    {
        x=xStart;
        y=yStart;
        type=theType;
        width=theWidth;
        height=theHeight;
        if(type<4){type=4;}
        if(type<5){type=5;}
        if(type==WidePaddle){color=Widecolor;}
        if(type==FastBall){color=fastColor;}
        dy=(int)(Math.random()*6+1);//3 is range &1 is start
    }
    //________________________functions__________________________________________
    public void setX(int newx) {
        x = newx;
    }
    public int getX() {
        return x;
    }
    
    public void draw(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    } 
    
    public void Update()
    {
        y+=dy;
        if(y>ArkanoidGame.WIDTH)
        {
            isOnscreen=false;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDy() {
        return dy;
    }

    public int getY() {
        return y;
    }
    public  boolean getIsOnScreen()
    {
        return isOnscreen;
    }

    public void setIsOnscreen(boolean isOnscreen)
    {
        this.isOnscreen = isOnscreen;
    }
    public  Rectangle getRect()
    {
        return new Rectangle(x,y,width,height);
    }
}
