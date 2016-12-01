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
		
		glClearColor(0.0f , 0.0f, 0.0f, 1.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		glEnable(GL_TEXTURE_2D);
		
		//mainCamera = new Camera((float)Math.toRadians(70.0f), (float)(Window.getWidth()/Window.getHeight()), 0.1f, 1000f);
		ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
		
//		int lightFieldWidth = 5;
//		int lightFieldDepth = 5;
//		
//		float lightFieldStartX = 0;
//		float lightFieldStartY = 0;
//		float lightFieldStepX = 7;
//		float lightFieldStepY = 7;
//		
//		pointLightList = new PointLight[lightFieldWidth * lightFieldDepth];
//		
//		for(int i = 0; i < lightFieldWidth; i++)
//		{
//			for(int j = 0; j < lightFieldDepth; j++)
//			{
//				pointLightList[i * lightFieldWidth + j] = new PointLight(new BaseLight(new Vector3f(0,1,0), 0.5f),
//				new Attenuation(0,0,1),
//				new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j), 100);
//			}
//		}
//		
//		activePointLight = pointLightList[0];
//		
//		spotLight = new SpotLight(new PointLight(new BaseLight(new Vector3f(0,1,1), 0.4f),new Attenuation(0,0,0.1f),
//				new Vector3f(lightFieldStartX,0,lightFieldStartY), 100), new Vector3f(1,0,0), 0.7f);
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
		//glDepthMask(false);
		glDisable(GL_DEPTH_TEST);
		glDepthFunc(GL_EQUAL);
	

		for(BaseLight light: lights){
			
			activelight = light;
			
			object.render(light.getShader(), this);
		}

		glDepthFunc(GL_LESS);
		glEnable(GL_DEPTH_TEST);
		glDisable(GL_BLEND);
	}
	
	private static void clearScreen()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
//	private void setTextures(boolean enabled){
//		if(enabled)
//			glEnable(GL_TEXTURE_2D);
//		else
//			glDisable(GL_TEXTURE_2D);
//	}
	
//	private static void setClearColor(Vector3f color){
//		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
//	}
	
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
