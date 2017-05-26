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
	private int x,y;
	private boolean colidable, canMove;
	private String entity;
	private Direction dir;
    public Projectile(int xPos, int yPos, Direction direction, ImageIcon pic)
    {
        super(xPos,yPos,pic);
        dir = direction;
        entity = "projectile";
        colidable = true;
        canMove = true;
    }
	public boolean getCanMove()
	{
		return canMove;
	}
	public void setMove(boolean b)
	{
		canMove=b;
	}
    public Direction getDirection()
    {
    	return dir;
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
