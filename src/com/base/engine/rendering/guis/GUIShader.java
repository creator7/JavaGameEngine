package com.base.engine.rendering.guis;

import com.base.engine.core.Matrix4f;
import com.base.engine.rendering.Shader;

public class GUIShader extends Shader{

	public GUIShader() {
		super("gui");
	}
	
	@Override
	public void updateUniforms(GUI gui){
		
		Matrix4f worldMatrix = Matrix4f.getTransformation(gui);
		
		for(int i = 0; i < getResource().getUniformNames().size(); i++ ){
			String uniformName = getResource().getUniformNames().get(i);
			String uniformType = getResource().getUniformTypes().get(i);
			
			if(uniformType.equals("sampler2D")){
				gui.getTexture().bind(gui.getTexture().getID());
				setUniformi(uniformName, gui.getTexture().getID());
			}
			else if(uniformName.equals("T_model")){
					setUniform(uniformName, worldMatrix);
			}
			else
				throw new IllegalArgumentException(uniformName + " is not a valid component of Transform");
		}
	}
	

}
