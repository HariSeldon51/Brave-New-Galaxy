package com.degauvendesign.bravenewgalaxy;

import com.degauvendesign.interstella.Game;
import com.degauvendesign.interstella.GameState;
import com.degauvendesign.interstella.Renderer;
import com.degauvendesign.interstella.Window;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.opengl.GL11.glViewport;

public class TestState implements GameState {
	
	private int direction = 0;
	private float color = 0.5f;
	private final Renderer renderer = new Renderer();

	@Override
	public void instate(Game game) {

		renderer.init();	
	}
	
	@Override
	public void render(Window window) {
		
		if ( window.isResized() ) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

		window.setClearColor(color, color, color, 0.0f);
		renderer.clear();
	}
	
	@Override
	public void input(Window window) {

		if ( window.isKeyPressed(GLFW_KEY_UP) ) {
			direction = 1;
		} else if ( window.isKeyPressed(GLFW_KEY_DOWN) ) {
			direction = -1;
		} else {
			direction = 0;
		}	
	}

	@Override
	public void update(Game game, double delta) {

		color += direction * 0.01f;
		
		if (color > 1) {
			color = 1.0f;
		} else if (color < 0) {
			color = 0.0f;
		}
	}

	@Override
	public void dispose(Game game) {
		// TODO Auto-generated method stub
		
	}
}
