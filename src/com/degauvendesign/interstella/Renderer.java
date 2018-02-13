package com.degauvendesign.interstella;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import com.degauvendesign.interstella.graph.*;

public class Renderer {
	
    private static ShaderProgram shaderProgram;
	
	// TODO: Not sure if this class is needed -- remove if not used.
	public Renderer() {     
		
    }
    
    public void init() throws Exception {
    	
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("resources/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("resources/fragment.fs"));
        shaderProgram.link();
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
