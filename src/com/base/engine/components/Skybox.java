package com.base.engine.components;


import com.base.engine.core.Transform;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.skybox.SkyboxMesh;
import com.base.engine.rendering.skybox.SkyboxShader;

public class Skybox{
	
	private static final float SIZE = 500f;
	
	private static final float[] VERTICES = {        
	    -SIZE,  SIZE, -SIZE,
	    -SIZE, -SIZE, -SIZE,
	    SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	    -SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE
	};
	
	private Transform transform;
	private SkyboxMesh mesh;
	private SkyboxShader shader;
	
	public Skybox(String[] files){
		this.mesh = new SkyboxMesh(VERTICES,files);
		transform = new Transform();
		shader = new SkyboxShader();
	}
	
	public Skybox(){
		this.mesh = new SkyboxMesh(VERTICES);
		transform = new Transform();
		shader = new SkyboxShader();
	}
	
//	private RawModel cube;
//	private int texture;
//	private int nightTexture;
//	private SkyboxShader shader;
//	
//	public SkyboxRenderer(Loader loader, Matrix4f projectionMatrix){
//		cube = loader.loadToVAO(VERTICES, 3);
//		texture = loader.loadCubeMap(TEXTURE_FILES);
//		nightTexture = loader.loadCubeMap(NIGHT_TEXTURE_FILES);
//		shader = new SkyboxShader();
//		shader.start();
//		shader.connectTexrureUnits();
//		shader.loadProjectionMatrix(projectionMatrix);
//		shader.stop();
//		
//	}
//
	public void render(RenderingEngine renderingEngine){
		shader.bind();
		shader.updateUniforms(transform, mesh.getTexture(), renderingEngine);
		mesh.draw();
		shader.stop();
	}

}
