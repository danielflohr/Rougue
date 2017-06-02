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
public class HealthPack extends Model
{
	private int x,y;
	private boolean colidable, canMove;
	private String entity;
    public HealthPack(int xPos, int yPos, ImageIcon pic)
    {
        super(xPos,yPos,pic);
        entity = "healthPack";
        colidable = true;
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
