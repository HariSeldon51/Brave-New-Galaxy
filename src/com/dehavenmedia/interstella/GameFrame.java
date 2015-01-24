package com.dehavenmedia.interstella;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements WindowListener
{
	
	private static int P_WIDTH = 800;
	private static int P_HEIGHT = 600;
	private static int DEFAULT_FPS = 80;
	private static String TITLE = "Interstella - A Java Game Engine";
	
	private GameStateManager gameStateManager;
	private GamePanel gamePanel;
	
	DisplayMode displayMode = new DisplayMode(P_WIDTH, P_HEIGHT, 16, DEFAULT_FPS);
		
	public GameFrame()
	{
		super(TITLE);
		
		gameStateManager = new GameStateManager();
		gamePanel = new GamePanel(gameStateManager);
		
		makeGUI(gamePanel);
		initWindow(this);
		
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
	} // End of BraveNewGalaxy() constructor.
	
	private void makeGUI(GamePanel gp)
	{
		GamePanel myPanel= gp;
		setContentPane(myPanel);
		pack();
	}
	
	private void initWindow(GameFrame gf)
	{
		GameFrame gameFrame = gf;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addWindowListener(gameFrame);		
		setLocationRelativeTo(null);
	}
	
	// ------------  Inherited methods from the WindowListener interface  ------------ //

	@Override
	public void windowActivated(WindowEvent e)
	{
		gameStateManager.resumeGame();		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		gameStateManager.pauseGame();		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {	}

	@Override
	public void windowClosed(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		gameStateManager.pauseGame();		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		gameStateManager.resumeGame();		
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		gameStateManager.stopGame();		
	}

	// ------------  Main method  ------------ //
	
	public static void main(String[] args)
	{
		new GameFrame();
	} // End of main().

}
