import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Block extends JLabel
{
	String color;
	String entity;
	
	public Block(Model m)
	{
		super(m.getX()+" "+m.getY());
	}
}
