package com.degauvendesign.interstella;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {
	
	public static String loadResource(String fileName) throws Exception {
        String result;    
        try {
        	
        	InputStream in = Utils.class.getResourceAsStream("resources/" + fileName);
        	Scanner scanner = new Scanner(in, "UTF-8");
        	result = scanner.useDelimiter("\\A").next();
        	scanner.close();
        	
        } catch (NullPointerException e) {
        	
        	System.err.println("Could not get resource.  " + e.getMessage());
        	
        	if (fileName == "/vertex.vs") {
            	result = "#version 330\n\nlayout (location=0) in vec3 position;\n\nvoid main()\n{\n  gl_Position = vec4(position, 1.0);\n}\n";
        	} else if (fileName == "/fragment.fs") {
            	result = "#version 330\n\nout vec4 fragColor;\n\nvoid main()\n{\n  fragColor = vec4(0.0, 0.5, 0.5, 1.0);\n}\n";
        	} else {
        		result = null;
        	}
        	
        	return result;
        } 
        return result;
    }
}
