package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardSpot;

public class SpotLight extends PointLight {
	private float cuttoff;
	
	public SpotLight(Vector3f color, float intensity, Vector3f attenuation, float cuttoff) {
		
		super(color,intensity,attenuation);
		this.cuttoff = cuttoff;
		
		setShader(ForwardSpot.getInstance());
	}
	
	public Vector3f getDirection() {
		return getTransform().getTransformedRot().getForward();
	}
	
	public float getCuttoff() {
		return cuttoff;
	}
	public void setCuttoff(float cuttoff) {
		this.cuttoff = cuttoff;
	}
	
	

}
