package com.dehavenmedia.interstella;

public interface State
{		
	default public void instate(GameStateManager gsm, Game game) {
		// Initialize state for upcoming use.
	}
	
	default public void update(GameStateManager gsm, Game game, double delta) {
		// Listen for new events to act upon.
	}
	
	default public void dispose(GameStateManager gsm, Game game) {
		// Dispose of state.
	}
} // End of State interface.