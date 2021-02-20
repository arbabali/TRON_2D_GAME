/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Game.Colour;
import Menu.OKCancelDialog;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.*;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * class to chose  Color of trail of player
 * @author Arbab Ali
 */
public class optiondialog extends OKCancelDialog {
      // private JCheckBox       inverse;
    private JRadioButton[]  colorButtons;
    private int             selected;
    
    public optiondialog(JFrame frame, String name, Colour[] colors, boolean inv)
    {
        super(frame,name);
       // inverse = new JCheckBox("Invert text color", inv);
        ButtonGroup csoport = new ButtonGroup();
        JPanel      panel = new JPanel(new GridLayout(colors.length / 3, 3));
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Trails  color : "));
        colorButtons = new JRadioButton[colors.length];
        for ( int i = 0; i < colors.length; i++ )
        {
            colorButtons[i] = button(colors[i]);
            panel.add(colorButtons[i]);
            csoport.add(colorButtons[i]);
        }
        setLayout(new BorderLayout());
      //  add("North", inverse);
        add("Center", panel);
        add("South", btnPanel);

        pack();
        setResizable(false);
    }

    public void setValue(boolean inv)
    {
      //  inverse.setSelected(inv);
    }

    

    public int color()                   { return selected; }

    @Override
    protected boolean processOK()
    {
      //  if ( inverse.isSelected() )  return true;
        for ( int i = 0; i < colorButtons.length; i++ )
            if ( colorButtons[i].isSelected() )
            {
                selected = i;
                return true;
            }
        return false;
    }

    @Override
    protected void processCancel()      {}

    private JRadioButton button(Colour sz)
    {
        BufferedImage   img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics        g = img.getGraphics();
        g.setColor(sz.color());
        g.fillOval(0, 0, 16, 16);
        JRadioButton    b = new JRadioButton(sz.name(), new ImageIcon(img));
        img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        g = img.getGraphics();
        g.setColor(sz.color());
        g.fillOval(0, 0, 16, 16);
        if ( sz.color().equals(Color.black) )    g.setColor(Color.white);
        else                                    g.setColor(Color.black);
        g.fillOval(4, 4, 8, 8);
        b.setSelectedIcon(new ImageIcon(img));
        return b;
    }

  
}
