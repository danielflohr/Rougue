import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Model
{
	private int x,y,dir;
    public Projectile(int xPos, int yPos,int direction)
    {
        super(xPos,yPos);
        dir = direction;
    }
    
    public int move()
    {
        return -1;
    }
}
