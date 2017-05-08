import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of interface Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacle extends Model
{
	private int x,y;
	public Obstacle(int xPos, int yPos)
    {
        super(xPos,yPos);
    }
    
    public int move()
    {
        return -1;
    }
}
