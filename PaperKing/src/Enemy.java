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
	private boolean colidable, canMove;
	private int health;
	private String entity;
	public Enemy(int xPos, int yPos, ImageIcon pic)
    {
        super(xPos,yPos,pic);
        colidable = true;
        health = 100;
        canMove = false;
        entity = "enemy";
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
    	return 2;
    }
    public String getEntity()
    {
	    return entity;
    }
}