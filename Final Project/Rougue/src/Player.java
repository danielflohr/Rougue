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
public class Player implements Model
{
    public Player()
    {
    	ImageIcon fishIcon = new ImageIcon("fish.gif");
        fish = fishIcon.getImage();

        int load = fishIcon.getImageLoadStatus();
        System.out.println("fish load " + load);
    }
    
    public void move()
    {
        
    }
    
    public void attack()
    {
        
    }
    
    public void hasColided()
    {
        
    }
    
    public void getLocation()
    {
        
    }
}
