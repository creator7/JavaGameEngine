package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Shader;

public class SpotLight extends PointLight {
	private float cuttoff;
	
	public SpotLight(Vector3f color, float intensity, Attenuation attenuation, float cuttoff) {
		
		super(color,intensity,attenuation);
		this.cuttoff = cuttoff;
		
		setShader(new Shader("forward-spot"));
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
