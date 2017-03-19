package com.base.engine.rendering.guis;

import java.util.List;

import org.lwjgl.opengl.GL11;

public class GUIRenderer {
	
	private final float[] VERTICES = {-1,1,1,1,-1,-1,1,-1};
	
	private GUIShader shader;
	private GUIMesh mesh;
	
	public GUIRenderer(){
		shader = new GUIShader();
		mesh = new GUIMesh(VERTICES);
	}
	
	public void render(List<GUI> guis){
		shader.bind();
		mesh.enableVertexArray();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		for(GUI gui: guis){
			shader.updateUniforms(gui);
			mesh.draw();
		}
		
		GL11.glDisable(GL11.GL_BLEND);
		mesh.disableVertexArray();
		shader.stop();
	}

}
