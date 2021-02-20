/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res;

import java.awt.Graphics;
import java.awt.Image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author Arbab Ali
 */
public class ImageSource {
    	/**
	 * Keep track of pictures that have already been drawn so that we don't have to load them every time.
	 */
	private static Map<String, Image> cache = new HashMap<String, Image>();
        /**
	 * Draw an image.
	 *
	 * @param g The graphics context in which to draw the image.
	 * @param filepath The location of the image file.
	 * @param x The x-coordinate of where the upper-left corner of the image should be drawn.
	 * @param y The y-coordinate of where the upper-left corner of the image should be drawn.
	 */
	public static void draw(Graphics g, String filepath, int x, int y) {
		try {
			Image img;
			if (cache.containsKey(filepath))
				img = cache.get(filepath);
			else {
                               img= ResourceLoader.loadImage(filepath);
				cache.put(filepath, img);
			}
			g.drawImage(img, x, y, null);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
       public static Image getImage(String filePath)
       {
           
			Image img;
           try {
			if (cache.containsKey(filePath))
				img = cache.get(filePath);
                        
			else {
                               img= ResourceLoader.loadImage(filePath);
				cache.put(filePath, img);
			}
                       // System.out.println("IMG: "+ img.getSource());
			return img;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
           return null;
           
       }
       public static ImageIcon getIcon(String filePath)
       {
           return new ImageIcon( ImageSource.getImage(filePath));
       }
	

}
