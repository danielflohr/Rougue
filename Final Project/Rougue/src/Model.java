import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of interface Model here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Model
{
	private int x,y;
	private boolean colidable;
	public Model(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
		colidable = false;
	}
	
    public int move()
    {
    	return -1;
    }
    
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    public boolean isColidable()
    {
    	return colidable;
    }
    public Icon getImg()
    {
    	return null;
    }
}
