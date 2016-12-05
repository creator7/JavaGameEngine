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
		
		String vertexShaderText = loadShader("forward-ambient.vs");
		String fragmentShaderText = loadShader("forward-ambient.fs");
		
		
		addVertexShader(vertexShaderText);
		addFragmentShader(fragmentShaderText);
		
		
		compileShader();
		
		addAllUniforms(vertexShaderText);
		addAllUniforms(fragmentShaderText);
//		
//		addUniform("MVP");
//		addUniform("ambientIntensity");
	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
		
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().mul(worldMatrix);
		
		material.getTexture("diffuse").bind();
		
		setUniform("MVP", projectedMatrix);
		setUniform("ambientIntensity", renderingEngine.getAmbientLight());
	}

}
