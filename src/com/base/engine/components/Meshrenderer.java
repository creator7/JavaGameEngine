package com.base.engine.components;

import com.base.engine.core.Transform;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Shader;

public class Meshrenderer extends GameComponent{
	
	private Mesh mesh;
	private Material material;
	
	
	public Meshrenderer(Mesh mesh, Material material) {
		this.mesh = mesh;
		this.material = material;
	}
	
	public void render(Transform transform, Shader shader){
		
		shader.bind();
		shader.updateUniforms(transform, material);
		mesh.Draw();
	}
	
	
}
