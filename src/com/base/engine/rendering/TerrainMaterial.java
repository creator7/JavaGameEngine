package com.base.engine.rendering;

public class TerrainMaterial {
	
	private Material material;
	
	public TerrainMaterial(String diffuse, String red, String green, String blue, String blendMap){
		this.material = new Material();
		material.addTexture("diffuse", new Texture(diffuse));
		material.addTexture("rTex", new Texture(red));
		material.addTexture("gTex", new Texture(green));
		material.addTexture("bTex", new Texture(blue));
		material.addTexture("blendMap", new Texture(blendMap));
	}
	
	public TerrainMaterial(String diffuse){
		this.material = new Material();
		material.addTexture("diffuse", new Texture(diffuse));
	}

	public Material getMaterial() {
		return material;
	}

}
