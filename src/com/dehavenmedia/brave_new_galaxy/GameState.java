package com.dehavenmedia.brave_new_galaxy;


public enum GameState implements State {
	ST_LOAD, 
	ST_COMPANY, 
	ST_TITLE, 
	ST_INTRO, 
	ST_MAINMENU, 
	ST_SETUP, 
	ST_GENERATE, 
	ST_WORLD, 
	ST_EXIT, 
	ST_NULL;
}
