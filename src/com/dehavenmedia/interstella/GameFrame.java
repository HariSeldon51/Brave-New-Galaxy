package com.dehavenmedia.interstella;

import javax.swing.*;

public class GameFrame extends JFrame
{
	
	private static String TITLE = "Interstella - A Java Game Engine";
	
	//public GameFrame gameFrame;
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
		addWindowListener(gamePanel);		
		setLocationRelativeTo(null);
	}
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args)
	{
		new GameFrame();
	} // End of main().

}
