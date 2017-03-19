package com.base.engine.components;

import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;
import com.base.engine.rendering.TerrainMaterial;
import com.base.engine.rendering.terrain.Terrain;

public class TerrainRenderer extends GameComponent{
	
	private Mesh mesh;
	private Material material;
	
	public TerrainRenderer(Terrain terrain, TerrainMaterial material) {	
		this.mesh = new Mesh(terrain.getVertices(), terrain.getIndices(),true);
		this.material = material.getMaterial();
	}

	@Override
	public void render(Shader shader, RenderingEngine renderingEngine){
		shader.bind();
		renderingEngine.setTerrain(1);
		shader.updateUniforms(getTransform(),material, renderingEngine);
		mesh.draw();
		shader.stop();
	}
	
}
