package com.dehavenmedia.interstella;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GameFrame extends JFrame implements WindowListener
{
	
	private static String TITLE = "Interstella - A Java Game Engine";
	
	public static GamePanel gamePanel;
	
	public GameFrame()
	{
		super(TITLE);
		
		gamePanel = new GamePanel();
		
		makeGUI();
		initWindow();
		
		setVisible(true);
	} // End of BraveNewGalaxy() constructor.
	
	private void makeGUI()
	{
		setContentPane(gamePanel);
		pack();
	}
	
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
	public void windowOpened(WindowEvent e) {	}
			
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
