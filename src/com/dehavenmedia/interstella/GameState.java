package com.dehavenmedia.interstella;

public enum GameState implements State
{	
	
	ST_DEFAULT {	// Default game state -- it's logic moves it to the correct starting state.
		
		public void instate() {
			
		}
		
		public void update(double delta) {
			//iterate through all updateable objects.
		}
		
		public void render() {
			//iterate through all drawable objects
		}
	},		
	ST_COMPANY, 	// Company logo splash image.
	ST_TITLE, 		// Game title splash image.
	ST_INTRO, 		// Intro cinematic (if used).
	ST_MAINMENU,  	// Main menu screen.
	ST_SETUP, 		// Character set up screen.
	ST_GENERATE, 	// World generation/reload.
	ST_WORLD, 		// Main playing screen.		
	
} // End of GameState enum.