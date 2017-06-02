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
	private boolean colidable, canMove, isSuper;
	private int health;
	private String entity;
	public Enemy(int xPos, int yPos,boolean s, ImageIcon pic)
    {
        super(xPos,yPos,pic);
        colidable = true;
        if(s)
        {
        	health = 150;
        }
        else
        {
        	health = 75;
        }
        canMove = false;
        entity = "enemy";
        isSuper = s;
    }
	public boolean getSpecial()
	{
		return isSuper;
	}
	public boolean getCanMove()
	{
		return canMove;
	}
	public void setMove(boolean b)
	{
		canMove=b;
	}
    public boolean isColidable()
    {
    	return colidable;
    }
    public boolean damage(int attack)
    {
    	health -= attack;
    	return (health > 0);
    }
    public int attack()
    {
    	if(isSuper)
    	{
    		return 6;
    	}
    	else
    	{
    		return 2;
    	}
    }
    public int getHP()
    {
    	return health;
    }
    public String getEntity()
    {
	    return entity;
    }
}