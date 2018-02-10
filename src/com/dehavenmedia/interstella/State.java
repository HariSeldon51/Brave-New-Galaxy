package com.dehavenmedia.interstella;

public interface State
{		
	default public void instate(GameStateManager gsm, GamePanel gameContext) {
		// Initialize state for upcoming use.
	}
	
	default public void update(GameStateManager gsm, GamePanel gameContext, double delta) {
		// Listen for new events to act upon.
	}
	
	default public void dispose(GameStateManager gsm, GamePanel gameContext) {
		// Dispose of state.
	}
} // End of State interface.