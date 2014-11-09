package com.dehavenmedia.brave_new_galaxy;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.dehavenmedia.brave_new_galaxy.Enums.GameState;

public class Game {
	
	GameState myGameState = GameState.ST_COMPANY; //The initial state the game starts out in
	
	//main method
	public static void main(String[] argv) {
		Game myGame = new Game();
		myGame.start();
		myGame.update();
		myGame.end();
	}
	
	//start method
	public void start() {
		initGL(800, 600);
		init();
	}
	
	//update method
	public void update() {
		
		while (!Display.isCloseRequested() && myGameState != GameState.ST_EXIT) {
			myGameState.listen();
			myGameState.step();	
			myGameState.render();
			Display.update();
		}
	}
	
	//end method
	public void end() {
		Display.destroy();
		System.exit(1);
	}
	
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}		
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
        
        // enable alpha blending
       	GL11.glEnable(GL11.GL_BLEND);
       	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
       	GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void init() {
		Texture GameLogo;
		try {
			GameLogo = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Brave New Galaxy Logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}