import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
/**
 * Write a description of class GameTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameTester
{
    public static void main(String[] args)
    {
        Controller control = new Controller();
        RougueGUI gui = new RougueGUI(control);
        control.setGUI(gui);
    }
}
