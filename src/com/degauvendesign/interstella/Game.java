package com.degauvendesign.interstella;

import java.util.Map;

public class Game implements Runnable {
	
	//Declare game constants
	private static int MS_PER_FRAME;
	private static int MS_PER_UPDATE;
	private static int MAX_FRAME_SKIPS;
	private static int INIT_WIDTH;
	private static int INIT_HEIGHT;
	private static String G_MODE;
	private static String G_TITLE;
	private static boolean VSYNC;
	
	// Reference to window object
	private Window window;
	
	// Reference to main thread of the game.
	private Thread gameLoop;
	
	// Reference to current GameState (Menu, Load, Play, etc).
	private GameStateManager gameStateManager;
	
	// Reference to mouse input listener
	private final MouseInput mouseInput;
	
	// Initialize basic game states.
	private volatile boolean isPaused = false; // Flag -- is the game currently paused?
	private volatile boolean isRunning = false; // Flag -- is the game currently running?

	public Game(int fps, int ups, int skips, int width, int height, boolean vsync, String title, String mode) {
		
		//Initialize game constants
		MS_PER_FRAME = 1000/fps;
		MS_PER_UPDATE = 1000/ups;
		MAX_FRAME_SKIPS = skips;		
		INIT_WIDTH = width;
		INIT_HEIGHT = height;
		G_MODE = mode;
		G_TITLE = title;
		VSYNC = vsync;
		
		// Instantiate window manager (GLFW implementation).
		window = new Window(G_TITLE, INIT_WIDTH, INIT_HEIGHT, VSYNC);
		
		// Instantiate game state manager (game logic).
		gameStateManager = new GameStateManager(this, window, G_MODE);
	
		// Instantiate mouse input listener.
		mouseInput = new MouseInput();
	}
	
	public Game(int fps, int ups, int skips, int width, int height, boolean vsync, String title) {
		
		this(fps, ups, skips, width, height, vsync, title, "dev");
	}
	
	public Game(int fps, int ups, int skips, int width, int height, boolean vsync) {
		
		this(fps, ups, skips, width, height, vsync,  "A Game Powered by Interstella Game Engine", "dev");
	}
	
	// Start() is found in the Game Lifecycle Methods section
	// at the end of the class. Start() initiates run().
	public void run() {
		
		isRunning = true;
		
		try {
			
			// Initializes all LWJGL and GFLW settings and objects.
			window.init();
			mouseInput.init(window);
			init();
			
			gameLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void init() throws Exception {		
		// This method is a stub, intended to be overriden by the developer
		// to handle initial game setup.
	}
	
	private void gameLoop() throws Exception {
		
		// Initialize variables for game loop.
		int numSkippedFrames = 0;
		float currentTime;
		float maximumFrameTime = MS_PER_FRAME;
		float previousTime = getCurrTime(); //Sets the time the game loop starts
		
		float elapsedTime;
		float accumulatedTime = 0;
		float maximumUpdateTime = MS_PER_UPDATE;

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( isRunning ) {
			
			currentTime = getCurrTime(); //Sets the time this particular game loop starts
			elapsedTime = currentTime - previousTime; //Measures the time that has elapsed since the beginning of the last game loop
			accumulatedTime += elapsedTime; // Add elapsed time to the time left over from the last loop.
			
			// Receive input
			input();
			
			// Update game
			if (accumulatedTime >= maximumUpdateTime) {
				update((accumulatedTime/maximumUpdateTime) / 1000000); // Update game based on current state, time adjusted for speed and elapsed time.
				accumulatedTime %= maximumUpdateTime; 
			}
			
			// Render game
			if (getCurrTime() - previousTime <= maximumFrameTime || numSkippedFrames >= MAX_FRAME_SKIPS) {
				render();
				numSkippedFrames = 0; //Reset the skipped frames counter
			} else {
				numSkippedFrames++; //Skip a frame render and increment the skipped frames counter
			} 
			
			// If window received a close event, change isRunning to false.
			isRunning = !window.windowShouldClose();
			
			//Saves the time this game loop started for the next game loop to evaluate
			previousTime = currentTime;
		}
		
		stop(); // Handles cleanup before program closes.
	}
	
	private void input() {
		
		mouseInput.input(window);
		gameStateManager.input(mouseInput);
	}
	
	private void update(float delta) throws Exception {
		
		gameStateManager.update(delta, mouseInput); //Update the game, with the ratio of elapsed time to frame length as the delta.
	}
	
	private void render() {
		
		gameStateManager.render();
		window.update();
	}
	
	// ------------  Game's life cycle methods  ------------ //
	
	public void start() {
		
		if (gameLoop == null || !isRunning) {
			gameLoop = new Thread(this);
		}		
		gameLoop.start();
		
	} // End of start()
	
	public void pause() {
		
		isPaused = true;
		
	} // End of pause()
		
	public void resume() {
		
		isPaused = false;
		
	} // End of resume()
		
	public void stop() {
		
		isRunning = false;
		gameStateManager.cleanup();
		window.close();
		
	} // End of stop()
	
	// ------------  Game's utility methods  ------------ //
	
	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}
	
	public void addState(String stateName, GameState gameState ) {
		gameStateManager.add(stateName, gameState);
	}
	
	public void addState(Map<String, GameState> gameState ) {
		gameStateManager.add(gameState);
	}
	
	public void setState(String stateName) throws Exception {
		gameStateManager.setState(stateName);
	}
	
	public Window getWindow() {
		return window;
	}
	
	long getCurrTime() {
		
		return System.nanoTime();
	}
	
} // End of Game class.