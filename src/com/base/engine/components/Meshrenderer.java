package com.base.engine.components;

import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class Meshrenderer extends GameComponent{
	
	private Mesh mesh;
	private Material material;
	
	
	public Meshrenderer(Mesh mesh, Material material) {
		this.mesh = mesh;
		this.material = material;
	}

	@Override
	public void render(Shader shader, RenderingEngine renderingEngine){	
		shader.bind();
		renderingEngine.setTerrain(0);
		shader.updateUniforms(getTransform(),material, renderingEngine);
		mesh.draw();
		shader.stop();
	}
	
	
}
