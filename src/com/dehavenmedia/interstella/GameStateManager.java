package com.dehavenmedia.interstella;

/**
 * 
 * @author William DeHaven
 *
 */

public class GameStateManager {

	private GameState gameState; // Tracks the game's state throughout execution.
	private volatile boolean isPaused = false; // Flag -- is the game currently paused?
	private volatile boolean isRunning = false; // Flag -- is the game currently running?
	
	public GameStateManager()
	{
		gameState = GameState.ST_DEFAULT;
	}
	
	public GameStateManager(GameState gs)
	{
		gameState = gs;
	}
	
	public void changeState(GameState gs)
	{
		gameState.dispose();
		gameState = gs;
		gameState.instate();		
	} // End of changeState().
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void update()
	{
		gameState.update();
	}
	
	public void render()
	{
		gameState.render();
	}
	
	// ------------   Game's status checking methods   ------------ //
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	public boolean isPaused()
	{
		return isPaused;
	}
	
	// ------------  Game's life cycle methods  ------------ //
	
	public void startGame()
	{
		isRunning = true;
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
		
}
