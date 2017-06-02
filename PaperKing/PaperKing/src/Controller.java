import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.net.URL;
import java.util.*;
/**
 * Write a description of class Controller here.
 * 
 * @Daniel Flohr & Jeffrey Chen (your name) 
 * @Alpha 1.7.23 (a version number or a date)
 */
public class Controller
{
    private RougueGUI myGUI;
    private int xCord, yCord, hp, gridRows, gridCols, mapRows, mapCols, enemiesLeft, movesLeft;
    private Model[][] map;
    private ImageIcon playerImg, enemyImg, floorImg, obstacleImg, healthImg, arrowImg, superEnemyImg, exitImg;

    public Controller()
    {
    	loadImages();
    	gridRows = 9;
    	gridCols = 12;
    	mapRows = 30;
    	mapCols = 40;
        
    }
    
    private void loadImages()
    {
    	ClassLoader cldr = this.getClass().getClassLoader();
        URL tempURL = cldr.getResource("Main.png");
        playerImg = new ImageIcon(tempURL);
        playerImg.setImage(playerImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Enemy.png");
        enemyImg = new ImageIcon(tempURL);
        enemyImg.setImage(enemyImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Blank.png");
        floorImg = new ImageIcon(tempURL);
        floorImg.setImage(floorImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Wall.png");
        obstacleImg = new ImageIcon(tempURL);
        obstacleImg.setImage(obstacleImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Health.png");
        healthImg = new ImageIcon(tempURL);
        healthImg.setImage(healthImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Projectile.png");
        arrowImg = new ImageIcon(tempURL);
        arrowImg.setImage(arrowImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Harbinger.png");
        superEnemyImg = new ImageIcon(tempURL);
        superEnemyImg.setImage(superEnemyImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
        tempURL = cldr.getResource("Exit.png");
        exitImg = new ImageIcon(tempURL);
        exitImg.setImage(exitImg.getImage().getScaledInstance(100, 85, Image.SCALE_SMOOTH));
    }
    
    public void newGame()
    {
    	xCord = 0;
    	yCord = 0;
    	hp = 100;
    	movesLeft=50;
    	enemiesLeft=0;
        map = genMap();
    }
    
    public void setGUI(RougueGUI gui)
    {
        myGUI = gui;
        myGUI.updateEnemies(enemiesLeft); // display on the bottom of screen total enemies
    }
    
    public int getGridRows()
    {
    	return gridRows;
    }
    
    public int getGridCols()
    {
    	return gridCols;
    }
    
    private int playerX()
    {
	    return xCord+gridCols/2; // returns the xCord of the player on the map
    }
    
    private int playerY()
    {
    	return yCord + gridRows/2; // returns the yCord of the player on the map
    }
    
    public void doFight(Model enemy)
    {
    	hp -= ((Enemy) enemy).attack(); // lowers player hp
    	myGUI.updateHp(hp); // and updates change to GUI
    }
    
    public Model getMap(int r, int c)
    {
    	return map[r][c];
    }
    
    public Model[][] genMap()// to be changed because this is not very good at generating a map
    {
    	Model[][] myMap = new Model[mapRows][mapCols]; 
    	for(int a = 0; a < mapRows; a++)
    	{
    		for(int b = 0; b< mapCols; b++)
    		{
    	    	if(a<playerY() || a >= mapRows-playerY() || b<playerX() || b >= mapCols-playerX())
    	    	{
    	    		myMap[a][b] = new Obstacle(b,a,obstacleImg);
    	    	}
    			else
    	    	{
    				if((int)(Math.random() * 30) == 0)
    				{
    					myMap[a][b] = new HealthPack(b,a,healthImg);
    				}
    				else if((int)(Math.random() * 10) == 0)
	    	    	{
	    	    		myMap[a][b] = new Enemy(b,a,false,enemyImg);
	    	    		enemiesLeft++;
	    	    	}
	    	    	else if((int)(Math.random() * 6) == 0)
	    	    	{
	    	    		myMap[a][b] = new Obstacle(b,a,obstacleImg);
	    	    	}
	    	    	else
	    	    	{
	    	    		myMap[a][b] = new Model(b,a,floorImg);
	    	    	}
    	    	}
    		}
    	}
    	myMap[gridRows/2][gridCols/2] = new Player(gridRows/2,gridCols/2,playerImg);//put player in top left corner
    	myMap[gridRows/2][gridCols/2+1] = new Model(gridRows/2,gridCols/2+1,floorImg);//clears area near player to prevent spawn trapping
    	myMap[gridRows/2+1][gridCols/2] = new Model(gridRows/2+1,gridCols/2,floorImg);//clears area near player to prevent spawn trapping
    	
    	myMap[mapRows-gridRows/2-1][mapCols-gridCols/2-1] = new Model(gridRows/2-1,gridCols/2-1,exitImg);//puts exit in the bottom right
    	myMap[mapRows-gridRows/2-1][mapCols-gridCols/2-2] = new Model(gridRows/2-1,gridCols/2-2,floorImg);//clears area near Exit to prevent exit trapping
    	myMap[mapRows-gridRows/2-2][mapCols-gridCols/2-1] = new Model(gridRows/2-2,gridCols/2-1,floorImg);//clears area near Exit to prevent exit trapping
    	return myMap;
    }

    public Model getEmptySpace(int a, int b)
    {
    	return new Model(a,b,floorImg);
    }
    
    public Model getNewProjectile(int a, int b, Direction dir)
    {
    	return new Projectile(a,b,dir,arrowImg);
    }
    
    public Direction doMove(Model m)
    {
    	if(m.getEntity().equals("enemy"))
    	{
	    	return new Direction((int) Math.signum(playerY() - m.getY())*-1,(int) Math.signum(playerX() - m.getX())); // enemy moves toward the player
    	}
    	else if(m.getEntity().equals("projectile"))
    	{
    		return ((Projectile) m).getDirection();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    public boolean hurtEnemy(int y, int x, int damage)
    {
    	if(!((Enemy) map[y][x]).damage(damage))
		{
			if(!((Enemy) map[y][x]).getSpecial())
			{

				myGUI.updateEnemyBar(0,((Enemy) map[y][x]).getSpecial());
				enemiesLeft--;
				myGUI.updateEnemies(enemiesLeft);
			}
			map[y][x] = getEmptySpace(y,x);
			myGUI.getGridSpot(y-yCord,x-xCord).setIcon(map[y][x].getImg());
			return true;
		}
		else
		{
			myGUI.updateEnemyBar(((Enemy) map[y][x]).getHP(),((Enemy) map[y][x]).getSpecial());
			return false;
		}
    }
    
    public void fireProjectile(Direction pDir)
    {
    if(xCord+pDir.getX()<map[0].length-gridCols && xCord+pDir.getX()>=0 && yCord-pDir.getY()<=map.length-gridRows && yCord-pDir.getY()>=0 && !map[playerY()-pDir.getY()][playerX()+pDir.getX()].getEntity().equals("projectile")) // moving
    {
    	enemyMoves();
    	if(!map[playerY()-pDir.getY()][playerX()+pDir.getX()].isColidable())
    	{
	        map[playerY()-pDir.getY()][playerX()+pDir.getX()]=getNewProjectile(playerY()-pDir.getY(),playerX()+pDir.getX(),pDir);
			myGUI.getGridSpot((gridRows/2)-pDir.getY(),(gridCols/2)+pDir.getX()).setIcon(map[playerY()-pDir.getY()][playerX()+pDir.getX()].getImg());
    	}
    	else if(map[playerY()-pDir.getY()][playerX()+pDir.getX()].getEntity().equals("enemy"))
		{
    		hurtEnemy(playerY()-pDir.getY(),playerX()+pDir.getX(),15);
		}
    }
 }
    
    public void makeMoves(Direction playerDir)
    {
        if(xCord+playerDir.getX()<map[0].length-gridCols && xCord+playerDir.getX()>=0 && yCord-playerDir.getY()<=map.length-gridRows && yCord-playerDir.getY()>=0) // moving
        {
        	if(!map[playerY()-playerDir.getY()][playerX()+playerDir.getX()].isColidable())
        	{
		        map[playerY()-playerDir.getY()][playerX()+playerDir.getX()]=map[playerY()][playerX()];
		        map[playerY()][playerX()] = getEmptySpace(playerY()+playerDir.getY(),playerX()-playerDir.getX());
		        if(playerDir.getY() == 1)
		        {
		        	for(int c = 0; c< gridCols;c++)
		        	{
		        		map[yCord-1][c+xCord].setMove(false);
		        	}
		        	for(int a = gridRows-1; a>=0; a--)
			        {
			        	for(int b = 0; b<gridCols;b++)
			        	{
				        	myGUI.getGridSpot(a,b).setIcon(map[a+yCord-1][b+xCord].getImg());
			        	}
			        }
			        yCord--;
		        }
		        else if(playerDir.getY() == -1)
		        {

		        	for(int c = 0; c< gridCols;c++)
		        	{
		        		map[yCord+gridRows][c+xCord].setMove(false);
		        	}
		        	for(int a = 0; a<gridRows; a++)
			        {
			        	for(int b = 0; b<gridCols;b++)
			        	{
				        	myGUI.getGridSpot(a,b).setIcon(map[a+yCord+1][b+xCord].getImg());
			        	}
			        }
			        yCord++;
		        }
		        else if(playerDir.getX() == 1)
		        {
		        	for(int c = 0; c< gridRows;c++)
		        	{
		        		map[c+yCord][xCord+gridCols].setMove(false);
		        	}
			        for(int a = 0; a<gridRows; a++)
			        {
			        	for(int b = 0; b<gridCols;b++)
			        	{
			        		myGUI.getGridSpot(a,b).setIcon(map[a+yCord][b+xCord+1].getImg());
			        	}
			        }
			        xCord++;
		        }
		        else
		        {

		        	for(int c = 0; c< gridRows;c++)
		        	{
		        		map[c+yCord][xCord-1].setMove(false);
		        	}
		        	for(int a = 0; a<gridRows; a++)
			        {
			        	for(int b = gridCols-1; b>=0;b--)
			        	{
				        	myGUI.getGridSpot(a,b).setIcon(map[a+yCord][b+xCord-1].getImg());
			        	}
			        }
			        xCord--;
		        }
        	}
        	else if(map[playerY()-playerDir.getY()][playerX()+playerDir.getX()].getEntity().equals("enemy"))
        	{

        		hurtEnemy(playerY()-playerDir.getY(),playerX()+playerDir.getX(),25);
        		
        	}
        	else if(map[playerY()-playerDir.getY()][playerX()+playerDir.getX()].getEntity().equals("healthPack"))
        	{
        		
        	    	hp += 20;
        	    	myGUI.updateHp(hp);
        	    
        			map[playerY()-playerDir.getY()][playerX()+playerDir.getX()] = getEmptySpace(playerY()-playerDir.getY(),playerX()+playerDir.getX());
        			myGUI.getGridSpot((gridRows/2)-playerDir.getY(),(gridCols/2)+playerDir.getX()).setIcon(map[playerY()-playerDir.getY()][playerX()+playerDir.getX()].getImg());
        		
        	}
        }
        enemyMoves();
    }
    
    public void enemyMoves()
    {
    	movesLeft--;
    	if(movesLeft<=0)
    	{
    		movesLeft = 20;
    		int rand1;
    		int rand2;
    		do
    		{
    		rand1 = (int)(Math.random()*mapCols);
    		rand2 = (int)(Math.random()*mapRows);
    		}while(map[rand2][rand1].isColidable() || (rand1-xCord<gridCols && rand1>=xCord) || (rand2-yCord<gridRows && rand2 >= yCord));
    		map[rand2][rand1] = new Enemy(rand1,rand2,true,superEnemyImg);
    	}
    	String[] priorities = {"projectile","enemy"};
    	Model[][] tempMap = new Model[mapRows][mapCols];
    	for(int a = 0; a< mapRows; a++)
    	{
    		for(int b = 0; b< mapCols; b++)
        	{
        		tempMap[a][b]=map[a][b];
        	}
    	}
    	for(int ent = 0; ent<priorities.length;ent++)
    	{
	    for(int row = 0; row < gridRows; row++) // make enemy moves
	    {
	    	for(int col = 0; col < gridCols; col++)
	    	{
	    		if(doMove(map[yCord+row][xCord+col]) != null && map[yCord+row][xCord+col].getEntity().equals(priorities[ent]))
	    		{
	    			if(map[yCord+row][xCord+col].getCanMove())
	    			{
		        		int tempX = doMove(map[yCord+row][xCord+col]).getX();
		        		int tempY = doMove(map[yCord+row][xCord+col]).getY();
		        		if(col+tempX<gridCols && col+tempX>=0 && row-tempY<gridRows && row-tempY>=0)
		        		{
			        		if(!tempMap[yCord+row-tempY][xCord+col+tempX].isColidable())
			    			{
			        			map[yCord+row][xCord+col].move(new Direction(tempY*-1,tempX)); // is probably broken
			        	        tempMap[yCord+row-tempY][xCord+col+tempX] = map[yCord+row][xCord+col];
			    				tempMap[yCord+row][xCord+col] = getEmptySpace(xCord+col, yCord+row);
			    				myGUI.getGridSpot(row-tempY, col+tempX).setIcon(tempMap[row+yCord-tempY][col+xCord+tempX].getImg());
			    				myGUI.getGridSpot(row, col).setIcon(tempMap[row+yCord][col+xCord].getImg());
			    			}
			    			else if(map[yCord+row][xCord+col].getEntity().equals("enemy") && tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("player"))
			    			{
			        			doFight(tempMap[yCord+row][xCord+col]);
			    			}
			    			else if(map[yCord+row][xCord+col].getEntity().equals("enemy") && tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("projectile"))
			    			{
			            		if(!hurtEnemy(yCord+row,xCord+col,20))
			            		{
				    				map[yCord+row][xCord+col].move(new Direction(tempY*-1,tempX));
				    				tempMap[yCord+row-tempY][xCord+col+tempX] = map[yCord+row][xCord+col];
				    				tempMap[yCord+row][xCord+col] = getEmptySpace(xCord+col, yCord+row);
				    				myGUI.getGridSpot(row-tempY, col+tempX).setIcon(tempMap[row+yCord-tempY][col+xCord+tempX].getImg());
				    				myGUI.getGridSpot(row, col).setIcon(tempMap[row+yCord][col+xCord].getImg());
			            		}
			    			}
			    			else if(map[yCord+row][xCord+col].getEntity().equals("projectile"))
			    			{
			    				if(tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("enemy"))
			            		{
			    					hurtEnemy(yCord+row-tempY,xCord+col+tempX,20);
			            			tempMap[yCord+row][xCord+col] = getEmptySpace(yCord+row,xCord+col);
			            			myGUI.getGridSpot(row,col).setIcon(tempMap[yCord+row][xCord+col].getImg());
			    				}
			    				else if(!tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("projectile"))
			    				{
		            			tempMap[yCord+row][xCord+col] = getEmptySpace(yCord+row,xCord+col);
		            			myGUI.getGridSpot(row,col).setIcon(tempMap[yCord+row][xCord+col].getImg());
			    				}
			    			}
		        		}
		        		else if(map[yCord+row][xCord+col].getEntity().equals("projectile"))
		        		{
	            			tempMap[yCord+row][xCord+col] = (Model) getEmptySpace(yCord+row,xCord+col); 
	            			myGUI.getGridSpot(row,col).setIcon(tempMap[yCord+row][xCord+col].getImg());
		        		}
	    			}
	    			else
	    			{
	    				map[yCord+row][xCord+col].setMove(true);
	    			}
	    		}
	    	}
	    }
    	}
    	for(int a = 0; a< mapRows; a++)
    	{
    		for(int b = 0; b< mapCols; b++)
        	{
        		map[a][b]=tempMap[a][b];
        	}
    	}
	    map = tempMap;
    	if(hp<=0)
	    {
	    	myGUI.exit();
	    	MainMenu myMenu = new MainMenu("You Lose :(",this);
    	}
    	else if(enemiesLeft <= 0)
    	{
	    	myGUI.exit();
	    	MainMenu myMenu = new MainMenu("You Win by Genocide >:)",this);
    	}
    	else if(playerX() == mapCols-gridCols/2-1 && playerY() == mapRows-gridRows/2-1)
    	{
	    	myGUI.exit();
	    	MainMenu myMenu = new MainMenu("You Win by Escape!",this);
    	}
    }
}

/* Things to Be Added Maybe
 * Balance the game to make it fun
 * fix map generation
 * fix spawn trapping
 * win condition - done.
 * lose condition - done.
 * fix images - done.
 * add main menu - done.
 * add projectiles for player only - done.
 * decrement enemies killed - done.
 * maybe add a time limit or moves restriction to add difficulty
 * option menu
 */
