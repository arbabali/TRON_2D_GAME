/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import res.ImageSource;

/**
 *
 * @author Arbab Ali
 */
public class BaseBike extends Sprite {
    private double velx;
    private double vely;
    private final ArrayList<LightTrace> trail=new ArrayList<>();
    private String name;
    private int score;
    private final Integer playerNo;
    private boolean isAlive;
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 /**
  * constructor to construct a Bike Instance (Initial velocity is ZERO)
  * @param name
  * @param number
  * @param x
  * @param y
  * @param width
  * @param height
  * @param image
  * @param color 
  */
    public BaseBike(String name,Integer number,int x, int y, int width, int height, Image image,Color color) {
        super(x, y, width, height, image,color);
        this.name=name;
        this.velx = 0;
        this.vely = 0;
        
        this.playerNo=number;
        this.isAlive=true;
    }

    /**
     * CHANGE THE DIRECTION OF BIKE SPRITE BY CHANGE THE @this.image to new rotated image
     */
    public void change()
        
    
    {
         Direction d=Direction.Z;
        if(this.velx<0.0)
            d=Direction.L;
        else if(this.velx>0.0)
            d=Direction.R;
        else if(this.vely<0.0)
            d=Direction.U;
        else if(this.vely>0.0)
        {
           d=Direction.D;
        }
           
            this.image=ImageSource.getImage("res/Data/motor"+this.playerNo.toString()+(d.toString())+".png");
        
    }

    /**
     * CHECKS THE COLLISION OF BIKE sprites with other bike or with others bike sprite and  LightTrace collision
     * @param other
     * @return 
     */
    public boolean collision(BaseBike other)
    {
        //System.out.println("TOUCHS OWN LINE :  ? "+ this.getPath().size() );
        if(!isAlive)
            return true;
     if(other !=this)
     {
        if(this.collides(other)){
            System.out.println("BIKES COLLISON");
             this.image=ImageSource.getImage("res/Data/crash.png");
            return true;
        }
        ArrayList<LightTrace> path = other.getPath();
        
       
        for(LightTrace p:path){
              
           if (p.collides(this)){
                  this.image=ImageSource.getImage("res/Data/crash.png");
          
               return true;
           }
        }
       
        
        return false;
     }
     return false;
    }
  
    /**
     * add a new co-Ordinates (x,y) in the LightTrace instance array with same size and color also checks if the same point exist in Arraylist then change isAlive status
     */
    public void addTrace()
    {
        int a = x;
        int b = y;
        
        LightTrace t=new LightTrace(a, b, LightTrace.size, LightTrace.size,this.getColor());
        ArrayList<LightTrace> pa = this.getPath();
       // pa.forEach((x)->System.out.println("lettssee "+x.collides(t)));   
       for(LightTrace p:pa)
       {
        if(p.getX1()==t.getX1() && p.getY1()==t.getY1())   
             this.isAlive=false;
             //return ;
       }
       
  
       if(!pa.contains(t))
        {   
                            trail.add(t);
	
        }
		
        
    }
        /**
         * Moves the sprite Bike in x direction also checks the bound as well and if true then change isAlive status to false
         */
        public void moveX() {

            x += velx;
            if (x + width >= 800 || x <= 0) {
                //return false;
                isAlive=false;
                //invertVelX();
            }
           // trail.add(new LightTrace(x1,y,x,y));
           if(velx!=0){


               addTrace();

           }
           //return true;
        }
/**
     * Moves the sprite Bike in Y direction also checks the bound as well and if true then change isAlive status to false
     */
    public void moveY() {

        y += vely;
        if (y <= 0 || y +height >= 580) {
            //return false;
            isAlive=false;
            //invertVelY();
        }
       
      
      // trail.add(new LightTrace(x,y1,x,y));
      if(vely!=0)
      {
       
   
        addTrace();
         
      }
        //return true;  
       // return true;
    }
    /**
     * Moves the sprite Bike which calls moveX() 0and moveY()
     */
      public void move() {
          
          
        moveX();
          
        moveY();
         
        
    }
     /**
      * draws the the Bike sprite and also its LightTrace trail
      * @param g 
      */
    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
     for (LightTrace k: trail) {
			k.draw(g);
		}   
   
    }
 

    /**
     * return the Arraylist of lightTrae
     * @return 
     */
    public ArrayList<LightTrace> getPath() {
        return trail;
	}
    
    /**
     * gets the value of x velocity
     * @return 
     */
    public double getVelx() {
        return velx;
    }
/**
     * gets the value of y velocity
     * @return 
     */
    public double getVely() {
        return vely;
    }
    /**
     * set the value of y velocity only if velocity y is ZERO (to have linear motion)
     * @return 
     */
    public void setVely(double vely) {
        this.velx=0;
        System.out.println("VELOCITY Y:"+this.getVelx()+","+this.getVely());
        if(this.vely==0){
          
            this.vely = vely;
            this.change();
        }
    }

    /**
     * set the value of x velocity only if velocity x is ZERO (to have linear motion)
     * @return 
     */
    public void setVelx(double velx) {
        this.vely=0;
        System.out.println("VELOCITY X:"+this.getVelx()+","+this.getVely());
        if(this.velx==0)
        {
            
            this.velx = velx;
            this.change();
         }
    }
    /**
     * inverts  velocity x direction to bounce back(required to check bounds only)
     */
    public void invertVelX() {
        velx = -velx;
    }

    /**
     * inverts  velocity y direction to bounce back(required to check bounds only)
     */
    public void invertVelY() {
        vely = -vely;

    }

}
