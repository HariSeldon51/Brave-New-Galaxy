package com.degauvendesign.interstella.graph;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class Mesh {
	
	private final int vaoId;
	private final int posVboId;
	private final int idxVboId;
	private final int colorVboId;
	private final int vertexCount;
	
	public Mesh( float[] positions, float[] colors, int[] indices ) {
		
		IntBuffer indicesBuffer = null;
		FloatBuffer posBuffer = null;
		FloatBuffer colorBuffer = null;
		
		try {
			
			vertexCount = indices.length;
			
			// Create the VAO and bind to it.
	        vaoId = glGenVertexArrays();
	        glBindVertexArray(vaoId);
			
	        // Create the vertex coordinate VBO and bind to it.
            posVboId = glGenBuffers();
            posBuffer = MemoryUtil.memAllocFloat(positions.length);
			posBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            // Define structure of the data for shaders to access.
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            
            // Create the vertex color VBO and bind to it.
            colorVboId = glGenBuffers();
            colorBuffer = MemoryUtil.memAllocFloat(colors.length);
            colorBuffer.put(colors).flip();
            glBindBuffer(GL_ARRAY_BUFFER, colorVboId);
            glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
            // Define structure of the data for shaders to access.
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
            
	        // Create the vertex index VBO and bind to it.
            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            // Unbind the vertex coordinate VBO
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            // Unbind the VAO
            glBindVertexArray(0);
			
		} finally {
			
			if (posBuffer != null) {
				MemoryUtil.memFree(posBuffer);
			}
			
			if(colorBuffer != null) {
				MemoryUtil.memFree(colorBuffer);
			}
			
			if (indicesBuffer != null) {
				MemoryUtil.memFree(indicesBuffer);
			}		
		}
	}

	public int getVaoId() {
		
		return vaoId;
	}
	
	public int getVertexCount() {
		
		return vertexCount;
	}
	
	public void dispose() {
		
		glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(colorVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
	}
}
