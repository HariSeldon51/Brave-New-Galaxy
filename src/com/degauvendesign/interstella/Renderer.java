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
	
	private Matrix4f projectionMatrix;
    private static ShaderProgram shaderProgram;
	
	public Renderer() {     
		// TODO: This is a stub for the constructor -- may not be needed, but leave here.
    }
    
    public void init(Window window) throws Exception {
    	
    	// Create shader
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("resources/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("resources/fragment.fs"));
        shaderProgram.link();
        
        //Create projection matrix
        // TODO: Allow aspect ratio to change if screen mode changes.
        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shaderProgram.createUniform("projectionMatrix");
    }

    public void clear() {   	
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public void render(Window window, Mesh mesh) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Bind to the VAO
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        // Draw the mesh
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        shaderProgram.unbind();
    }
    
    public void dispose() {
        if (shaderProgram != null) {
            shaderProgram.dispose();
        }
    }
}
