package com.base.engine.rendering;

import com.base.engine.core.Input;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Vector3f;

public class Camera {
	public static final Vector3f yaxis = new Vector3f(0, 1, 0);
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	private Matrix4f projection;
	
	public Camera(float fov, float aspectRatio, float zNear, float zFar) {
		this.pos = new Vector3f(0, 0, 0);
		this.forward = new Vector3f(0, 0, 1).normalized();
		this.up = new Vector3f(0, 1, 0).normalized();
		this.projection = new Matrix4f().initPerspective(fov, aspectRatio, zNear, zFar);
	}
	
	public Matrix4f getViewProjection(){
		Matrix4f cameraRotation = new Matrix4f().initRotation(forward, up);
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(-pos.getX(), -pos.getY(), -pos.getZ());
		
		return projection.mul(cameraRotation.mul(cameraTranslation));
	}
	
	public void move(Vector3f dir, float amt){
		pos = pos.add(dir.mul(amt));
	}
	
	public void rotateY(float angle){
		Vector3f Haxis = yaxis.cross(forward).normalized();
		
		forward = forward.rotate(angle, yaxis).normalized();
		
		up = forward.cross(Haxis).normalized();
	}
	
	public void rotateX(float angle){
		Vector3f Haxis = yaxis.cross(forward).normalized();
		
		forward = forward.rotate(angle, Haxis).normalized();
		
		up = forward.cross(Haxis).normalized();
	}
	
	public Vector3f getLeft(){
		return forward.cross(up).normalized();
	}
	
	public Vector3f getRight(){
		return up.cross(forward).normalized();
	}

	public Vector3f getPos() {
		return pos;
	}


	public void setPos(Vector3f pos) {
		this.pos = pos;
	}


	public Vector3f getForward() {
		return forward;
	}


	public void setForward(Vector3f forward) {
		this.forward = forward;
	}


	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}
	
	public void input(float delta){
		float movAmt = (float)(10 * delta);
		float rotAmt = (float)(100 * delta);
		
		if(Input.getKey(Input.KEY_W))
			move(getForward(), movAmt);
		if(Input.getKey(Input.KEY_S))
			move(getForward(), -movAmt);
		if(Input.getKey(Input.KEY_A))
			move(getLeft(), movAmt);
		if(Input.getKey(Input.KEY_D))
			move(getRight(), movAmt);
		
		if(Input.getKey(Input.KEY_UP))
			rotateX(-rotAmt);
		if(Input.getKey(Input.KEY_DOWN))
			rotateX(rotAmt);
		if(Input.getKey(Input.KEY_LEFT))
			rotateY(rotAmt);
		if(Input.getKey(Input.KEY_RIGHT))
			rotateY(-rotAmt);
		
	}
	
}
