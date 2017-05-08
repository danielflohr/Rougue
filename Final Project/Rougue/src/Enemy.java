import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of interface Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Model
{
	private int x,y;
	private boolean colidable;
	public Enemy(int xPos, int yPos)
    {
        super(xPos,yPos);
        colidable = true;
    }
    
    public int move()
    {
        return -1;
    }
}
