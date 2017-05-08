import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of class Controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Controller
{
    private RougueGUI myGUI;
    
    public Controller()
    {
    	
    }
    
    public void setGUI(RougueGUI gui)
    {
        myGUI = gui;
    }
    
    public void makeMoves(int playerDir, Model[][] map,int y,int x)
    {
    	Model[][] temp = map;
        System.out.println(playerDir);
        if(x<map.length-myGUI.getCols() && playerDir == 0)
        {
	        for(int a = 0; a<myGUI.getRows(); a++)
	        {
	        	for(int b = 0; b<myGUI.getCols()-1;b++)
	        	{
	        		myGUI.getGridSpot(a,b).setIcon(myGUI.getGridSpot(a,b+1).getIcon());
	        	}
	        	myGUI.getGridSpot(a,9).setIcon(map[a+y][10+x].getImg());
	        }
        }
        else if(x>0 && playerDir == 2)
        {
	        for(int a = 0; a<5; a++)
	        {
	        	for(int b = 9; b>0;b--)
	        	{
	        		myGUI.getGridSpot(a,b).setIcon(myGUI.getGridSpot(a,b-1).getIcon());
	        	}
	        	myGUI.getGridSpot(a,0).setIcon(map[a+y][x-1].getImg());
	        }
        }
        else if(y<15 && playerDir == 3)
        {
	        for(int a = 0; a<4; a++)
	        {
	        	for(int b = 0; b<10;b++)
	        	{
	        		myGUI.getGridSpot(a,b).setIcon(myGUI.getGridSpot(a+1,b).getIcon());
	        	}
	        }
	        for(int c = 0; c<10;c++)
	        {
	        	myGUI.getGridSpot(4,c).setIcon(map[5+y][c+x].getImg());
	        }
        }
        else if(y>0 && playerDir == 1)
        {
	        for(int a = 4; a>0; a--)
	        {
	        	for(int b = 0; b<10;b++)
	        	{
	        		myGUI.getGridSpot(a,b).setIcon(myGUI.getGridSpot(a-1,b).getIcon());
	        	}
	        }
	        for(int c = 0; c<10;c++)
	        {
	        	myGUI.getGridSpot(0,c).setIcon(map[y-1][c+x].getImg());
	        }
        }
        for(int row = 0; row < 5; row++)
        {
        	for(int col = 0; col < 10; col++)
        	{
        		switch(map[y+row][x+col].move())
        		{
	        		case 0:
	        			if(!map[y+row][x+col+1].isColidable())
	        			{
	        				map[y+row][x+col+1] = map[y+row][x+col];
	        			}
	        			break;
	        		case 1:
	        			if(!map[y+row-1][x+col+1].isColidable())
	        			{
	        				map[y+row-1][x+col+1] = map[y+row][x+col];
	        			}
	        			break;
	        		case 2:
	        			if(!map[y+row-1][x+col].isColidable())
	        			{
	        				map[y+row-1][x+col] = map[y+row][x+col];
	        			}
	        			break;
	        		case 3:
	        			if(!map[y+row-1][x+col-1].isColidable())
	        			{
	        				map[y+row-1][x+col-1] = map[y+row][x+col];
	        			}
	        			break;
	        		case 4:
	        			if(!map[y+row][x+col-1].isColidable())
	        			{
	        				map[y+row][x+col-1] = map[y+row][x+col];
	        			}
	        			break;
	        		case 5:
	        			if(!map[y+row+1][x+col-1].isColidable())
	        			{
	        				map[y+row+1][x+col-1] = map[y+row][x+col];
	        			}
	        			break;
	        		case 6:
	        			if(!map[y+row+1][x+col].isColidable())
	        			{
	        				map[y+row+1][x+col] = map[y+row][x+col];
	        			}
	        			break;
	        		case 7:
	        			if(!map[y+row+1][x+col+1].isColidable())
	        			{
	        				map[y+row+1][x+col+1] = map[y+row][x+col];
	        			}
	        			break;
        		}
        	}
        
        }
     }
}