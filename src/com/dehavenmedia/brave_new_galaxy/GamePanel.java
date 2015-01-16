package com.dehavenmedia.brave_new_galaxy;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
//import java.text.DecimalFormat;

public class GamePanel extends JPanel implements Runnable {
	
	private GameState gameState = GameState.ST_GENERATE; //The initial state the game starts out in
	private boolean isPaused = false;
	private Thread gameLoop;
	
	private DisplayMode displayMode;
	private BraveNewGalaxy bng;
	
	public GamePanel(BraveNewGalaxy bng, DisplayMode displayMode)
	{
		this.displayMode = displayMode;
		this.bng = bng;
		
		setBackground(Color.white);
		
		setFocusable(true);
		requestFocus();
		readyForTermination();
		
	} // End of GamePanel() constructor.
	
	public void run()
	{		
		ScreenManager screen = new ScreenManager();
		try {
			screen.setFullScreen(displayMode, bng);
	
			while (gameState != GameState.ST_EXIT) {
				gameState.update();	
				gameState.render();
			}
		} finally {
			screen.restoreScreen();
		}		
		System.exit(1);
	} // End of run().
	
	private void readyForTermination()
	{
		addKeyListener (new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ESCAPE) {
					stopGame();
				}
			}
		});
	} // End of readyForTermination().
	
	public void addNotify()
	{
		super.addNotify();
		startGame();
	} // End of addNotify().
	
	private void startGame()
	{
		if (gameLoop == null) {
			gameLoop = new Thread(this);
			gameLoop.start();
		}
	}
	
	// ------------  Game's life cycle methods  ------------ //
	
	public void resumeGame()
	{
		isPaused = false;
	}
	
	public void stopGame()
	{
		gameState = GameState.ST_EXIT;
	}
	
	public void pauseGame()
	{
		isPaused = true;
	}
	
} // End of Game class.