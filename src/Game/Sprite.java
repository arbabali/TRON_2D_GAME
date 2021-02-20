/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * bass class Sprite to draw any object on screen and checks the intersection 
 * @author Arbab Ali
 */
public class Sprite {

    

    /**

     * The coordinates of the top left corner of the sprite

     */

    protected int x;

    protected int y;
 
    protected int width;

    protected int height;

    protected Image image;
    
    protected  Color color;


    public Sprite(int x, int y, int width, int height, Image image,Color color) {

        this.x = x;

        this.y = y;

        this.width = width;

        this.height = height;

        this.image = image;

        this.color=color;
    }

    
/**
 * draws the image file of sprite
 * @param g 
 */
    public void draw(Graphics g) {
        //System.out.println("img:"+image.getSource());
        g.drawImage(image, x, y, width, height, null);

    }

    /**
     * gets the color of Sprite
     * @return 
     */
    public Color getColor() {
        return color;
    }
    /**
     * sets the color of Sprite
     * @param color 
     */

    public void setColor(Color color) {
        this.color = color;
    }




    /**

     * Returns true if this sprite collides with the other sprite

     * @param other

     * @return 

     */

    public boolean collides(Sprite other) {

        Rectangle rect = new Rectangle(x, y, width, height);

        Rectangle otherRect = new Rectangle(other.x, other.y, other.width, other.height);        

        return rect.intersects(otherRect);

    }


    
    public int getX() {

        return x;

    }



    public void setX(int x) {

        this.x = x;

    }



    public int getY() {

        return y;

    }



    public void setY(int y) {

        this.y = y;

    }



    public int getWidth() {

        return width;

    }



    public void setWidth(int width) {

        this.width = width;

    }



    public int getHeight() {

        return height;

    }



    public void setHeight(int height) {

        this.height = height;

    }

    

}
