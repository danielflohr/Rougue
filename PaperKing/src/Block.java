import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Block extends JLabel
{
	Icon img;
	String entity;
	
	public Block(Model m)
	{
		super(m.getImg());
	}
}
