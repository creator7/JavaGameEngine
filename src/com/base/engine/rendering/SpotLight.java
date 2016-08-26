package com.base.engine.rendering;

import com.base.engine.core.Vector3f;

public class SpotLight {
	
	private PointLight pointLight;
	private Vector3f direction;
	private float cuttoff;
	
	public SpotLight(PointLight pointLight, Vector3f direction, float cuttoff) {
		this.pointLight = pointLight;
		this.direction = direction.normalized();
		this.cuttoff = cuttoff;
	}
	
	public PointLight getPointLight() {
		return pointLight;
	}
	public void setPointLight(PointLight pointLight) {
		this.pointLight = pointLight;
	}
	public Vector3f getDirection() {
		return direction;
	}
	public void setDirection(Vector3f direction) {
		this.direction = direction.normalized();
	}
	public float getCuttoff() {
		return cuttoff;
	}
	public void setCuttoff(float cuttoff) {
		this.cuttoff = cuttoff;
	}
	
	

}
