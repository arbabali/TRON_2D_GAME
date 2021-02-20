/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 * Dialog before the game start to store names and games rounds
 * @author Arbab Ali
 */

import javax.swing.*;
import java.awt.*;



public class namedialog extends OKDialog
{
    private JTextField  name1;
    private JTextField name2;
    private JPanel rounds;
    private JRadioButton[]  roundsButton;
    ButtonGroup buttonGroup = new ButtonGroup();
    public namedialog(JFrame frame, String title)
    {
        super(frame, title);
        name1 = new JTextField("Player1");
        name2=new JTextField("Player2");
        rounds=new JPanel ();
         roundsButton=new JRadioButton[3];
        roundsButton[0]=new JRadioButton("3",true);
        roundsButton[0].setActionCommand("3");
        
        roundsButton[1]=new JRadioButton("5",false);
         roundsButton[1].setActionCommand("5");
         
        roundsButton[2]=new JRadioButton("7",false);
        roundsButton[2].setActionCommand("7");
       rounds.add(new JLabel("ROUNDS"));
       
       buttonGroup.add(roundsButton[0]);
       buttonGroup.add(roundsButton[1]);
       buttonGroup.add(roundsButton[2]);
     
       rounds.add(roundsButton[0]);
       rounds.add(roundsButton[1]);
       rounds.add(roundsButton[2]);
        
        setLayout(new GridLayout(4,1));
        
        add(name1);
        add(name2);
        add(rounds);
        add(btnPanel);
        pack();
        //resize(500,200);
        setResizable(false);
    }
    public int getLevels(){
        
      // System.out.println( "SELECTED LEVELS:" + buttonGroup.getSelection().getActionCommand());
      return Integer.parseInt(buttonGroup.getSelection().getActionCommand());
    }
    public String getName1()        { return name1.getText(); }
    public String getName2()        { return name2.getText(); }
    
       
    @Override
    protected boolean processOK()   {  getLevels(); return true; }

   
}
