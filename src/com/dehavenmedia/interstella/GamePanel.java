package com.dehavenmedia.interstella;

import java.awt.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable
{
	
	GameStateManager gameStateManager;
	
	Thread gameLoop;
	
	private static int P_WIDTH = 800;
	private static int P_HEIGHT = 600;
	private static int DEFAULT_FPS = 80;
	private static int MS_PER_FRAME = 1000/DEFAULT_FPS;
	
	private volatile boolean isPaused = false; // Flag -- is the game currently paused?
	private volatile boolean isRunning = false; // Flag -- is the game currently running?
	
	private long previousTime;
	private long currentTime;
	private long elapsedTime;
	private double lag = 0.0;
	
	public GamePanel()
	{
		gameStateManager = new GameStateManager();
		
		setBackground(Color.white);
	    setPreferredSize( new Dimension(P_WIDTH, P_HEIGHT));

	    setFocusable(true);
	    requestFocusInWindow(); 
	}

	public void run()
	{		
		isRunning = true;
		
		previousTime = getCurrTime();
		while (isRunning)
		{
			currentTime = getCurrTime();
			elapsedTime = currentTime - previousTime;
			previousTime = currentTime;
			lag += elapsedTime;
			
			while (lag > MS_PER_FRAME)
			{
				gameStateManager.update(lag/MS_PER_FRAME);
				lag -= MS_PER_FRAME;
			}
						
			gameStateManager.render();
			gameStateManager.stateUpdate();
		}
		
		System.exit(0);
	} // End of run().
	
	public void addNotify()
	{
		super.addNotify();
		startGame();
	}
	
	private long getCurrTime()
	{
		return System.nanoTime();
	}
	
	// ------------  Game's life cycle methods  ------------ //
	
	public void startGame()
	{
		if (gameLoop == null || !isRunning) {
			gameLoop = new Thread(this);
			gameLoop.start();
		}
	} // End of startGame().
	
	public void pauseGame()
	{
		isPaused = true;
	} // End of pauseGame().
		
	public void resumeGame()
	{
		isPaused = false;
	} // End of resumeGame().
		
	public void stopGame()
	{
		isRunning = false;
	} // End of stopGame().

} // End of Game class.