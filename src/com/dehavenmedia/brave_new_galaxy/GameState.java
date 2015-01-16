package com.dehavenmedia.brave_new_galaxy;

public enum GameState implements State
{
	
	ST_COMPANY, 	// Company logo splash image.
	ST_TITLE, 		// Game title splash image.
	ST_INTRO, 		// Intro cinematic (if used).
	ST_MAINMENU,  	// Main menu screen.
	ST_SETUP, 		// Character set up screen.
	ST_GENERATE, 	// World generation.
	ST_WORLD, 		// Main playing screen.
	ST_EXIT; 		// Program exit flag.
	
} // End of GameState enum.

