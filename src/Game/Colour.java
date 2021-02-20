/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *  class Color to store name of color and color value 
 * @author Arbab Ali 
 */
import java.awt.Color;

public class Colour
{
    private  Color   color;
    private  String  name;

    public Colour(String name, Color color)
    {
        this.color = color;   this.name = name;
    }

    public Color color() { return color; }

    public String name() { return name; }

    @Override
    public String toString()    { return name; }
}
