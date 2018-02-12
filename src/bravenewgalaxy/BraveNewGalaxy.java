package bravenewgalaxy;

import com.dehavenmedia.interstella.Game;
import com.dehavenmedia.interstella.GameStateManager;

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
		
	} // End of BraveNewGalaxy() constructor.
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args) {
		
		// Instantiate game and initialize game components
		game = new BraveNewGalaxy();
		
		stateManager = game.getGameStateManager();
		stateManager.add("test", new TestState());
		stateManager.setState("test");
		
		game.start();
		
	} // End of main().
}