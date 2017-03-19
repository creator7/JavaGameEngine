package com.base.game;

import com.base.engine.components.Camera;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.FreeLook;
import com.base.engine.components.FreeMove;
import com.base.engine.components.LookAtComponent;
import com.base.engine.components.Meshrenderer;
import com.base.engine.components.Skybox;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;
import com.base.engine.rendering.Window;

public class MonkeyPicker extends Game{
	
	private Skybox skybox;
	private GameObject camera;
	    
		public void init()
		{
			
			String[] FILES = {"vr_rt.png", "vr_lf.png", "vr_up.png", "vr_dn.png", "vr_bk.png","vr_ft.png"};
			
	    	skybox = new Skybox(FILES);
	    	
			Material material = new Material();
			material.addTexture("diffuse", new Texture("test.png"));
			material.addFloat("specularIntensity", 1);
			material.addFloat("specularPower", 8);
			
			Material material2 = new Material();
			material2.addTexture("diffuse", new Texture("Green.png"));
			material2.addFloat("specularIntensity", 1);
			material2.addFloat("specularPower", 8);
			
			Material red = new Material();
			red.addTexture("diffuse", new Texture("red.png"));
			red.addFloat("specularIntensity", 1);
			red.addFloat("specularPower", 8);
			
			Material blue = new Material();
			blue.addTexture("diffuse", new Texture("blue.png"));
			blue.addFloat("specularIntensity", 1);
			blue.addFloat("specularPower", 8);
			
			Material pink = new Material();
			pink.addTexture("diffuse", new Texture("pink.png"));
			pink.addFloat("specularIntensity", 1);
			pink.addFloat("specularPower", 8);
			
			Material material3 = new Material();
			material3.addTexture("diffuse", new Texture("blank.png"));
			material3.addFloat("specularIntensity", 1);
			material3.addFloat("specularPower", 8);
			

			Mesh tempMesh = new Mesh("monkey.obj");
			
			GameObject environment = new GameObject().addComponent(new Meshrenderer(new Mesh("environment.obj"), pink));


			GameObject directionalLightObject = new GameObject();
			DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0.5f,0.5f,0.5f), 0.2f);
			directionalLightObject.addComponent(directionalLight);
			directionalLightObject.setPos(new Vector3f(1000, 1000, -7000));
			
			addObject(directionalLightObject);

			GameObject monkey = new GameObject().addComponent(new Meshrenderer(tempMesh, material));
			
			
			monkey.getTransform().getPos().set(5, 5, 5);
			
			
			Camera cam = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
			camera = new GameObject().addComponent(cam).addComponent(new FreeLook(0.5f));
			camera.addComponent(new FreeMove(10.0f));
			camera.setPos(new Vector3f(-1.24896f, 49.29807f, 8.450344f));
			addObject(camera);
			monkey.addComponent(new LookAtComponent(camera));
			
			addObject(monkey);
			addObject(environment);
		}
		
		@Override
		public void update(float delta){
			super.update(delta);
		}
		
	    @Override
	    public void render(RenderingEngine renderingEngine){
	    	super.render(renderingEngine);
	    	skybox.render(renderingEngine);
	    	
	    }

}
