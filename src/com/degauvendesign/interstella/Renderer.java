package com.degauvendesign.interstella;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class Renderer {
	
	
	// TODO: Not sure if this class is needed -- remove if not used.
	public Renderer() {     
		
    }
    
    public void init() {     
    	
    }

    public void clear() {
    	
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
