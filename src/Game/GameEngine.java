/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import DB.HighScoreDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

import res.ResourceLoader;

/**
 *
 * @author Arbab Ali
 */

public class GameEngine extends JPanel {

    

    private final  int FPS = 100;

    private final int MOTOR_VELOCITY = 2;

   
   private Integer score1=0;
   private Integer score2=0;
   
   HighScoreDB highScores;

    

   private boolean paused = false;

   private final Image background;
    
   static Integer ChoseRounds=0;
   private int levelNum=3 ;

  
   
    private Bike player1;
    private BikeAI player2;
 
    private Color player1Color=Color.YELLOW;
    private Color player2Color=Color.RED;
    private String player1Name="Player1";
    private String player2Name="Player2";

    private final Timer newFrameTimer;
    private int random_turn = 40;
     private Random rand = new Random();
    /**
     * checks the keystrokes and takes coresponding actionPerformed and  creates instance of newFrameListener
     */

    public GameEngine() {

        super();
         
        background = new ImageIcon("src/res/Data/background2.png").getImage();
       
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             
                player1.setVelx(-MOTOR_VELOCITY);
           
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                player1.setVelx(MOTOR_VELOCITY);
              
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             
                player1.setVely(MOTOR_VELOCITY);
            
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                player1.setVely(-MOTOR_VELOCITY);
              
            }
        });
        
//       int r = rand.nextInt(100);
//		if (r == 1) {
//                     player2.setVelx(-MOTOR_VELOCITY);
//		} 
//        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed d");
//        this.getActionMap().put("pressed d", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                player2.setVelx(-MOTOR_VELOCITY);
//   
//            }
//        });
//        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed a");
//        this.getActionMap().put("pressed a", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                player2.setVelx(MOTOR_VELOCITY);
//    
//            }
//        });
//        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed s");
//        this.getActionMap().put("pressed s", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//               
//                player2.setVely(MOTOR_VELOCITY);
//               
//            }
//        });
//        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed w");
//        this.getActionMap().put("pressed w", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                
//                player2.setVely(-MOTOR_VELOCITY);
//              
//            }
//        });
//       
        
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });

        play();

        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());

        newFrameTimer.start();

        

        



    }

     private void play(){
           if(ChoseRounds!=0)  levelNum=ChoseRounds;
          restart();
     }


    
    private void setColor(Bike player,Color c)
    {
        player.setColor(c);
    }
    /**
     * sets the player 2 color that chosen in menu
     * @param c 
     */
    public void setPlayer2Color(Color c)
    {
       player1Color=c;
    }
    /**
     * sets the player 1 color that chosen in menu
     * @param c 
     */
    public void setPlayer1Color(Color c)
    {
      player2Color=c;
    }
    /**
     * sets the names of players before game starts 
     * @param p1
     * @param p2 
     */
    public void setNames(String p1,String p2)
    {
        player1Name=p1;
        player2Name=p2;
    }
    /**
     * shows end game  score dialog and stores  maximum score in database with the player name   and resets the scores
     */
    public void endGame() 

    {
       
       JOptionPane.showMessageDialog(this, " FinalScores!\n Name: " + player1.getName() + " Score : " +score1.toString()+
                                            "\nName: "+ player2.getName()+" Score : "+ score2.toString());
       
       
        String winnerName="" ;
        Integer winnerScore=0;
       if(score1>score2)
       {
           winnerName=player1.getName();
           winnerScore=score1;
       }else if(score1<score2) {
           winnerName=player2.getName();
           winnerScore=score2;
       }

       
       
     try {

              HighScoreDB highScores = new HighScoreDB(10);
            if(winnerScore>0)
                highScores.putHighScore(winnerName, winnerScore );
           System.out.println(highScores.getHighScoreDB());

        } catch (SQLException ex1) {

            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);

        }

      
        score1=0;
        score2=0;
        
       
    }

    

        
    /**
     * restarts the game by putting player in first positions 
     */
    public void restart() {


//        livesLabel=new Score(2,levelNum,levelNum);
        
        player1=initPlayer(player1Name,1,player1Color,new Point(350,450));
        player2=initPlayerAI(player2Name,2,player2Color,new Point(350,50));
        
        
   
    }
    /**
     * initilize a player and also chose the image of motor on the basis of playerNO ( to enable different color bikes possible)
     * @param name
     * @param playerNo
     * @param c
     * @param loc
     * @return 
     */
    private Bike initPlayer(String name,Integer playerNo,Color c,Point loc)
    {
         String path ="src/res/Data/motor"+playerNo.toString()+".png";
          Image bikeImage=new ImageIcon(path).getImage();
        return  new Bike(name,playerNo,loc.x,loc.y,30,50,bikeImage,c);
    }
    private BikeAI initPlayerAI(String name,Integer playerNo,Color c,Point loc)
    {
         String path ="src/res/Data/motor"+playerNo.toString()+".png";
          Image bikeImage=new ImageIcon(path).getImage();
        return  new BikeAI(playerNo,loc.x,loc.y,30,50,bikeImage,c);
    }

    /**
     * draws the background image and also calls the players draw function on the Jpanel 
     * @param grphcs 
     */
    @Override

    protected void paintComponent(Graphics grphcs) {

        super.paintComponent(grphcs);

        grphcs.drawImage(background, 0, 0, 800, 600, null);

        player1.draw(grphcs);
        player2.draw(grphcs);
        //this.add(livesLabel);
 

    }
   
    /**
     * newFrame listener  moves the players if not paused game and checks collison if collides then reduce levelRounds and also player a boom.wav sound effect
     * also shows continue dialog window
     */
    class NewFrameListener implements ActionListener {



        @Override

        public void actionPerformed(ActionEvent ae) {

            if (!paused)
            {
           
                player1.move();
                player2.move();
                 if(player1.collision(player2)  )      
                {
                    try {
                        Clip clip=  ResourceLoader.loadClip("src/res/data/atari_boom.wav");
                        clip.start();
                        //paused=true;
                    } catch (Exception ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        System.out.println("LEVEL NO:"+levelNum);
                        System.out.println("COLLISION OCCUR,"+player2.getName() +" wins ");
                        levelNum-=1;
                        
                        restart();
                        score2++;
                    
                    
                }
                 else if( player2.collision(player1) )
                {
                    try {
                        Clip clip=  ResourceLoader.loadClip("src/res/data/atari_boom.wav");
                        clip.start();
                        //paused=true;
                    } catch (Exception ex) {
                        Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     System.out.println("LEVEL NO:"+levelNum);
                    System.out.println("COLLISION OCCURS," +player1.getName() +" wins ");
                     
                        player1.setScore(player1.getScore()+1);
                         levelNum-=1;
                        restart();
                        score1++;
                   //  paused=true;
                    
                }
          

                if (levelNum<=0) 
                {
                  
                   // System.out.println("scores:\n"+player1.getName()+":"+player1.getScore()+"\n"+player2.getName()+" : "+player2.getScore());
                    levelNum=ChoseRounds;
                    Integer integerOption=JOptionPane.showConfirmDialog(null,"Name: " + player1.getName() + " Score : " +score1.toString()+
                                            "\nName: "+ player2.getName()+" Score : "+ score2.toString()+"\n" +"DO you want to Continue with Same " + levelNum+ " rounds Game ?","GAME OVER!",JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("INTEGER OPTION:"+integerOption.toString());
                    if(integerOption!=0)
                     endGame();
                    else{
                        restart();
                    }
                                  
                }
             }
                  repaint();





        }
    }

    

}