package com.base.engine.rendering;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardAmbient extends Shader {
	
private static final ForwardAmbient instance = new ForwardAmbient();
	
	public static ForwardAmbient getInstance(){
		return instance;
	}
	
	public ForwardAmbient() {
		super();
		
		addVertexShaderFromFile("forward-ambient.vs");
		addFragmentShaderFromFile("forward-ambient.fs");
		
		compileShader();
		addUniform("MVP");
		addUniform("ambientIntensity");
	}
	
	public void updateUniforms(Transform transform, Material material){
		
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
		
		material.getTexture().bind();
		
		setUniform("MVP", projectedMatrix);
		setUniform("ambientIntensity", getRenderingEngine().getAmbientLight());
	}

}
