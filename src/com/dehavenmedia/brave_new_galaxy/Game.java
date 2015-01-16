package com.dehavenmedia.brave_new_galaxy;

public class Game {
	
	GameState gameState = GameState.ST_LOAD; //The initial state the game starts out in
	
	public static void main(String[] argv)
	{
		Game myGame = new Game();
		myGame.init();
		myGame.run();
		myGame.end();
	} // End of main().
	
	public void init()
	{
		// No members as of yet.
	} // End of gameStart().
	
	public void run()
	{		
		while (gameState != GameState.ST_EXIT) {
			gameState.update();	
			gameState.render();
		}
	} // End of gameLoop().
	
	public void end()
	{
		System.exit(1);
	} // End of gameEnd().
	
} // End of Game class.