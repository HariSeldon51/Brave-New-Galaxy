package com.dehavenmedia.interstella;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, WindowListener {
	
	GameStateManager gameStateManager;
	Thread gameLoop;
	
	private static int P_WIDTH = 800;
	private static int P_HEIGHT = 600;
	private static int DEFAULT_FPS = 80;
	private static int MS_PER_FRAME = 1000/DEFAULT_FPS;
	
	long previousTime;
	long currentTime;
	long elapsedTime;
	double lag = 0.0;
	
	public GamePanel()
	{
		gameStateManager = new GameStateManager();
	}

	public void run()
	{		
		gameStateManager.startGame();
		
		previousTime = getCurrTime();
		while (gameStateManager.isRunning())
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
	
	private void startGame()
	{
		if (gameLoop == null || !gameStateManager.isRunning()) {
			gameLoop = new Thread(this);
			gameLoop.start();
		}
	}
	
	private long getCurrTime()
	{
		return System.nanoTime();
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
	
} // End of Game class.