package com.dehavenmedia.interstella;

import javax.swing.*;
//import java.text.DecimalFormat;

public class GamePanel extends JPanel implements Runnable {
	
	GameStateManager gameStateManager;
	Thread gameLoop;
	
	public GamePanel()
	{
		gameStateManager = new GameStateManager();
	}

	public void run()
	{		
		gameStateManager.startGame();
		
		while (gameStateManager.isRunning()) {
			gameStateManager.loop();	
		}
		
		System.exit(0);
	} // End of run().
	
	public void addNotify()
	{
		super.addNotify();
		startGame();
	}
	
	private void startGame()
	{
		if (gameLoop == null || !gameStateManager.isRunning()) {
			gameLoop = new Thread(this);
			gameLoop.start();
		}
	}
	
} // End of Game class.