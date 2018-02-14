package com.degauvendesign.interstella;

import org.joml.Vector3f;
import com.degauvendesign.interstella.graph.Mesh;

public class Entity {
	
	private final Mesh mesh;
	private final Vector3f position;
	private float scale;
	private final Vector3f rotation;
	
	public Entity(Mesh mesh) {
		
		this.mesh = mesh;
		position = new Vector3f(0, 0, 0);
		scale = 1;
		rotation = new Vector3f(0, 0, 0);
	}
	
	public Vector3f getPosition() {
		
		return position;
	}
	
	public void setPosition(float x, float y, float z) {
		
		this.position.set(x, y, z);
	}
	
	public float getScale() {
		
		return scale;
	}
	
	public void setScale(float scale) {
		
		this.scale = scale;
	}
	
	public Vector3f getRotation() {
		
		return rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		
		this.rotation.set(x, y, z);
	}
	
	public Mesh getMesh() {
		
		return mesh;
	}
}
