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
	private boolean colidable, canMove;
	private String entity;
	public Obstacle(int xPos, int yPos, ImageIcon pic)
    {
        super(xPos,yPos,pic);
        colidable = true;
        entity = "obstacle";
        canMove = false;
    }
    public boolean isColidable()
    {
    	return colidable;
    }
    public String getEntity()
    {
	    return entity;
    }
}
