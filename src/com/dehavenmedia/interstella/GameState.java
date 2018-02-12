package com.dehavenmedia.interstella;

public interface GameState {
	
	public void instate(GameStateManager gsm, Game game);
	
	public void render(GameStateManager gsm, Game game);
	
	public void input(Window window);

	public void update(GameStateManager gsm, Game game, double delta);
	
	public void dispose(GameStateManager gsm, Game game);
} // End of State interface.