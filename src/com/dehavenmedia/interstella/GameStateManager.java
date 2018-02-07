package com.dehavenmedia.interstella;

public class GameStateManager {

	private GameState gameState; // Tracks the game's state throughout execution.
	private GameState nextState; // The next state the game should transition to at the beginning of the next loop.
	private static String MODE;
	
	public GameStateManager(GamePanel gameContext, String gameMode)
	{
		MODE = gameMode;
		gameState = GameState.ST_DEFAULT;
		gameState.instate();
	}
	
	public GameStateManager(GamePanel gameContext, GameState gs)
	{
		gameState = gs;
		gameState.instate();
	}
	
	public void changeState(GameState gs)
	{
		nextState = gs;
	} // End of changeState().
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void update(double delta)
	{
		gameState.update(delta);
	}
	
	public void render()
	{
		gameState.render();
	}
	
	public void stateUpdate()
	{
		if (nextState != null) {
			gameState.dispose();
			gameState = nextState;
			gameState.instate();
			nextState = null;
		}		
	}

}
