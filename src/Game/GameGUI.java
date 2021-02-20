/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;


import DB.HighScoreDB;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

import java.io.IOException;
import java.sql.SQLException;

import java.util.logging.Logger;
import Menu.optiondialog;
import Menu.namedialog;
import Menu.OKCancelDialog;
import res.ImageSource;

import res.AudioSource;

/**
 *
 * @author Arbab Ali
 */
public class GameGUI {

    

    private JFrame frame;

    private GameEngine gameArea;

   private Colour[]      colors = { 
                                   new Colour("Black", Color.black), new Colour("White", Color.white),
                                   new Colour("Gray", Color.gray), new Colour("Red", Color.red),
                                   new Colour("Green", Color.green), new Colour("Blue", Color.blue),
                                   new Colour("Yellow", Color.yellow), new Colour("Magenta", Color.magenta),
                                   new Colour("Cyan", Color.cyan) 
                                    };
      

 private optiondialog   optionDlg = new optiondialog(frame, "TRAILS color", colors, false);

 private namedialog     namesDlg = new namedialog(frame,"PLAYERS NAME");

    public GameGUI() throws IOException {

        frame = new JFrame("Tron Retro");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       /**
       * 
       *  Main Menu
       * 
       */
      // main menu panel
        
      AudioSource.playClip("src/res/data/Space.wav");
      final JPanel mainMenu = new JPanel(){
                @Override
    	  public void paintComponent(Graphics gc) {
    		  super.paintComponent(gc);
    		  ImageSource.draw(gc, "res/Data/mainMenu.png", 0, 0);
    	  }
      };
      final JFrame optionMenu =new JFrame();
      optionMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     // optionMenu.setD
      final JComponent backgroundPicture=new JComponent(){
                   @Override
    	  public void paintComponent(Graphics gc) {
    		  super.paintComponent(gc);
    		  ImageSource.draw(gc, "res/Data/play_menu.jpg", 0, 0);
    	  }
      };
      
      
      final JButton optionButton1=new JButton("PLAYER 1");
      final  JButton optionButton2=new JButton("PLAYER 2");
      final JButton doneButton=new JButton ("DONE");
     // final JButton cancelButton=new JButton("Cancel");
     final JPanel okCancelPanel =new JPanel();
      okCancelPanel.setLayout(new FlowLayout());
      okCancelPanel.setBackground(Color.DARK_GRAY);
      okCancelPanel.add(doneButton);
      //okCancelPanel.add(cancelButton);
      optionMenu.setLayout(new BorderLayout());
      optionMenu.add(backgroundPicture,BorderLayout.CENTER);
      optionMenu.add(optionButton1,BorderLayout.WEST);
      optionMenu.add(optionButton2,BorderLayout.EAST);
      optionMenu.add(okCancelPanel,BorderLayout.SOUTH);
      
 
      
      mainMenu.setLayout(new BorderLayout());
      mainMenu.setBackground(Color.BLACK);
       // main menu screen image
  	
    
      // panel for main menu buttons
      final JPanel topMenu = new JPanel();
      topMenu.setLayout(new FlowLayout());
      topMenu.setBackground(Color.DARK_GRAY);
      
      // buttons for main menu
      
	  final JButton play = new JButton("PLAY");

	  topMenu.add(play);
       
	  final JButton options = new JButton("OPTION");

	  topMenu.add(options);

    JButton quit = new JButton("QUIT");

	  mainMenu.add(topMenu,BorderLayout.SOUTH);
	   
	  // adds main menu panel to the frame
	  frame.add(mainMenu);
     
      gameArea = new GameEngine();   
      
      
      play.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
                       
                      
                            namesDlg.setModal(true);
                            namesDlg.pack();
                            namesDlg.setLocationRelativeTo(frame);
                            namesDlg.setVisible(true);
                           gameArea.setNames(namesDlg.getName1(),namesDlg.getName2()); 
                           GameEngine.ChoseRounds=(namesDlg.getLevels());
			   gameArea.restart();
                         
                           frame.remove(mainMenu);
                         frame.add(gameArea);
                         
			 frame.update(frame.getGraphics());
                         //frame.pack();
                         gameArea.requestFocusInWindow();
                         // frame.getContentPane().add(gameArea);
			 gameArea.revalidate();
                         
		  }
	  });
      
      
     options.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e){
                // frame.remove(mainMenu);
                 //frame.add(optionMenu);
                 optionMenu.setTitle("PLAYERS SETTING");
                  optionMenu.setSize(600,270);  
                 optionMenu.setLocationRelativeTo(frame);
                  optionMenu.setVisible(true);
                 
                 optionMenu.getFocusableWindowState();
                 optionMenu.revalidate();
             
                 }
                 }
     );
    optionButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
        {
            //optionDlg.setValue(inverse);
            optionDlg.setLocationRelativeTo(optionMenu);
            optionDlg.setVisible(true);
            
            if ( optionDlg.getButtonCode() != OKCancelDialog.OK ) return;

               System.out.println("BUTTON SELECTION: "+ optionDlg.color());
               gameArea.setPlayer1Color(colors[optionDlg.color()].color());
               
        }
    });
     optionButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
        {
            //optionDlg.setValue(inverse);
            optionDlg.setLocationRelativeTo(optionMenu);
            optionDlg.setVisible(true);
            
            if ( optionDlg.getButtonCode() != OKCancelDialog.OK ) return;
                
               gameArea.setPlayer2Color(colors[optionDlg.color()].color());
               System.out.println("BUTTON SELECTION: "+ optionDlg.color());

        }
    });
    doneButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e)
        {
             optionMenu.setVisible(false);
             optionMenu.setLocationRelativeTo(frame);
             //optionDlg.transferFocusBackward();
        }
    });
	  
      topMenu.add(quit);
            quit.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  System.exit(1);
		  }
	  });
	  
        JMenuBar menuBar = new JMenuBar();

        frame.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game");

        menuBar.add(gameMenu);

        JMenuItem restartMenuItem = new JMenuItem("Restart");

        gameMenu.add(restartMenuItem);

        restartMenuItem.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent ae) {
                //gameArea.endGame();
                AudioSource.playClip("src/res/data/InsertCoin.wav");
                gameArea.restart();

            }

        });

        

        JMenuItem highscoreMenuItem = new JMenuItem("Highscores");

        gameMenu.add(highscoreMenuItem);

                highscoreMenuItem.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent ae) {

                JFrame newFrame= new JFrame();
                newFrame.setTitle("HIGH SCORE TABLE !");
               JLabel contentPane = new JLabel();
              contentPane.setIcon( ImageSource.getIcon("res/Data/highscore2.png") );
                contentPane.setLayout( new GridLayout(6,2) );
              JLabel header= new JLabel("10!           ");
              JLabel empty=new JLabel("                          TOP");
             
                 header.setFont(new Font("Dialog", Font.BOLD, 25));
                 empty.setFont(new Font("Dialog", Font.BOLD, 25));
                 empty.setForeground(Color.red);
                 header.setForeground(Color.red);
                
                  contentPane.add(empty);
                 contentPane.add(header);
                 
              
                DefaultListModel<String> l1 = new DefaultListModel<>();                         
                
                AudioSource.playClip("src/res/data/Scores.wav");
                try{
                    HighScoreDB highScores=new HighScoreDB( 10);
                    highScores.getHighScoreDB();
                    
                            for(Integer i=1;i<=highScores.size;i++)
                                 {
                                     if(i>10)
                                        break;
                                    JLabel elem=new JLabel( i.toString()+ "->"+ highScores.getHighScoreDB().get(i-1).toString());
                                    //elem.setBackground(Color.yellow);
                                    elem.setFont(new Font("Dialog", Font.BOLD, 20));
                                    elem.setForeground(Color.YELLOW);
                                    contentPane.add(elem );
                                    //System.out.println(highScores.getHighScores().get(i).toString());
                                 }
                     
                }                 
                         catch (SQLException exl)

                    {

                        Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE,null,exl);

                    }

                

                

                

                JList<String> list = new JList<>(l1);  

                list.setBounds(100,100, 75,75);  

                //highScore.add(list);  
                //highScore.setVisible(true);
                //newFrame.add(highScore);
                newFrame.add(contentPane);
                newFrame.setSize(400,400);                 
                newFrame.pack();
                newFrame.setLocationRelativeTo(frame);
                newFrame.setVisible(true);
                newFrame.setResizable(false); 
                
                          
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }

        });

        
        JMenuItem backtoMainItem=new JMenuItem("MAIN MENU");
       
        gameMenu.add(backtoMainItem);
        
        backtoMainItem.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                       frame.remove(gameArea);
                       frame.add(mainMenu);
                       frame.update(frame.getGraphics());
                         mainMenu.requestFocusInWindow();
                         // frame.getContentPane().add(gameArea);
			  mainMenu.revalidate();
                    }
                });
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        
        gameMenu.add(exitMenuItem);

        exitMenuItem.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent ae) {

                System.exit(0);

            }

        });

        frame.setPreferredSize(new Dimension(800, 600));
  
        frame.setResizable(false);

        frame.pack();
        
        frame.setVisible(true);

    }

        

    

}