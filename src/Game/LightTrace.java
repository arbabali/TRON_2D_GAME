/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Graphics;



/**
 *
 * @author Arbab Ali
 */

   public class LightTrace extends Sprite {

    
       // positions of the start and end of the line
	
	private int x2;
	private int y2;
        public static int size=5;
       
	

    /**
     * creates the lightTrace instance also calls the sprite constructor and sves the width and hieght as x2 and y2
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color 
     */
    public LightTrace(int x, int y, int width, int height,Color color) {
        super(x,y,width,height,null,color);
        
        this.x2 = width;
        this.y2 = height;
        
        
    }


   /**
    * draws the line 
    * @param gc 
    */
    public void draw(Graphics gc) {
            //   Graphics2D g = (Graphics2D) gc;
             //  g.setStroke(new BasicStroke(5));
                //gc.drawRect(x1, y1-30, 5, 5);
             //   gc.fillRect(x1, y1, size, size);
               gc.setColor(color);
               // gc.drawLine(x1,y1 , x2, y2);
                gc.drawRect(this.x, this.y, this.width, this.height);
                
		//g.drawLine(x1, y1, x2, y2);
                
    }
    

	
    public int getX1() {
        return x;
    }

    public void setX1(int x1) {
        this.x = x1;
    }

    public int getY1() {
        return y;
    }

    public void setY1(int y1) {
        this.y = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    // checks if the line is vertical
	public boolean isVertical() {
		return (x == x2);
	}  

    

} 

