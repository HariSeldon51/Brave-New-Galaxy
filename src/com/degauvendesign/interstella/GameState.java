package com.degauvendesign.interstella;

public interface GameState {
	
	public void instate(Game game) throws Exception;
	
	public void render(Window window);
	
	public void input(Window window);

	public void update(Game game, double delta);
	
	public void dispose(Game game);
}