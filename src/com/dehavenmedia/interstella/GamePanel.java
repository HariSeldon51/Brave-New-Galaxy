package com.dehavenmedia.interstella;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable
{
	//Initialize a GameStateManager in order to move smoothly from intro, to menu, to world.
	GameStateManager gameStateManager;	
	Thread gameLoop;
	
	//Default game settings
	private static int P_WIDTH = 800;
	private static int P_HEIGHT = 600;
	private static int DEFAULT_FPS = 80;
	private static int MS_PER_FRAME = 1000/DEFAULT_FPS;
	private static int MAX_FRAME_SKIPS = 5;
	
	private volatile boolean isPaused = false; // Flag -- is the game currently paused?
	private volatile boolean isRunning = false; // Flag -- is the game currently running?
	
	public GamePanel()
	{
		gameStateManager = new GameStateManager(this);
		
		setBackground(Color.white);
	    setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));

	    setFocusable(true);
	    requestFocusInWindow(); 
	}

	public void run()
	{		
		long previousTime;
		long currentTime;
		long elapsedTime;
		double lag = 0.0;
		isRunning = true;
		
		previousTime = getCurrTime(); //Sets the time the game loop starts
		
		while (isRunning)
		{
			currentTime = getCurrTime(); //Sets the time this particular game loop starts
			elapsedTime = currentTime - previousTime; //Measures the time that has elapsed since the beginning of the last game loop
			previousTime = currentTime; //Saves the time this game loop started for the next game loop to evaluate
			lag += elapsedTime/1000000; //Converts the elapsed time into milliseconds
			
			int numSkippedFrames = 0;
			while (lag > MS_PER_FRAME && numSkippedFrames < MAX_FRAME_SKIPS) //If more time has elapsed since the last loop than desired...
			{
				if(!isPaused) { gameStateManager.update(1); } //...update the game logic, if not paused,...
				lag -= MS_PER_FRAME; //...as many frames as would have occurred during that time period.
				numSkippedFrames++;
			}
			
			gameStateManager.update(lag/MS_PER_FRAME);
			gameStateManager.render(); //Then render everything
			gameStateManager.stateUpdate(); //Update the game state, if requested in the previous loop
			lag = 0.0;
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