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
	private String entity;
    public Player(int xPos,int yPos, ImageIcon pic)
    {
    	super(xPos,yPos,pic);
    	colidable = true;
        entity = "player";
    }
    public boolean isColidable()
    {
    	return colidable;
    }
    public void attack()
    {
        
    }
    public String getEntity()
    {
	    return entity;
    }
}
