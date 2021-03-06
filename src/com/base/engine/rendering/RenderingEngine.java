package com.base.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import java.util.ArrayList;
import java.util.HashMap;

import com.base.engine.components.BaseLight;
import com.base.engine.components.Camera;
import com.base.engine.core.GameObject;
import com.base.engine.core.Transform;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.resourceManagement.MappedValues;

public class RenderingEngine extends MappedValues{

	private Camera mainCamera;

	
	private ArrayList<BaseLight> lights;
	private BaseLight activelight;
	private HashMap<String, Integer> samplerMap;
	private Shader forwardAmbient;
	private Vector3f ambientlight;
	private int terrain;
	
	public RenderingEngine() {
		super();
		lights = new ArrayList<BaseLight>();
		
		samplerMap = new HashMap<>();
		samplerMap.put("diffuse", 0);
		samplerMap.put("rTex", 1);
		samplerMap.put("gTex", 2);
		samplerMap.put("bTex", 3);
		samplerMap.put("blendMap", 4);
		ambientlight = new Vector3f(0.1f, 0.1f, 0.1f);
		
		forwardAmbient = new Shader("forward-ambient");
		//addVector3f("ambient", new Vector3f(0.3f, 0.3f, 0.3f));
		
		glClearColor( 0.5f, 0.8f, 0.95f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		glEnable(GL_TEXTURE_2D);
	}

	public void render(GameObject object){
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		addVector3f("ambient", ambientlight);
		object.render(forwardAmbient, this);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		
		for(BaseLight light: lights){
			activelight = light;
			object.render(light.getShader(), this);
		}
		
		
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}
	
	public void updateUniformStruct(Transform transform, Material material, Shader shader, String uniformName, String uniformType){
		throw new IllegalArgumentException(uniformType + " is not a supported type of in Rendering Engine");
	}
	
	public Camera getMainCamera() {
		return mainCamera;
	}
	
	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}
	
	public BaseLight getActiveLight(){
		return activelight;
	}
	
	public void addLight(BaseLight light){
		lights.add(light);
	}
	
	public void addCamera(Camera camera){
		mainCamera = camera;
	}
	
	public int getSamplerSlot(String samplerName){
		return samplerMap.get(samplerName);
	}
	
	public int isTerrain(){
		return terrain;
	}
	
	public void setTerrain(int terrain){
		this.terrain = terrain;
	}

	public Vector3f getAmbientlight() {
		return ambientlight;
	}

	public void setAmbientlight(Vector3f ambientlight) {
		this.ambientlight = ambientlight;
	}
	
	
}
