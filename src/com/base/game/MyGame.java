package com.base.game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.base.engine.components.Camera;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.FreeLook;
import com.base.engine.components.FreeMove;
import com.base.engine.components.LookAtComponent;
import com.base.engine.components.Meshrenderer;
import com.base.engine.components.PointLight;
import com.base.engine.components.Skybox;
import com.base.engine.components.SpotLight;
import com.base.engine.components.TerrainRenderer;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Input;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.TerrainMaterial;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Window;
import com.base.engine.rendering.guis.GUI;
import com.base.engine.rendering.guis.GUIRenderer;
import com.base.engine.rendering.terrain.Terrain;

public class MyGame extends Game{
    
	private GameObject lamp;
	private GameObject spotLight;
	private GameObject camera;
	private GameObject Kid;
	private Terrain myTerrain;
    private Skybox skybox;
    private List<GUI> guis;
    private GUIRenderer guiRenderer;
    private Texture tex;
	
    public MyGame(){
    	guis = new ArrayList<>();
    }
    
    public void init(){
    	
    	guiRenderer = new GUIRenderer();
    	
    	myTerrain = new Terrain("heightMap");
    	String[] FILES = {"morning_rt.png", "morning_lf.png", "morning_up.png", "morning_dn.png", "morning_bk.png","morning_ft.png"};
    	
    	skybox = new Skybox(FILES);
    	
    	guis.add(new GUI(new Texture("health.png"), new Vector2f(0f,-0.8f), new Vector2f(0.30f,0.30f)));
    	guis.add(new GUI(new Texture("signature.png"), new Vector2f(0.8f,0.8f), new Vector2f(0.25f,0.25f)));
        
    	tex = new Texture("health2.png");
    	
		Material material = new Material();
		material.addTexture("diffuse", new Texture("grass.png"));
		
		Material material2 = new Material();
		material2.addTexture("diffuse", new Texture("test.png"));
		material2.addFloat("specularIntensity", 1);
		material2.addFloat("specularPower", 8);
		
		GameObject cube = new GameObject().addComponent(new Meshrenderer(new Mesh("cube.obj"), material2)) ;
		
		Material material4 = new Material();
		material4.addTexture("diffuse", new Texture("lamp.png"));
		
        
		TerrainMaterial tmaterial = new TerrainMaterial("grass.png","mud.png","grassFlowers.png","path.png","blendMap.png");
		
		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0.5f,0.5f,0.5f), 4f);
		directionalLightObject.addComponent(directionalLight);
		directionalLight.getTransform().setPos(new Vector3f(0,10000,-7000));
		
		Mesh tempMesh = new Mesh("monkey.obj");
		GameObject monkey = new GameObject().addComponent(new Meshrenderer(tempMesh, material2));
		
		GameObject terrain = new GameObject();
		TerrainRenderer terrainRenderer = new TerrainRenderer(myTerrain, tmaterial);
		terrain.addComponent(terrainRenderer);
		
		Camera cam = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
		camera = new GameObject().addComponent(cam);
		camera.addComponent(new FreeMove(20.0f));
		camera.getTransform().setPos(new Vector3f(100, 2, 50));
		
		monkey.getTransform().setPos(new Vector3f(100, 0, 60));
		monkey.addComponent(new LookAtComponent(camera));
		
		Mesh lampMesh = new Mesh("lamp.obj");
		lamp = new GameObject().addComponent(new Meshrenderer(lampMesh, material4));
		
		lamp.setPos(new Vector3f(120, -3f, 60));
		lamp.getTransform().setScale(new Vector3f(1, 0.8f, 1));
		
		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.8f, new Attenuation(0,0.1f,0)));
		pointLightObject.getTransform().setPos(new Vector3f(120, 5f, 60f));
		
		spotLight = new GameObject().addComponent(new SpotLight(new Vector3f(0,0,1), 0.8f, new Attenuation(0,0f,0.1f), 0.7f));
		spotLight.setPos(new Vector3f(110, 3f, 60f));
		
		Kid = new GameObject();
		Kid.addComponent(new Meshrenderer(new Mesh("SP_Kid.obj"), material4));
		Kid.getTransform().setPos(new Vector3f(110, -5f, 65f));
		Kid.getTransform().setScale(new Vector3f(0.3f, 0.3f, 0.3f));
		camera.addComponent(new FreeMove(20.0f, Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D));
		//camera.addComponent(new LookAtComponent(Kid));
		camera.addComponent(new FreeLook(0.5f));
		Kid.addComponent(new FreeMove(20.0f, Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT));
		
		
		Vector3f fernScale = new Vector3f(0.8f, 0.8f, 0.8f);
		Random random = new Random(676452);
        for(int i=0;i<100;i++){
        	float x = random.nextFloat()*800;
        	float z = random.nextFloat() * 600;
        	float y = myTerrain.getHeightOfTerrain(x, z);
        	GameObject pine = new GameObject().addComponent(new Meshrenderer(new Mesh("pine.obj"), new Material("diffuse", new Texture("pine.png"))));
            addObject(pine);;
            pine.setPos(new Vector3f(x, y, z));
            pine.getTransform().setScale(new Vector3f(2.5f, 2.5f, 2.5f));
        }
        
        for(int i=0;i<300;i++){
        	float x = random.nextFloat()*800;
        	float z = random.nextFloat() * 600;
        	float y = myTerrain.getHeightOfTerrain(x, z);
        	GameObject fern = new GameObject().addComponent(new Meshrenderer(new Mesh("fern.obj"), new Material("diffuse", new Texture("fern.png"))));
            addObject(fern);
            fern.setPos(new Vector3f(x+ random.nextFloat()*10+2, y, z+random.nextFloat()*8+7));
            fern.getTransform().setScale(fernScale);
        }
		
		
		addObject(lamp);
		addObject(cube);
		addObject(monkey);
		addObject(directionalLightObject);
		addObject(pointLightObject);
		addObject(terrain);
		addObject(camera);
		addObject(Kid);
		
    }
    
    @Override
    public void update(float delta){
    	super.update(delta);
//    	if(Input.getKey(Input.KEY_SPACE))
//    		lamp.getTransform().getPos().y = (lamp.getTransform().getPos().y+1);
    	Kid.getPos().y = myTerrain.getHeightOfTerrain(Kid.getPos().x, Kid.getPos().z);
//    	//Quaternion cameraRot = camera.getTransform().getRot();
//    	camera.getTransform().LookAt(Kid.getPos(), new Vector3f(0, 1, 0));
    	//Kid.getTransform().setRot(camera.getTransform().GetLookAtRotation(Kid.getPos(), new Vector3f(0, 1, 0)));
    	//camera.getTransform().setRot(cameraRot.mul(camera.getTransform().getRot()));
    	
    	//Kid.getTransform().getRot().x = 0;
//    	Kid.getTransform().getRot().y = Util.clamp(Kid.getTransform().getRot().y, -0.18f, 0.18f);
    	//Kid.getTransform().getRot().z = 0;
    	if(Input.getKey(Input.KEY_SPACE))
    		guis.get(0).setTexture(tex);
    
    	//camera.setPos(new Vector3f(Kid.getPos().x, Kid.getPos().y+6.5f, Kid.getPos().z -10));
    }
    
    @Override
    public void render(RenderingEngine renderingEngine){
    	super.render(renderingEngine);
    	skybox.render(renderingEngine);
    	guiRenderer.render(guis);
    	
    	
    }
    
}
