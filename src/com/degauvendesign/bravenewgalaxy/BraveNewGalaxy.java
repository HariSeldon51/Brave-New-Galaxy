package com.degauvendesign.bravenewgalaxy;

import com.degauvendesign.interstella.Game;
import com.degauvendesign.interstella.GameStateManager;

public class BraveNewGalaxy extends Game {
	
	// References to game and game components
	private static Game game;
	private static GameStateManager stateManager;
	
	public BraveNewGalaxy() {
		
		// Params:
		//   target fps,
		//   target ups,
		//   max frame skips,
		//   initial window width,
		//   initial window height,
		//   use vsync,
		//   game mode,
		//   window title
		super(75, 30, 5, 800, 600, true, "Brave New Galaxy", "test");
		
	}
	
	@Override
	public void init() {
		
		stateManager = game.getGameStateManager();
		stateManager.add("test", new TestState());
		stateManager.setState("test");
	}
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args) {
		
		// Instantiate and start game.
		game = new BraveNewGalaxy();	
		game.start();	
	}
}