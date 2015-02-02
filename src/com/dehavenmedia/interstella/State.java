package com.dehavenmedia.interstella;

public interface State
{		
	default public void instate() {
		// Initialize state for upcoming use.
	}
	
	default public void update(double delta) {
		// Listen for new events to act upon.
	}
	
	default public void render() {
		// Render things.
	}
	
	default public void dispose() {
		// Dispose of state.
	}
} // End of State interface.