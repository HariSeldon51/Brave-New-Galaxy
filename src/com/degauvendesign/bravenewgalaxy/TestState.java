package com.degauvendesign.bravenewgalaxy;

import com.degauvendesign.interstella.Entity;
import com.degauvendesign.interstella.Game;
import com.degauvendesign.interstella.GameState;
import com.degauvendesign.interstella.MouseInput;
import com.degauvendesign.interstella.Renderer;
import com.degauvendesign.interstella.Window;
import com.degauvendesign.interstella.graph.Camera;
import com.degauvendesign.interstella.graph.Mesh;
import com.degauvendesign.interstella.graph.Texture;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class TestState implements GameState {
	
	private static final float MOUSE_SENSITIVITY = 0.2f;
	private static final float CAMERA_POS_STEP = 0.05f;
	
	private final Vector3f cameraInc;
	private final Renderer renderer;
	private final Camera camera;
	
	private Entity[] entities;
	
	public TestState() {
		renderer = new Renderer();
		camera = new Camera();
		cameraInc = new Vector3f(0, 0, 0);
	}

	@Override
	public void instate(Game game) throws Exception {
		
		Window window = game.getWindow();
		renderer.init(window);
		window.hideCursor();
			
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
		
		Entity entity1 = new Entity(mesh);
		entity1.setScale(0.5f);
		entity1.setPosition(0, 0, -2);
		
		Entity entity2 = new Entity(mesh);
		entity2.setScale(0.5f);
		entity2.setPosition(0.5f, 0.5f, -2);
		
		Entity entity3 = new Entity(mesh);
		entity3.setScale(0.5f);
		entity3.setPosition(0, 0, -2.5f);
		
		Entity entity4 = new Entity(mesh);
		entity4.setScale(0.5f);
		entity4.setPosition(0.5f, 0, -2.5f);
			
		entities = new Entity[] { entity1, entity2, entity3, entity4 };
	}
	
	@Override
	public void input(Window window, MouseInput mouseInput) {

		cameraInc.set(0, 0, 0);
		
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
        
        if (window.isKeyPressed(GLFW_KEY_Q)) {
            
        	if (window.cursorVisible()) {
        		window.hideCursor();
        	} else {
        		window.showCursor();
        	}
        }
        
	}

	@Override
	public void update(Game game, float delta, MouseInput mouseInput) {

		// Update camera position
        camera.movePosition(cameraInc.x * delta * CAMERA_POS_STEP,
        					cameraInc.y * delta * CAMERA_POS_STEP,
        					cameraInc.z * delta * CAMERA_POS_STEP);

        // Update camera based on mouse            
        Vector2f rotVec = mouseInput.getDisplVec();
        camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
	}
	
	@Override
	public void render(Window window) {
		
        renderer.render(window, camera, entities);
	}

	@Override
	public void dispose(Game game) {
		renderer.dispose();	
		
		for (Entity entity : entities) {
			entity.getMesh().dispose();
		}
		
	}
}
