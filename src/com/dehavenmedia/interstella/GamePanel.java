package com.dehavenmedia.interstella;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener
{
	//Declare game constants
	private static int MS_PER_FRAME;
	private static int MS_PER_UPDATE;
	private static int MAX_FRAME_SKIPS;
	private static int P_WIDTH;
	private static int P_HEIGHT;
	
	//Declare variable to hold a reference to current GameMode (Menu, Load, Play, etc).
	GameStateManager gameStateManager;
	
	//Declare variable to hold a reference to main thread of the game.
	Thread gameLoop;	
	
	//Initialize basic game states.
	private volatile boolean isPaused = false; // Flag -- is the game currently paused?
	private volatile boolean isRunning = false; // Flag -- is the game currently running?
	
	public GamePanel(int pWidth, int pHeight, int fps, int ups, int maxSkips)
	{
		//Initialize game constants from arguments provided by GameFrame.
		MS_PER_FRAME = 1000/fps;
		MS_PER_UPDATE = 1000/ups;
		MAX_FRAME_SKIPS = maxSkips;		
		P_WIDTH = pWidth;
		P_HEIGHT = pHeight;
		
		//Initialize first game mode.
		gameStateManager = new GameStateManager(this);
	}

	public void run()
	{		
		isRunning = true;
		
		int numSkippedFrames = 0;
		long currentTime;
		long elapsedTime;
		long accumulatedTime = 0;
		long maximumFrameTime = MS_PER_FRAME * 1000000;
		long maximumUpdateTime = MS_PER_UPDATE * 1000000;
		long previousTime = getCurrTime(); //Sets the time the game loop starts
		
		while (isRunning)
		{
			currentTime = getCurrTime(); //Sets the time this particular game loop starts
						
			/* If the amount of time elapsed since the start of the last game loop is less than the target frames per second
			 * or the maximum number of skipped frames have already been skipped, render the frame.	Otherwise, skip the frame. */
			elapsedTime = currentTime - previousTime; //Measures the time that has elapsed since the beginning of the last game loop
			if (elapsedTime <= maximumFrameTime || numSkippedFrames >= MAX_FRAME_SKIPS)
			{
				gameStateManager.render();
				numSkippedFrames = 0; //Reset the skipped frames counter
			} else { 
				numSkippedFrames++; //Skip a frame render and increment the skipped frames counter
			} 
			
			/* If the targeted amount of time since the last game update has elapsed, update the game.
			 * Otherwise, wait until the next loop and check the accumulated time again. */
			accumulatedTime += elapsedTime; //Measures the time that has elapsed since the beginning of the last game update
			if (accumulatedTime >= maximumUpdateTime) 
			{
				gameStateManager.update(accumulatedTime/maximumUpdateTime); //Update the game, with the ratio of elapsed time to frame length as the delta.
				gameStateManager.stateUpdate(); //Update the game state, if requested in the previous loop
				accumulatedTime %= maximumUpdateTime; //Find the extra time accumulated (the modulo of accumulated time and maximum time)
			}
			
			previousTime = currentTime; //Saves the time this game loop started for the next game loop to evaluate
		}
		
		System.exit(0);
	} // End of run().
	
	public void addNotify()
	{
		super.addNotify();
		
		setBackground(Color.white);
	    setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));

	    setFocusable(true);
	    requestFocusInWindow(); 
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
		}		
		gameLoop.start();
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