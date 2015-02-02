package com.dehavenmedia.interstella;

/**
 * 
 * @author William DeHaven
 *
 */

public class GameStateManager {

	private GameState gameState; // Tracks the game's state throughout execution.
	private GameState nextState; // The next state the game should transition to at the beginning of the next loop.
	private volatile boolean isPaused = false; // Flag -- is the game currently paused?
	private volatile boolean isRunning = false; // Flag -- is the game currently running?
	
	public GameStateManager()
	{
		gameState = GameState.ST_DEFAULT;
		gameState.instate();
	}
	
	public GameStateManager(GameState gs)
	{
		gameState = gs;
	}
	
	public void changeState(GameState gs)
	{
		nextState = gs;
	} // End of changeState().
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void loop()
	{
		gameState.update();
		gameState.render();
		stateUpdate();
	}
	
	private void stateUpdate()
	{
		if (nextState != null) {
			gameState.dispose();
			gameState = nextState;
			gameState.instate();
			nextState = null;
		}		
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
