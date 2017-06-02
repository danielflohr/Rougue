import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
/**
 * Write a description of interface Model here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Model
{
	private int x,y;
	private boolean colidable, canMove;
	private ImageIcon img;
	private String entity;
	public Model(int xPos, int yPos, ImageIcon pic)
	{
		x = xPos;
		y = yPos;
		colidable = false;
		img = pic;
		entity = "model";
		canMove = false;
	}
	public boolean getCanMove()
	{
		return canMove;
	}
	public void setMove(boolean b)
	{
		canMove=b;
	}
    public void move(Direction dir)
    {
    	x += dir.getX();
    	y += dir.getY();
    }
    public int getX()
    {
    	return x;
    }
    public int getY()
    {
    	return y;
    }
    public boolean isColidable()
    {
    	return colidable;
    }
    public ImageIcon getImg()
    {
    	return img;
    }
    public String getEntity()
    {
	    return entity;
    }
}
