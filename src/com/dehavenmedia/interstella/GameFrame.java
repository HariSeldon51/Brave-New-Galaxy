package com.dehavenmedia.interstella;

import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements WindowListener
{
	//Default game settings
	private int pWidth = 800;
	private int pHeight = 600;
	private int dFPS = 60; // Frames per second
	private int dUPS = 24; // Updates per second
	private int maxSkips = 4;
	private static String TITLE = "Interstella - A Java Game Engine";	
	
	// Reference to game's rendering object
	public static GamePanel gamePanel;
	
	public GameFrame()
	{
		//Initializing GameFrame's components
		super(TITLE);
		gamePanel = new GamePanel(pWidth, pHeight, dFPS, dUPS, maxSkips);
		setContentPane(gamePanel);
		pack();	
		
		//Creating window
		initWindow();		
		setVisible(true);
	} // End of BraveNewGalaxy() constructor.
	
	private void initWindow()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addWindowListener(this);		
		setLocationRelativeTo(null);
	}
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args)
	{
		new GameFrame();
	} // End of main().

	// ------------  Inherited methods from the WindowListener interface  ------------ //

	@Override
	public void windowActivated(WindowEvent e)
	{
		gamePanel.resumeGame();		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		gamePanel.pauseGame();		
	}
	
	@Override
	public void windowOpened(WindowEvent e) 
	{
		gamePanel.startGame();
	}
			
	@Override
	public void windowClosed(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		gamePanel.pauseGame();		
	}
		
	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		gamePanel.resumeGame();		
	}
		
	@Override
	public void windowClosing(WindowEvent e)
	{
		gamePanel.stopGame();		
	}
		
}
