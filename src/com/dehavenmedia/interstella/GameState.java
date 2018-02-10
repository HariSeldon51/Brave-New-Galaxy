package com.dehavenmedia.interstella;

import java.awt.Color;

public enum GameState implements State
{	
	
	ST_START {	// Default game state -- it's logic moves it to the correct starting state.
		
		public void update(GameStateManager gsm, double delta) {
			gsm.changeState(GameState.ST_COMPANY);
		}
	},		
	ST_COMPANY, 	// Company logo splash image.
	ST_TITLE, 		// Game title splash image.
	ST_INTRO, 		// Intro cinematic (if used).
	ST_MAINMENU,  	// Main menu screen.
	ST_SETUP, 		// Character set up screen.
	ST_GENERATE, 	// World generation/reload.
	ST_WORLD, 		// Main playing screen.	
	ST_END,			// Player requests to end the game.
		
	ST_TEST {
		
		public void instate(GameStateManager gsm, Game game) {
			//instate necessary objects and systems
		}
		
		public void update(GameStateManager gsm, double delta) {
			//iterate through all updateable objects.
		}
		
		public void dispose() {
			//dispose of no longer needed objects.
		}
	}
	
} // End of GameState enum.