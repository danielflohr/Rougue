import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RougueGUI extends JFrame
{
    private Controller myController;
    private Image fish;

    /**
     * Constructor for objects of class GUI
     */
    public RougueGUI(Controller control)
    {
    	ImageIcon fishIcon = new ImageIcon("fish.gif");
        fish = fishIcon.getImage();

        int load = fishIcon.getImageLoadStatus();
        System.out.println("fish load " + load);
        myController = control;
        setSize(400, 170);
        setVisible(true);
    }
    
    public void getModelList()
    {
        
    }
    
    public void paint (Graphics g)
    {
        // call superclass's paint method
        super.paint(g);

        // draw a red line; 2 blue rectangles, one filled and one
        // not; and 2 magenta ovals, one filled and one not

        g.setColor(Color.red);
        g.drawLine(5, 30, 350, 30);

        g.setColor(Color.blue);
        g.drawRect(5, 40, 90, 55);
        g.fillRect(100, 40, 90, 55);

        g.setColor(Color.magenta);
        g.drawOval(195, 100, 90, 55);
        g.fillOval(290, 100, 90, 55);

        // location of fish changes each time the timer goes off
        g.drawImage(fish, 300, 40, this);	// 'fish' was loaded in constructor
    }
    
}
