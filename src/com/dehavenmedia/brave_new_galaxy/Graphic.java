package com.dehavenmedia.brave_new_galaxy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Graphic {
	
	public Graphic(int width, int height, String Image) {
		Texture thisImage;
		String thisPNG = Image;
		
		try {
		thisImage = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + thisPNG));
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
	
	public void draw(int x, int y){
		// draw image at x and y locations
	}
}
