import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.net.URL;
import java.util.*;
/**
 * Write a description of class Controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * tempPlayer.jpg from http://unisci24.com/253647.html
 * tempEnemy.png from http://clipart-library.com/clipart/255283.htm
 * tempArrow.png from https://icons8.com/icon/set/arrow/all
 * tempWall.jpg from https://pixabay.com/en/brick-wall-wall-art-design-185085/
 * tempArrow.jpeg from https://clipartfest.com/categories/view/f35d9fb6ceb1810f984ab0312c5dffbf90c8a61c/clipart-arrow-weapon.html
 */
public class Controller
{
    private RougueGUI myGUI;
    private int xCord, yCord, hp, gridRows, gridCols, mapRows, mapCols;
    private Model[][] map;
    private ImageIcon playerImg, enemyImg, floorImg, obstacleImg, healthImg, arrowImg;
    public Controller()
    {
    	gridRows = 7;
    	gridCols = 10;
    	mapRows = 30;
    	mapCols = 40;
    	xCord = 0;
    	yCord = 0;
    	hp = 100;
        ClassLoader cldr = this.getClass().getClassLoader();
        URL tempURL = cldr.getResource("tempPlayer.jpg");
        playerImg = new ImageIcon(tempURL);
        tempURL = cldr.getResource("tempEnemy.png");
        enemyImg = new ImageIcon(tempURL);
        tempURL = cldr.getResource("tempGround.png");
        floorImg = new ImageIcon(tempURL);
        tempURL = cldr.getResource("tempWall.jpg");
        obstacleImg = new ImageIcon(tempURL);
        tempURL = cldr.getResource("healthPack.jpg");
        healthImg = new ImageIcon(tempURL);
        tempURL = cldr.getResource("tempArrow.jpeg");
        arrowImg = new ImageIcon(tempURL);
        map = genMap();
        
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
	    return xCord+gridCols/2;
    }
    
    private int playerY()
    {
    	return yCord + gridRows/2;
    }
    
    public void setGUI(RougueGUI gui)
    {
        myGUI = gui;
    }
    
    public void doFight(Model enemy)
    {
    	hp -= ((Enemy) enemy).attack();
    	myGUI.updateHp(hp);
    	/*
    	if(hp<=0)
    	{
    	myGUI.exit();
    	myMenu.lose();
    	}
    	*/
    }
    
    public Model getMap(int r, int c)
    {
    	return map[r][c];
    }
    
    public Model[][] genMap()
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
	    	    		myMap[a][b] = new Enemy(b,a,enemyImg);
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
    	myMap[gridRows/2][gridCols/2] = new Player(gridRows/2,gridCols/2,playerImg);
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
	    	return new Direction((int) Math.signum(playerY() - m.getY())*-1,(int) Math.signum(playerX() - m.getX()));
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
    
    public void fireProjectile(Direction pDir)
    {
    System.out.println(pDir);
    System.out.println((xCord+pDir.getX()<map[0].length-gridCols) + "| x u |" + (xCord+pDir.getX()>=0)  + "| x d |" +  (yCord-pDir.getY()<=map.length-gridRows)  + "| y u |" +  (yCord-pDir.getY()>=0)  + "| y d |" +  (!map[yCord+(gridRows/2)-pDir.getY()][xCord+(gridCols/2)+pDir.getX()].isColidable())  + "| colide |");
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
			if(!((Enemy) map[playerY()-pDir.getY()][playerX()+pDir.getX()]).damage(15))
    		{
    			System.out.println("Hit");
    			 map[playerY()-pDir.getY()][playerX()+pDir.getX()] = getEmptySpace(playerY()-pDir.getY(),playerX()+pDir.getX());
    			myGUI.getGridSpot((gridRows/2)-pDir.getY(),(gridCols/2)+pDir.getX()).setIcon(map[playerY()-pDir.getY()][playerX()+pDir.getX()].getImg());
    		}
		}
    }
 }
    
    public void makeMoves(Direction playerDir)
    {
        System.out.println(playerDir);
        System.out.println((xCord+playerDir.getX()<map[0].length-gridCols) + "| x u |" + (xCord+playerDir.getX()>=0)  + "| x d |" +  (yCord-playerDir.getY()<=map.length-gridRows)  + "| y u |" +  (yCord-playerDir.getY()>=0)  + "| y d |" +  (!map[yCord+(gridRows/2)-playerDir.getY()][xCord+(gridCols/2)+playerDir.getX()].isColidable())  + "| colide |");
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
        		if(!((Enemy) map[playerY()-playerDir.getY()][playerX()+playerDir.getX()]).damage(25))
        		{
        			map[playerY()-playerDir.getY()][playerX()+playerDir.getX()] = getEmptySpace(playerY()-playerDir.getY(),playerX()+playerDir.getX());
        			myGUI.getGridSpot((gridRows/2)-playerDir.getY(),(gridCols/2)+playerDir.getX()).setIcon(map[playerY()-playerDir.getY()][playerX()+playerDir.getX()].getImg());
        		}
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
			        			System.out.println((xCord+5) + " || "+ (yCord+2));
			            		System.out.println(tempX+ " | " + tempY);
			        	        System.out.println(map[yCord+row][xCord+col].getX() + " "+ map[yCord+row][xCord+col].getY() + " " + tempMap[yCord+row][xCord+col].getEntity());
			        			map[yCord+row][xCord+col].move(new Direction(tempY*-1,tempX)); // is probably broken
			        	        System.out.println(map[yCord+row][xCord+col].getX() + " "+ map[yCord+row][xCord+col].getY());
			    				tempMap[yCord+row-tempY][xCord+col+tempX] = map[yCord+row][xCord+col];
			    				tempMap[yCord+row][xCord+col] = getEmptySpace(xCord+col, yCord+row);
			    				myGUI.getGridSpot(row-tempY, col+tempX).setIcon(tempMap[row+yCord-tempY][col+xCord+tempX].getImg());
			    				myGUI.getGridSpot(row, col).setIcon(tempMap[row+yCord][col+xCord].getImg());
			    			}
			    			else if(map[yCord+row][xCord+col].getEntity().equals("enemy") && tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("player"))
			    			{
			        			doFight(tempMap[yCord+row][xCord+col]);
			    			}
			    			else if(map[yCord+row][xCord+col].getEntity().equals("projectile"))
			    			{
			    				if(tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("enemy") && !((Enemy) map[yCord+row-tempY][xCord+col+tempX]).damage(15))
			            		{
				        			System.out.println("Hit");
			            			map[yCord+row-tempY][xCord+col+tempX] = getEmptySpace(yCord+row-tempY,xCord+col+tempX);
			            			myGUI.getGridSpot(row-tempY,col+tempX).setIcon(map[yCord+row-tempY][xCord+col+tempX].getImg());
			            		}
			    				else if(!tempMap[yCord+row-tempY][xCord+col+tempX].getEntity().equals("projectile"))
			    				{
		            			tempMap[yCord+row][xCord+col] = getEmptySpace(yCord+row,xCord+col); //still need to fix projectile collitions
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
    }
}
/* Things to Be Added Maybe
 * replace Projectile with Health Pack - done.
 * Balance the game to make it fun
 * fix map generation
 * win condition
 * lose condition
 * fix images - done.
 * add main menu
 * add projectiles for player only - done.
 */
