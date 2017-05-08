import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RougueGUI extends JFrame
{
    private Controller myController;
    private JPanel grid, top, bottom;
    private Model[][] map;
    /**
     * Constructor for objects of class GUI
     */
    public RougueGUI(Controller control)
    {
        myController = control;
        grid = new JPanel();
        top = new JPanel();
        bottom = new JPanel();
        map = genMap(20,20);
        grid.setLayout(new GridLayout(5,10));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyHandler());
        for(int a = 0; a< ((GridLayout)grid.getLayout()).getRows(); a++)
        {
        	for(int b = 0; b< ((GridLayout)grid.getLayout()).getColumns(); b++)
        	{
        		grid.add(new Block(map[a][b]));
        	}        
        }
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setSize(800, 400);
        setVisible(true);
        System.out.println(getGridSpot(3,5));
    }
    public Block getGridSpot(int yPos, int xPos)
    {
        return (Block) grid.getComponent(yPos*10+xPos);
    }
    
    public JPanel getGrid()
    {
    	return grid;
    }
    
    public int getRows()
    {
    	return ((GridLayout)grid.getLayout()).getRows();
    }
    
    public int getCols()
    {
    	return ((GridLayout)grid.getLayout()).getColumns();
    }
    
    public Model[][] genMap(int width, int height)
    {
    	Model[][] myMap = new Model[height][width];
    	for(int a = 0; a < height; a++)
    	{
    		for(int b = 0; b< width; b++)
    		{
    	    	if((int)(Math.random() * 5) == 0)
    	    	{
    	    		myMap[a][b] = new Enemy(a,b);
    	    	}
    	    	else if((int)(Math.random() * 4) == 0)
    	    	{
    	    		myMap[a][b] = new Obstacle(a,b);
    	    		
    	    	}
    	    	else if((int)(Math.random() * 3) == 0)
    	    	{
    	    		myMap[a][b] = new Enemy(a,b);
    	    	}
    	    	else
    	    	{
    	    		myMap[a][b] = new Model(a,b);
    	    	}
    		}
    	}
    	myMap[3][5] = new Player(3,5);
    	return myMap;
    }
    
    private class KeyHandler implements KeyListener {
	    
	    public void keyPressed ( KeyEvent event )
	    {
	    	
	    }
	    
	    public void keyReleased (KeyEvent event)
	    {
	        if(event.getKeyCode() == KeyEvent.VK_UP)
	        {
	        	myController.makeMoves(1, map, new Integer(getGridSpot(0,0).getY()), new Integer(getGridSpot(0,0).getX()));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_DOWN)
	        {
	        	myController.makeMoves(3, map, new Integer(getGridSpot(0,0).getY()), new Integer(getGridSpot(0,0).getX()));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_LEFT)
	        {
	        	myController.makeMoves(2, map, new Integer(getGridSpot(0,0).getY()), new Integer(getGridSpot(0,0).getX()));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_RIGHT)
	        {
	        	myController.makeMoves(0, map, new Integer(getGridSpot(0,0).getY()), new Integer(getGridSpot(0,0).getX()));
	        }
	    }
	    
	    public void keyTyped (KeyEvent event )
	    {
	    	
	    }
	}
}
