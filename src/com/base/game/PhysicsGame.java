package com.base.game;

import com.base.engine.components.Camera;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.FreeLook;
import com.base.engine.components.FreeMove;
import com.base.engine.components.Meshrenderer;
import com.base.engine.components.PhysicsEngineComponent;
import com.base.engine.components.PhysicsObjectComponent;
import com.base.engine.components.Skybox;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector3f;
import com.base.engine.physics.BoundingSphere;
import com.base.engine.physics.PhysicsEngine;
import com.base.engine.physics.PhysicsObject;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Window;

public class PhysicsGame extends Game{
	
    private Skybox skybox;
    private Vector3f ambientLight;
	
    public void init(){
    	
		String[] FILES = {"morning_rt.png", "morning_lf.png", "morning_up.png", "morning_dn.png", "morning_bk.png","morning_ft.png"};
		
    	skybox = new Skybox(FILES);
	
		Material material = new Material();
		material.addTexture("diffuse", new Texture("test.png"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);
		
		Material grass = new Material();
		grass.addTexture("diffuse", new Texture("grass.png"));
		grass.addFloat("specularIntensity", 1);
		grass.addFloat("specularPower", 8);
		grass.addFloat("tiling", 40.0f);
		
		Material material2 = new Material();
		material2.addTexture("diffuse", new Texture("bricks.jpg"));
		material2.addFloat("specularIntensity", 1);
		material2.addFloat("specularPower", 8);
	    	
    	Mesh cubeMesh = new Mesh("cube.obj");
    	GameObject cube = new GameObject().addComponent(new Meshrenderer(cubeMesh, material));
    	GameObject plane = new GameObject().addComponent(new Meshrenderer(new Mesh("plane.obj"), grass));
    	Mesh sphereMesh = new Mesh("sphere.obj");
    	
    	Camera cam = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
		GameObject camera = new GameObject().addComponent(cam);
		camera.addComponent(new FreeLook(0.5f));
		camera.addComponent(new FreeMove(12.0f));
		camera.setPos(new Vector3f(0, 2, -5));
		
		plane.setPos(new Vector3f(0, -1, 0));
		plane.getTransform().setScale(new Vector3f(500, 0, 500));
		
		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0.8f,0.8f,0.8f), 1.5f);
		directionalLightObject.addComponent(directionalLight);
		directionalLight.getTransform().setPos(new Vector3f(0,10000,-1000));

    	
		addObject(camera);
		addObject(directionalLightObject);
    	addObject(cube);
    	addObject(plane);
    	
    	PhysicsEngine physicsEngine = new PhysicsEngine();
    	physicsEngine.addObject(new PhysicsObject(new BoundingSphere(new Vector3f(0, 0, 40), 1.0f), new Vector3f(0.0f, 0.0f, 0.0f)));
//    	physicsEngine.addObject(new PhysicsObject(new Plane(new Vector3f(0, 1f, 0f), 0.0f), new Vector3f(0, -10f, 0)));
    	physicsEngine.addObject(new PhysicsObject(new BoundingSphere(new Vector3f(0, 0, 25), 1.0f), new Vector3f(0.0f, 0.0f, 15)));
    	physicsEngine.addObject(new PhysicsObject(new BoundingSphere(new Vector3f(0, 0, 10), 1.0f), new Vector3f(0.0f, 0.0f, 15)));
    	physicsEngine.addObject(new PhysicsObject(new BoundingSphere(new Vector3f(0, 0, 0), 1.0f), new Vector3f(0.0f, 0.0f, 0)));
    	
    	PhysicsEngineComponent physicsEngineComponent = new PhysicsEngineComponent(physicsEngine);
    	
    	for(int i = 0; i < physicsEngineComponent.getPhysicsEngine().getNumObjects(); i++){
//    		if(i==2){
//    			addObject(new GameObject().addComponent(new PhysicsObjectComponent(physicsEngineComponent.getPhysicsEngine().getObject(i))).addComponent(new Meshrenderer(new Mesh("plane.obj"), material)));
//    		}
//    		else
    			addObject(new GameObject().addComponent(new PhysicsObjectComponent(physicsEngineComponent.getPhysicsEngine().getObject(i))).addComponent(new Meshrenderer(sphereMesh, material2)));
    	}
    	
    	addObject(new GameObject().addComponent(physicsEngineComponent));
    	
    }
    
    
    @Override
    public void render(RenderingEngine renderingEngine){
    	if(ambientLight == null){
    		ambientLight = new Vector3f(0.5f, 0.5f, 0.5f);
    		renderingEngine.setAmbientlight(ambientLight);
    	}
    	super.render(renderingEngine);
    	skybox.render(renderingEngine);
    	
    }

}
