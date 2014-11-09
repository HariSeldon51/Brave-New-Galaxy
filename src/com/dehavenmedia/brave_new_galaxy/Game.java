package com.dehavenmedia.brave_new_galaxy;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {
	
	GameState myGameState = GameState.ST_COMPANY; //The initial state the game starts out in
	
	//main method
	public static void main(String[] argv) {
		Game myGame = new Game();
		myGame.start();
		myGame.update();
		myGame.end();
	}
	
	//start method
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}		
		// init OpenGL here
	}
	
	//update method
	public void update() {
		
		while (!Display.isCloseRequested()) {
			
			// render OpenGL here
			
			Display.update();
		}
	}
	
	//end method
	public void end () {
		Display.destroy();
		System.exit(1);
	}
}