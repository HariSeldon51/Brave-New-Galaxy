package com.degauvendesign.interstella.graph;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;


public class Texture {
	
	private final int id;
	private int width;
	private int height;
	
	public Texture(int width, int height, ByteBuffer texture) {
		
		this.id = glGenTextures();
		this.width = width;
		this.height = height;
		
		glBindTexture(GL_TEXTURE_2D, id);
		
		// Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
	    glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
	    
	    // Upload the texture data
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, texture);
        // Generate Mipmap
        glGenerateMipmap(GL_TEXTURE_2D);
		
	}
	
	public void bind() {
		
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public int getId() {
		
		return id;
	}
	
	public static Texture loadTexture(String fileName) throws Exception {
		
		ByteBuffer textureBuffer;
		int width, height;
		
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);
			
			// Load the texture.
			textureBuffer = stbi_load(fileName, w, h, comp, 4);
			
			if (textureBuffer == null) {
                throw new RuntimeException("Failed to load a texture file!"
                                           + System.lineSeparator() + stbi_failure_reason());
            }
			
			width = w.get();
			height = h.get();
			
			Texture texture = new Texture(width, height, textureBuffer);
			return texture;
		}
	}
	
	public void dispose() {
		
		glDeleteTextures(id);
	}

}
