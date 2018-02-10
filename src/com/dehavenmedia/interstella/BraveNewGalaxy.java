package com.dehavenmedia.interstella;

public class BraveNewGalaxy
{
	//Default game settings
	private static String MODE = "test"; // Can be prod, dev, or test
	private static String TITLE = "Brave New Galaxy";	
	
	// Reference to game's rendering object
	private static Game game;
	
	public BraveNewGalaxy()
	{
		game = new Game(MODE, TITLE);
		game.start();
	} // End of BraveNewGalaxy() constructor.
	
	// ------------  Main method  ------------ //
	
	public static void main(String[] args)
	{
		new BraveNewGalaxy();
	} // End of main().

}