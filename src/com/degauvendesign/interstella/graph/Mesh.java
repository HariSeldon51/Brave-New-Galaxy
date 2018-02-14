package com.degauvendesign.interstella.graph;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.system.MemoryUtil;

public class Mesh {
	
	private final int vaoId;
	private final List<Integer> vboIdList;
	private final int vertexCount;
	
	private final Texture texture;
	
	public Mesh( float[] positions, float[] textCoords, int[] indices, Texture texture ) {
		
		FloatBuffer posBuffer = null;
		FloatBuffer textCoordsBuffer = null;
		IntBuffer indicesBuffer = null;
		
		try {
			
			// Initialize resources
			this.texture = texture;
			vertexCount = indices.length;
			vboIdList = new ArrayList();
			
			// Create the VAO and bind to it.
	        vaoId = glGenVertexArrays();
	        glBindVertexArray(vaoId);
			
	        // Create the vertex coordinate VBO and bind to it.
            int vboId = glGenBuffers();
            vboIdList.add(vboId);
            posBuffer = MemoryUtil.memAllocFloat(positions.length);
			posBuffer.put(positions).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            // Define structure of the data for shaders to access.
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            
            // Create the vertex color VBO and bind to it.
            vboId = glGenBuffers();
            vboIdList.add(vboId);
            textCoordsBuffer = MemoryUtil.memAllocFloat(textCoords.length);
            textCoordsBuffer.put(textCoords).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
            // Define structure of the data for shaders to access.
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
            
	        // Create the vertex index VBO and bind to it.
            vboId = glGenBuffers();
            vboIdList.add(vboId);
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            // Unbind the vertex coordinate VBO
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            // Unbind the VAO
            glBindVertexArray(0);
			
		} finally {
			
			if (posBuffer != null) {
				MemoryUtil.memFree(posBuffer);
			}
			
			if(textCoordsBuffer != null) {
				MemoryUtil.memFree(textCoordsBuffer);
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
	
	public void render() {
		 
		// Activate texture
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texture.getId());
		 
		// Draw the mesh
		glBindVertexArray(getVaoId());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
		
		// Restore state
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
	public void dispose() {
		
		glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        for (int vboId : vboIdList) {
        	glDeleteBuffers(vboId);
        }

        // Delete texture
        texture.dispose();

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
	}
}
