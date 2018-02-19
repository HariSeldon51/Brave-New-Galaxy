package com.degauvendesign.interstella;

public interface GameState {
	
	public void instate(Game game) throws Exception;
	
	public void render(Window window);
	
	public void input(Window window, MouseInput mouseInput);

	public void update(Game game, float delta, MouseInput mouseInput);
	
	public void dispose(Game game);
}