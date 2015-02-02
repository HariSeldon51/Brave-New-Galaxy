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
	
	//public GameFrame gameFrame;
	public static GamePanel gamePanel;
	
	public GameFrame()
	{
		super(TITLE);
		
		gamePanel = new GamePanel();
		
		makeGUI(gamePanel);
		initWindow();
		
		setVisible(true);
	} // End of BraveNewGalaxy() constructor.
	
	private void makeGUI(GamePanel gp)
	{
		GamePanel myPanel= gp;
		setContentPane(myPanel);
		pack();
	}
	
	private void initWindow()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addWindowListener(this);		
		setLocationRelativeTo(null);
	}
	
	// ------------  Inherited methods from the WindowListener interface  ------------ //

	@Override
	public void windowActivated(WindowEvent e)
	{
		gamePanel.gameStateManager.resumeGame();		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		gamePanel.gameStateManager.pauseGame();		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {	}

	@Override
	public void windowClosed(WindowEvent e) {	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		gamePanel.gameStateManager.pauseGame();		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		gamePanel.gameStateManager.resumeGame();		
	}
	
	@Override
	public void windowClosing(WindowEvent e)
	{
		gamePanel.gameStateManager.stopGame();		
	}

	// ------------  Main method  ------------ //
	
	public static void main(String[] args)
	{
		new GameFrame();
	} // End of main().

}
