package com.base.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import java.util.ArrayList;

import com.base.engine.components.BaseLight;
import com.base.engine.components.Camera;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector3f;

public class RenderingEngine {

	private Camera mainCamera;
	private Vector3f ambientLight;

	
	private ArrayList<BaseLight> lights;
	private BaseLight activelight;
	
	public RenderingEngine() {
		
		lights = new ArrayList<BaseLight>();
		
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		glEnable(GL_TEXTURE_2D);
		

		ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
	}
	
	public Vector3f getAmbientLight() {
		return ambientLight;
	}
	

	public void render(GameObject object){
		
		clearScreen();
		lights.clear();
		
		object.addToRenderingEngine(this);
		
		
		Shader forwardAmbient = ForwardAmbient.getInstance();
		
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
	
	private static void clearScreen()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void unbindTextures(){
		glBindTexture(GL_TEXTURE_2D, 0);
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
	
	
}
