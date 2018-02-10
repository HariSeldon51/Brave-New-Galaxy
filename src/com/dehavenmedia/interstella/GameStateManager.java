package com.dehavenmedia.interstella;

public class GameStateManager {

	private GameState gameState; // Tracks the game's state throughout execution.
	private GameState nextState; // The next state the game should transition to at the beginning of the next loop.
	private static String MODE;
	
	public GameStateManager(Game game, String gameMode)
	{
		MODE = gameMode;
		
		if (MODE == "test") {
			gameState = GameState.ST_TEST;
		} else {
			gameState = GameState.ST_START;
		}
		
		gameState.instate(this, game);
	}
	
	public void changeState(GameState gs)
	{
		nextState = gs;
	} // End of changeState().
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void update(Game game, double delta)
	{
		gameState.update(this, game, delta);
	}
	
	public void stateUpdate(Game game)
	{
		if (nextState != null) {
			gameState.dispose(this, game);
			gameState = nextState;
			gameState.instate(this, game);
			nextState = null;
		}		
	}

}
