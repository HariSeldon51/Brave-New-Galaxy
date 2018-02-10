package com.dehavenmedia.interstella;

public class GameStateManager {

	private GameState gameState; // Tracks the game's state throughout execution.
	private GameState nextState; // The next state the game should transition to at the beginning of the next loop.
	private static String MODE;
	
	public GameStateManager(GamePanel gameContext, String gameMode)
	{
		MODE = gameMode;
		
		if (MODE == "test") {
			gameState = GameState.ST_TEST;
		} else {
			gameState = GameState.ST_START;
		}
		
		gameState.instate(this, gameContext);
	}
	
	public void changeState(GameState gs)
	{
		nextState = gs;
	} // End of changeState().
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void update(GamePanel gameContext, double delta)
	{
		gameState.update(this, gameContext, delta);
	}
	
	public void stateUpdate(GamePanel gameContext)
	{
		if (nextState != null) {
			gameState.dispose(this, gameContext);
			gameState = nextState;
			gameState.instate(this, gameContext);
			nextState = null;
		}		
	}

}
