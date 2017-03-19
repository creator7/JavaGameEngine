package com.base.engine.components;

import com.base.engine.core.CoreEngine;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Vector3f;

public class Camera extends GameComponent
{

	private Matrix4f projection;

	public Camera(float fov, float aspect, float zNear, float zFar)
	{
		this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
	}
	
	public Camera(float left, float right, float bottom, float top, float near, float far){
		this.projection = new Matrix4f().initOrthographic(left, right, bottom, top, near, far);
	}

	public Matrix4f getViewProjection()
	{
		Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
		Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
		
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.x, cameraPos.y, cameraPos.z);

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}
	
	public Matrix4f getSkyboxViewProjection()
	{
		Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
		
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(0, 0, 0);

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}
	
	@Override
	public void addToEngine(CoreEngine engine)
	{
		engine.getRenderingEngine().addCamera(this);
	}
}