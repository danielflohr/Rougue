import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Model
{
	private int x,y;
	private boolean colidable;
    public Player(int xPos,int yPos)
    {
    	super(xPos,yPos);
    	colidable = true;
    }
    
    public int move()
    {
        return -1;
    }
    
    public void attack()
    {
        
    }
}
