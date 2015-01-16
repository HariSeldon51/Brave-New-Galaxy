package com.dehavenmedia.brave_new_galaxy;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BraveNewGalaxy extends JFrame implements WindowListener
{
	
	// private static int DEFAULT_FPS = 80;
	
	private GamePanel gamePanel;
	
	DisplayMode displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		
	public BraveNewGalaxy()
	{
		super("Brave New Galaxy");
		makeGUI();
		
		addWindowListener(this);
		pack();
		setVisible(true);
	} // End of BraveNewGalaxy() constructor.
	
	private void makeGUI()
	{
		Container c = getContentPane();
		
		gamePanel = new GamePanel(this, displayMode);
		c.add(gamePanel, "Center");
		
	} // End of makeGUI().
	
	// ------------  Inherited window methods  ------------ //

	@Override
	public void windowActivated(WindowEvent e)
	{
		gamePanel.resumeGame();		
	}

	@Override
	public void windowClosed(WindowEvent e) {	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		gamePanel.stopGame();		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		gamePanel.pauseGame();		
	}

	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		gamePanel.resumeGame();		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		gamePanel.pauseGame();		
	}

	@Override
	public void windowOpened(WindowEvent e) {	}
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args)
	{
		new BraveNewGalaxy();
	} // End of main().

}
