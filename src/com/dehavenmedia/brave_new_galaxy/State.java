package com.dehavenmedia.brave_new_galaxy;

public interface State
{
	
	default public void update()
	{
		// Listen for new events to act upon.
	}
	
	default public void render()
	{
		// Render graphics buffer.
	}

} // End of State interface.
