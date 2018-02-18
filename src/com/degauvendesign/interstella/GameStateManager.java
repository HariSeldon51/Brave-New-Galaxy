package com.degauvendesign.interstella;

import java.util.HashMap;
import java.util.Map;

public class GameStateManager {

	private HashMap<String, GameState> gameStates; // Tracks the game's state throughout execution.
	private GameState currentState; // The current state of the game.
	private GameState nextState; // The next state the game should transition to at the beginning of the next loop.
	private static String MODE;
	private static Game GAME;
	private static Window WINDOW;
	
	public GameStateManager(Game game, Window window, String gameMode) {
		
		GAME = game;
		MODE = gameMode;
		WINDOW = window;
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
	public void setState(String newState) throws Exception {
		
		if (currentState == null) {
			currentState = gameStates.get(newState);
			currentState.instate(GAME);
		} else {
			nextState = gameStates.get(newState);
		}
	}
	
	//  ------------   Game's gameloop methods   ------------ //
	
	public void input(MouseInput mouseInput) {
		currentState.input(WINDOW, mouseInput);
	}
	
	public void update(double delta, MouseInput mouseInput) throws Exception {
			
		// Change the game state if the previous loop set a different nextState.
		if (nextState != null) {
			
			if (currentState != null) {
				currentState.dispose(GAME);
			}
			
			currentState = nextState;
			currentState.instate(GAME);
			nextState = null;
		}
		
		String msg = "The initial state has not been set. Use GameStateManager.setState(String state).";
		
		try {
			currentState.update(GAME, delta, mouseInput);
		} catch (Exception e) {
			throw new NullPointerException(msg);
		}
		
	}
	
	public void render() {
		
		currentState.render(WINDOW);
	}
	
	public void cleanup() {
		currentState.dispose(GAME);
	}
}
