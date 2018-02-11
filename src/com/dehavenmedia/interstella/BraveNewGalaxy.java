package com.dehavenmedia.interstella;

public class BraveNewGalaxy {
	
	//Default game settings
	private final String MODE = "test"; // Can be prod, dev, or test
	private final String TITLE = "Brave New Galaxy";	
	
	// Reference to game's rendering/logic object
	private static Game game;
	
	public BraveNewGalaxy() {
		
		// Game constructor params:
		//   target fps,
		//   target ups,
		//   max frame skips,
		//   initial window width,
		//   initial window height,
		//   use vsync,
		//   game mode,
		//   window title
		game = new Game(75, 30, 5, 800, 600, true, MODE, TITLE);
	} // End of BraveNewGalaxy() constructor.
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args) {
		
		new BraveNewGalaxy();
		game.start();
	} // End of main().
}