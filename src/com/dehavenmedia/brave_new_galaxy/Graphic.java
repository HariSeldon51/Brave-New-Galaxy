package com.dehavenmedia.brave_new_galaxy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Graphic {
	private Texture myImage;
	private String PNGsource;
	private int myWidth;
	private int myHeight;	
	
	//Constructor: Loads the base image of the Graphic and couples it with width and height properties
	public Graphic(int width, int height, String PNG) {
		this.PNGsource = PNG;
		this.myWidth = width;
		this.myHeight = height;
		
		try {
		myImage = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + this.PNGsource));
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		Display.destroy();
		System.exit(1);	
		} catch (IOException e) {
		e.printStackTrace();
		Display.destroy();
		System.exit(1);
		}
	}
	
	public int getWidth() {
		return this.myWidth;
	}
	
	public int getHeight() {
		return this.myHeight;
	}
	
	public void draw(int x, int y){
		// draw image at x and y locations
	}
}
