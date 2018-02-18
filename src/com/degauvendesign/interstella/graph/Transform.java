package com.degauvendesign.interstella.graph;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import com.degauvendesign.interstella.Entity;

public class Transform {
	
	private final Matrix4f projectionMatrix;
	private final Matrix4f modelViewMatrix;
	private final Matrix4f viewMatrix;
	
	public Transform() {
		
		projectionMatrix = new Matrix4f();
		modelViewMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
		
	}
	
	public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
		
		float aspectRatio = width / height;
		projectionMatrix.setPerspective(fov, aspectRatio, zNear, zFar); // setPerspective() calls identity() before calling perspective().
		
		return projectionMatrix;
	}
	
	public Matrix4f getViewMatrix(Camera camera) {
		
		Vector3f cameraPos = camera.getPosition();
		Vector3f rotation = camera.getRotation();
		
		// First, rotate camera
		viewMatrix.rotation((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
				  .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
		
		// Than, translate camera
		viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
				
		return viewMatrix;
	}
	
	public Matrix4f getModelViewMatrix(Entity entity, Matrix4f viewMatrix) {
		
		Vector3f rotation = entity.getRotation();
		modelViewMatrix.translation(entity.getPosition()).
						rotateX((float)Math.toRadians(-rotation.x)).
						rotateY((float)Math.toRadians(-rotation.y)).
						rotateZ((float)Math.toRadians(-rotation.z)).
						scale(entity.getScale());
		Matrix4f viewCurr = new Matrix4f(viewMatrix);
		return viewCurr.mul(modelViewMatrix);		
	}
}
