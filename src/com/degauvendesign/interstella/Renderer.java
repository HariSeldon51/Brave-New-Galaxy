package com.degauvendesign.interstella;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.joml.Matrix4f;
import com.degauvendesign.interstella.graph.*;

public class Renderer {
	
	// TODO: Make it so the Field of View can be changed by the player as a setting.
	private static final float FOV = (float) Math.toRadians(60.0f);
	
	// TODO: Will these near and far clip distances need to be settable too?
	private static final float Z_NEAR = 0.01f;
	private static final float Z_FAR = 1000.f;
	
	private final Transform transform;
	private Matrix4f projectionMatrix;
    private static ShaderProgram shaderProgram;
	
	public Renderer() {     

		transform = new Transform();
	}
    
    public void init(Window window) throws Exception {
    	
    	// Create shader
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("resources/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("resources/fragment.fs"));
        shaderProgram.link();
        
        //Create projection and world matrices
        // TODO: Allow aspect ratio to change if screen mode changes.
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("worldMatrix");
        shaderProgram.createUniform("texture_sampler");
        
        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void clear() {   	
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public void render(Window window, Entity[] entities) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();
        
        // Update projection Matrix
        projectionMatrix = transform.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Update texture to use
        shaderProgram.setUniform("texture_sampler", 0);
        
        // Render each entity
        for (Entity entity : entities) {
        	
        	// Set the worldMatrix for this entity
        	Matrix4f worldMatrix = transform.getWorldMatrix(
    			entity.getPosition(),
    			entity.getRotation(),
    			entity.getScale());
        	shaderProgram.setUniform("worldMatrix", worldMatrix);
        	
        	// Render the entity
        	entity.getMesh().render();
        	
        }

        shaderProgram.unbind();
    }
    
    public void dispose() {
        if (shaderProgram != null) {
            shaderProgram.dispose();
        }
    }
}
