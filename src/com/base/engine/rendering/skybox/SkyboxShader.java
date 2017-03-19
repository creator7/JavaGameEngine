package com.base.engine.rendering.skybox;

import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class SkyboxShader extends Shader{

	public SkyboxShader() {
		super("skybox");
	}
	
	@Override
	public void updateUniforms(Transform transform, SkyboxTexture texture, RenderingEngine renderingEngine){
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f MVPMatrix = renderingEngine.getMainCamera().getSkyboxViewProjection();
		
		for(int i = 0; i < getResource().getUniformNames().size(); i++ ){
			String uniformName = getResource().getUniformNames().get(i);
			String uniformType = getResource().getUniformTypes().get(i);
			
			if(uniformType.equals("sampler2D")){
				texture.bind();
				setUniformi(uniformName, texture.getResource().getid());
			}
			
			else if(uniformName.startsWith("T_")){
				if(uniformName.equals("T_MVP"))
					setUniform(uniformName, MVPMatrix);
				else if(uniformName.equals("T_model"))
					setUniform(uniformName, worldMatrix);
				else
					throw new IllegalArgumentException(uniformName + " is not a valid component of Transform");
			}
		}
	}

}
