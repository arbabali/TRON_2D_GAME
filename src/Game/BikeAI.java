/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Arbab Ali
 */
class BikeAI extends Bike {
   
    // the number of steps before a random turn
    private int random_turn = 40;
    
    // the list of other players on the court
    private Bike[] players = new Bike[1];
        
    private Random rand = new Random();
    
    private  Integer bottomBound=580;
    private Integer rightBound=800;
    
    public BikeAI(Integer number,int x, int y, int width, int height, Image image,Color color){
        super("AI",number,x, y, width, height, image,color);
       
        players[0]=this;
        
    }

    // must be called so that the AI knows where trails are
	public void addPlayers(Bike[] players) {
		this.players = players;
	}
        public void reactProximity() {
		double velocity = Math.max(Math.abs(this.getVelx()), Math.abs(this.getVely()));
		
		// boosts randomly
		int r = rand.nextInt(100);
		if (r == 1) {
		//	startBoost();
		} 
                
               // adds all lines of all players to one list
               ArrayList<LightTrace> paths = new ArrayList<LightTrace>();
		paths.addAll(this.getPath());
		Collections.reverse(paths);
		for (Bike p: players) {
			if (p != this) {
				paths.addAll(p.getPath());
			}
		}
                
                
                for (int i=paths.size()-1;i>0;i--)
                {
                    LightTrace t=paths.get(i);
                    int maxX = Math.max(t.getX1(), t.getX2());
                    int minX = Math.min(t.getX1(), t.getX2());
                    int maxY = Math.max(t.getY1(), t.getY2());
                    int minY = Math.min(t.getY1(), t.getY2());
		
                    // if there is a line in the path, checks if there is one adjacent
                    
                    if(this.getVelx()>0 && t.isVertical() && y>=minY && x<=minX)
                    {
                        if (t.getX1() -x<6 && t.getX1()-x>0)
                        {
                            boolean b= false;
                            for(int j=paths.size()-1;j>0;j--)
                            {
                                LightTrace k = paths.get(j);
                                
                                if(!k.isVertical() && y-k.getY2()<6 && y-k.getY2()>0)
                                    b=true;
                            }
                            
                            //reacts appropriately
                            if(b) 
                            {
                                this.setVely(velocity);
                                
                            }else{
                                this.setVely(-velocity);
                            }
                            
                            this.setVelx(0);
                            random_turn=40;
                            return;
                        }
                    }
                    // if there is line in the path , checks if there is one adjacent
                    
                    if (this.getVelx()<0 && t.isVertical()&&y>=minY&&x<=maxY)
                    {
                    
                        if(x-t.getX1()<6 && x-t.getX1()>0)
                        {
                            boolean b=false;
                            for(int j=paths.size()-1;j>0;j--)
                            {
                               LightTrace k=paths.get(j);
                               
                               if(k.isVertical() && x-k.getX2()<6 && x-k.getX2()>0)
                               {
                                   b=true;
                               }
                            }
                            //reacts appropriately
                            if(b)
                            {
                                this.setVely(velocity);
                            }else{
                                this.setVely(-velocity);
                            }
                            this.setVelx(0);
                            random_turn=40;
                            return;
                        }
                    }
                    // if there is a line in the path, checks if there is one adjacent
			if (this.getVely() < 0 && !t.isVertical() && x >= minX && x <= maxX) {
				if (y - t.getX1()< 6 && y - t.getX1()> 0) {
					boolean b = false;
					for (int j = paths.size() - 1; j > 0; j--) {
						LightTrace k = paths.get(j);
						if (k.isVertical() && x - k.getX2() < 6 && 
								x - k.getX2() > 0) {
							b = true;
						}
					}
					
					// reacts appropriately
					if (b) {
						this.setVelx(velocity);
					} else {
						this.setVelx(-velocity);
					}
					this.setVely(0);
					random_turn = 40;
					return;
				}
			}
		}
                // checks if the Player is too close to the edge
		if (x < 6 && this.getVelx() != 0) {
			if (y < 250) {
				 this.setVely(velocity);
			} else {
				 this.setVely(-velocity);
			}
			this.setVelx(0);
			random_turn = 40;
			return;
		} 
		if (800 - x < 6 && this.getVelx() != 0) {
			if (y < 250) {
				 this.setVely(velocity);
			} else {
				 this.setVely(-velocity);
			}
			this.setVelx(0);
			random_turn = 40;
			return;
		} 
		if (y < 6 && this.getVely() != 0) {
			if (x < 250) {
				this.setVelx(velocity);
			} else {
				this.setVelx(-velocity);
			}
			this.setVely(0);
			random_turn = 40;
			return;
		} 
		if (580 - y < 6 && this.getVely() != 0) {
			if (x < 250) {
				this.setVelx(velocity);
			} else {
				this.setVelx(-velocity);
			}
			this.setVely(0);
			random_turn = 40;
			return;
		}
		// moves randomly if all others do not 
		// cause the Player to change direction
		if (random_turn == 0) {
			int rando = rand.nextInt(4);
			if (rando == 0 && this.getVelx() != velocity) {
				if (x > 6) {
					this.setVelx(-velocity);
					this.setVely(0);
				}
			} else if (rando == 1 && this.getVelx() != -velocity) {
				if (rightBound - x > 6) {
						this.setVelx(velocity);
					this.setVely(0);
				}
			} else if (rando == 2 && this.getVely() != velocity) {
				if (y > 6) {
						this.setVelx(0);
					this.setVely(-velocity);
				}
			} else if (rando == 3 && this.getVely() != -velocity) {
				if (bottomBound - y > 6) {
					this.setVelx(0);
					this.setVely(velocity);
				}
			}
			random_turn = 40;
		}	
                random_turn--;	
                }
                // moves the Player based on its conditions
	public void move() {		
		int a = x;
		int b = y;
		reactProximity();
		//boost();
	
		super.move();
	}
        

}
        
  

