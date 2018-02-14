package com.degauvendesign.bravenewgalaxy;

import com.degauvendesign.interstella.Entity;
import com.degauvendesign.interstella.Game;
import com.degauvendesign.interstella.GameState;
import com.degauvendesign.interstella.Renderer;
import com.degauvendesign.interstella.Window;
import com.degauvendesign.interstella.graph.Mesh;
import com.degauvendesign.interstella.graph.Texture;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector3f;

public class TestState implements GameState {
	
	private int displxInc = 0;
	private int displyInc = 0;
	private int displzInc = 0;	
	private int scaleInc = 0;
	
	private final Renderer renderer;
	
	private Entity[] entities;
	
	public TestState() {
		renderer = new Renderer();
	}

	@Override
	public void instate(Game game) throws Exception {
		
		renderer.init(game.getWindow());
			
		// Create vertex coordinates for mesh
        float[] positions = new float[] {
            -0.5f, 0.5f, 0.5f, // V0
            -0.5f, -0.5f, 0.5f, // V1
            0.5f, -0.5f, 0.5f, // V2
            0.5f, 0.5f, 0.5f,// V3
            -0.5f, 0.5f, -0.5f, // V4
            0.5f, 0.5f, -0.5f, // V5
            -0.5f, -0.5f, -0.5f, // V6
            0.5f, -0.5f, -0.5f, // V7
            
            // For text coords in top face
            -0.5f, 0.5f, -0.5f, // V8: V4 repeated
            0.5f, 0.5f, -0.5f, // V9: V5 repeated
            -0.5f, 0.5f, 0.5f, // V10: V0 repeated
            0.5f, 0.5f, 0.5f, // V11: V3 repeated

            // For text coords in right face
            0.5f, 0.5f, 0.5f, // V12: V3 repeated
            0.5f, -0.5f, 0.5f, // V13: V2 repeated

            // For text coords in left face
            -0.5f, 0.5f, 0.5f, // V14: V0 repeated
            -0.5f, -0.5f, 0.5f, // V15: V1 repeated

            // For text coords in bottom face
            -0.5f, -0.5f, -0.5f, // V16: V6 repeated
            0.5f, -0.5f, -0.5f, // V17: V7 repeated
            -0.5f, -0.5f, 0.5f, // V18: V1 repeated
            0.5f, -0.5f, 0.5f, // V19: V2 repeated
        };
        
        // Create texture coordinate for mesh
        float[] textCoords = new float[] {
    		0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,
            
            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            
            // For text coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,

            // For text coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,

            // For text coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,

            // For text coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,
        };
        
        // Setup vertex indices for mesh
        int[] indices = new int[] {
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            8, 10, 11, 9, 8, 11,
            // Right face
            12, 13, 7, 5, 12, 7,
            // Left face
            14, 15, 6, 4, 14, 6,
            // Bottom face
            16, 18, 19, 17, 16, 19,
            // Back face
            4, 6, 7, 5, 4, 7,
        };
		
        Texture texture = Texture.loadTexture("grassblock.png");
		Mesh mesh = new Mesh(positions, textCoords, indices, texture);
		Entity entity = new Entity(mesh);
		entity.setPosition(0,  0,  -2);
		entities = new Entity[] { entity };
	}
	
	@Override
	public void input(Window window) {

		displyInc = 0;
		displxInc = 0;
		displzInc = 0;
		scaleInc = 0;
		
		if (window.isKeyPressed(GLFW_KEY_UP)) {
			displyInc = 1;
		} else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
			displyInc = -1;
		} else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
			displxInc = -1;
		} else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
			displxInc = 1;
		} else if (window.isKeyPressed(GLFW_KEY_A)) {
			displzInc = -1;
		} else if (window.isKeyPressed(GLFW_KEY_Q)) {
			displzInc = 1;
		} else if (window.isKeyPressed(GLFW_KEY_Z)) {
			scaleInc = -1;
		} else if (window.isKeyPressed(GLFW_KEY_X)) {
			scaleInc = 1;
		}
	}

	@Override
	public void update(Game game, double delta) {

		for (Entity entity : entities) {
			
			//Update entity position
			Vector3f itemPos = entity.getPosition();
			float posX = itemPos.x + displxInc * 0.001f;
			float posY = itemPos.y + displyInc * 0.001f;
			float posZ = itemPos.z + displzInc * 0.001f;
			entity.setPosition(posX, posY, posZ);
			
			// Update entity scale
			float scale = entity.getScale();
			scale += scaleInc * 0.001f;
			if (scale < 0) scale = 0;
			entity.setScale(scale);
			
			// Update entity rotation angle so as to continuously rotate.
			float rotation = entity.getRotation().y + 0.3f;
			if ( rotation >= 360 ) rotation = 0;
			entity.setRotation(rotation, rotation, rotation);
		}
	}
	
	@Override
	public void render(Window window) {
		
        renderer.render(window, entities);
	}

	@Override
	public void dispose(Game game) {
		renderer.dispose();	
		
		for (Entity entity : entities) {
			entity.getMesh().dispose();
		}
		
	}
}
