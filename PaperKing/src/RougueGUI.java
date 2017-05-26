import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
import java.net.*;
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
    JProgressBar hpBar;
    /**
     * Constructor for objects of class GUI
     */
    public RougueGUI(Controller control)
    {
    	/*
    	borderlayout
    	top:name of the game
    	mid: Jlabel
    	bottom: Jpanel flowlayout: play and exit
    	*/
        myController = control;
        grid = new JPanel();
        top = new JPanel();
        bottom = new JPanel();
        hpBar = new JProgressBar();
        hpBar.setStringPainted(true);
        
        hpBar.setValue(100);
        hpBar.setString("100 hp");
        top.add(hpBar);
        		
        grid.setLayout(new GridLayout(myController.getGridRows(),myController.getGridCols()));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyHandler());
        
        for(int a = 0; a< myController.getGridRows(); a++)
        {
        	for(int b = 0; b< myController.getGridCols(); b++)
        	{
        		grid.add(new Block(myController.getMap(a, b)));
        	}        
        }
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void updateHp(int hp)
    {
        hpBar.setValue(hp);
        hpBar.setString(hp + " hp");
    }
    public Block getGridSpot(int yPos, int xPos)
    {
        return (Block) grid.getComponent(yPos*myController.getGridCols()+xPos);
    }
    public JPanel getGrid()
    {
    	return grid;
    }
    public void exit()
    {
    	dispose();
    }
    private class KeyHandler implements KeyListener {
	    
	    public void keyPressed ( KeyEvent event ){}
	    
	    public void keyReleased (KeyEvent event)
	    {
	        if(event.getKeyCode() == KeyEvent.VK_UP)
	        {
	        	myController.makeMoves(new Direction(1,0));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_DOWN)
	        {
	        	myController.makeMoves(new Direction(-1,0));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_LEFT)
	        {
	        	myController.makeMoves(new Direction(0,-1));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_RIGHT)
	        {
	        	myController.makeMoves(new Direction(0,1));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_W)
	        {
	        	myController.fireProjectile(new Direction(1,0));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_A)
	        {
	        	myController.fireProjectile(new Direction(0,-1));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_S)
	        {
	        	myController.fireProjectile(new Direction(-1,0));
	        }
	        else if(event.getKeyCode() == KeyEvent.VK_D)
	        {
	        	myController.fireProjectile(new Direction(0,1));
	        }
	    }
	    
	    public void keyTyped (KeyEvent event){}
	}
}
