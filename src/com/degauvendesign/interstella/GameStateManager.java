package com.degauvendesign.interstella;

import java.util.HashMap;
import java.util.Map;

public class GameStateManager {

	private HashMap<String, GameState> gameStates; // Tracks the game's state throughout execution.
	private GameState currentState; // The current state of the game.
	private GameState nextState; // The next state the game should transition to at the beginning of the next loop.
	private static String MODE;
	
	public GameStateManager(String gameMode) {
		
		MODE = gameMode;
		gameStates = new HashMap<String, GameState>();
	}
	
	public void add(Map<String, GameState> stateMap) {
		
		gameStates.putAll(stateMap);
	}
	
	// Alternate to the previous method -- adds only one state
	public void add(String string, GameState gameState) {
		
		gameStates.put(string, gameState);
	}
	
	// Sets the current state to a new state, thereby changing the game state.
	public void setState(String newState) {
		
		if (currentState == null) {
			currentState = gameStates.get(newState);
		} else {
			nextState = gameStates.get(newState);
		}
	}
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void input(Window window) {
		currentState.input(window);
	}
	
	public void update(Game game, double delta) {
			
		// Change the game state if the previous loop set a different nextState.
		if (nextState != null) {
			
			if (currentState != null) {
				currentState.dispose(game);
			}
			
			currentState = nextState;
			currentState.instate(game);
			nextState = null;
		}
		
		String msg = "The initial state has not been set. Use GameStateManager.setState(String state).";
		
		try {
			currentState.update(game, delta);
		} catch (Exception e) {
			throw new NullPointerException(msg);
		}
		
	}
	
	public void render(Window window) {
		
		currentState.render(window);
	}
}
