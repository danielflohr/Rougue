import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
 

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class MainMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
 
	public static final Font TITLE_FONT = new Font(Font.SERIF, Font.PLAIN, 30*5);//30
	public static final Font SUBTITLE_FONT = new Font(Font.SERIF, Font.PLAIN, 22*5);//22
	public static final Font BODY_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 18*5);//18
	
	public static final String GAME_NAME = "Doodle King";
	
	private JLabel gameNameLabel;
	private JLabel titleLabel;
	private JPanel buttonsPanel;
	private JButton[] buttons;
	private Controller myController;
	
	public MainMenu(String title, Controller c)
	{
		super ("Doodle King Main Menu");
		myController=c;
		gameNameLabel = new JLabel(GAME_NAME);
		gameNameLabel.setFont(TITLE_FONT);
		
		titleLabel = new JLabel(title);
		titleLabel.setFont(SUBTITLE_FONT);
		
		buttonsPanel = new JPanel();
		buttons = new JButton[2];
		buttons[0] = new JButton("Play");
        buttons[0].setActionCommand("play");
		buttons[1] = new JButton("Quit");
        buttons[1].setActionCommand("quit");
		for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
			buttons[i].setFont(BODY_FONT);
			buttonsPanel.add(buttons[i]);
		}
				
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		// game name row
		gbc.gridy = 0;
		add(gameNameLabel, gbc);
		// title row
		gbc.gridy = 1;
		add(titleLabel, gbc);
		// buttons row
		gbc.gridy = 2;
		add(buttonsPanel, gbc);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720); // 720p. woo.
		setVisible(true);
	}
	
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("play"))
        {
            myController.newGame();
        	RougueGUI gui = new RougueGUI(myController);
            myController.setGUI(gui);
            dispose();
        }
        else if(e.getActionCommand().equals("quit"))
        {
        	dispose();
        }
    }
}
