package com.base.game;

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
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Input;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.physics.AABB;
import com.base.engine.physics.IntersectData;
import com.base.engine.rendering.Attenuation;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;
import com.base.engine.rendering.Window;

public class Picker extends Game{
	
private Skybox skybox;
private GameObject camera;
private GameObject spotLight;
private Vector3f ambientLight;
private AABB cameraCollider;
private AABB wall1Collider;
    
	public void init()
	{
		cameraCollider = new AABB(new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		
		Quaternion angle120 = new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(120));
		Quaternion angle90 = new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90));
		
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		String[] FILES = {"vr_rt.png", "vr_lf.png", "vr_up.png", "vr_dn.png", "vr_bk.png","vr_ft.png"};
		
    	skybox = new Skybox(FILES);

		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

		int indices[] = { 0, 1, 2,
				2, 1, 3};

//		Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, -fieldDepth/ 10), new Vector2f(0.0f, 0.0f)),
//				new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, fieldDepth/ 10 * 3), new Vector2f(0.0f, 1.0f)),
//				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, -fieldDepth/ 10), new Vector2f(1.0f, 0.0f)),
//				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, fieldDepth/ 10 * 3), new Vector2f(1.0f, 1.0f))};
//
//		int indices2[] = { 0, 1, 2,
//				2, 1, 3};
//
//		Mesh mesh2 = new Mesh(vertices2, indices2, true);

		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material();
		material.addTexture("diffuse", new Texture("test.png"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);
		
		Material green = new Material();
		green.addTexture("diffuse", new Texture("Green.png"));
		green.addFloat("specularIntensity", 1);
		green.addFloat("specularPower", 8);
		
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
		
		Material blank = new Material();
		blank.addTexture("diffuse", new Texture("blank.png"));
		blank.addFloat("specularIntensity", 1);
		blank.addFloat("specularPower", 8);
		

		Mesh tempMesh = new Mesh("monkey.obj");
		Mesh cubeMesh = new Mesh("cube.obj");

		Meshrenderer meshRenderer = new Meshrenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, -1, 0);
		planeObject.getTransform().setScale(new Vector3f(3f, 1, 3f));

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0.2f,0.2f,0.2f), 0.01f);
		directionalLightObject.addComponent(directionalLight);
		directionalLightObject.setPos(new Vector3f(1000, 1000, -7000));
		
		
		GameObject boundary1 = new GameObject();
		GameObject boundary2 = new GameObject();
		GameObject boundary3 = new GameObject();
		GameObject boundary4 = new GameObject();
		GameObject wall1 = new GameObject();
		GameObject wall2 = new GameObject();
		GameObject wall3 = new GameObject();
		GameObject wall4 = new GameObject();
		GameObject wall5 = new GameObject();
		GameObject wall6 = new GameObject();
		GameObject wall7 = new GameObject();
		GameObject wall8 = new GameObject();
		GameObject wall9 = new GameObject();
		GameObject wall10 = new GameObject();
		GameObject wall11 = new GameObject();
		GameObject wall12 = new GameObject();
		GameObject wall13 = new GameObject();
		GameObject wall14 = new GameObject();
		GameObject wall15 = new GameObject();
		GameObject wall16 = new GameObject();
		
		boundary1.addComponent(new Meshrenderer(cubeMesh, red));
		boundary1.getTransform().setScale(new Vector3f(0.5f, 8, 60));
		boundary1.setPos(new Vector3f(30, 7, 90));
		boundary1.getTransform().setRot(angle90);
		
		boundary2.addComponent(new Meshrenderer(cubeMesh, red));
		boundary2.getTransform().setScale(new Vector3f(0.5f, 8, 60));
		boundary2.setPos(new Vector3f(30, 7, -30));
		boundary2.getTransform().setRot(angle90);
		
		boundary3.addComponent(new Meshrenderer(cubeMesh, red));
		boundary3.getTransform().setScale(new Vector3f(0.5f, 8, 60));
		boundary3.setPos(new Vector3f(90, 7, 30));
		
		boundary4.addComponent(new Meshrenderer(cubeMesh, red));
		boundary4.getTransform().setScale(new Vector3f(0.5f, 8, 60));
		boundary4.setPos(new Vector3f(-30, 7, 30));
		
		wall1.addComponent(new Meshrenderer(cubeMesh, green));
		wall1.getTransform().setScale(new Vector3f(0.5f, 5, 20));
		wall1.setPos(new Vector3f(10, 4, -10));
		wall1Collider = new AABB(wall1.getPos(), wall1.getPos().add(wall1.getTransform().getScale()));
		
		wall5.addComponent(new Meshrenderer(cubeMesh, green));
		wall5.getTransform().setScale(new Vector3f(0.5f, 5, 20));
		wall5.setPos(new Vector3f(20, 4, -10));
		
		wall2.addComponent(new Meshrenderer(cubeMesh, blank));
		wall2.getTransform().setScale(new Vector3f(0.5f, 5, 10));
		wall2.setPos(new Vector3f(0, 4, 10));
		wall2.getTransform().setRot(angle90);
		
		wall3.addComponent(new Meshrenderer(cubeMesh, blue));
		wall3.getTransform().setScale(new Vector3f(0.5f, 5, 50));
		wall3.setPos(new Vector3f(20, 4, 20));
		wall3.getTransform().setRot(angle90);
		
		wall4.addComponent(new Meshrenderer(cubeMesh, blank));
		wall4.getTransform().setScale(new Vector3f(0.5f, 5, 10));
		wall4.setPos(new Vector3f(30, 4, 10));
		wall4.getTransform().setRot(angle90);
		
		wall7.addComponent(new Meshrenderer(cubeMesh, pink));
		wall7.getTransform().setScale(new Vector3f(0.5f, 5, 30));
		wall7.setPos(new Vector3f(50, 4, 50));
		wall7.getTransform().setRot(angle120);
		
		wall6.addComponent(new Meshrenderer(cubeMesh, green));
		wall6.getTransform().setScale(new Vector3f(0.5f, 5, 6f));
		wall6.setPos(new Vector3f(50, 4, 32));
		
		wall8.addComponent(new Meshrenderer(cubeMesh, green));
		wall8.getTransform().setScale(new Vector3f(0.5f, 5, 10f));
		wall8.setPos(new Vector3f(40, 4, 36));
		
		wall9.addComponent(new Meshrenderer(cubeMesh, blue));
		wall9.getTransform().setScale(new Vector3f(0.5f, 5, 10f));
		wall9.setPos(new Vector3f(25, 4, 55));
		wall9.getTransform().setRot(angle120);
		
		wall10.addComponent(new Meshrenderer(cubeMesh, pink));
		wall10.getTransform().setScale(new Vector3f(0.5f, 5, 20f));
		wall10.setPos(new Vector3f(15, 4, 48));
		wall10.getTransform().setRot(angle120);
		
		wall11.addComponent(new Meshrenderer(cubeMesh, pink));
		wall11.getTransform().setScale(new Vector3f(0.5f, 5, 45));
		wall11.setPos(new Vector3f(60, 4, 60));
		wall11.getTransform().setRot(angle120);
		
		wall12.addComponent(new Meshrenderer(cubeMesh, blank));
		wall12.getTransform().setScale(new Vector3f(0.5f, 5, 10f));
		wall12.setPos(new Vector3f(10, 4, 36));
		
		wall13.addComponent(new Meshrenderer(cubeMesh, red));
		wall13.getTransform().setScale(new Vector3f(0.5f, 5, 25f));
		wall13.setPos(new Vector3f(-8, 4, 39f));
		wall13.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(140)));
		
		wall14.addComponent(new Meshrenderer(cubeMesh, blank));
		wall14.getTransform().setScale(new Vector3f(0.5f, 5, 10f));
		wall14.setPos(new Vector3f(13, 4, 67f));
		wall14.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(210)));
		
		wall15.addComponent(new Meshrenderer(cubeMesh, green));
		wall15.getTransform().setScale(new Vector3f(0.5f, 5, 10));
		wall15.setPos(new Vector3f(40, 4, 0));
		
		wall16.addComponent(new Meshrenderer(cubeMesh, green));
		wall16.getTransform().setScale(new Vector3f(0.5f, 5, 10f));
		wall16.setPos(new Vector3f(-10, 4, 0));
		
		spotLight = new GameObject().addComponent(new SpotLight(new Vector3f(0.5f,0.5f,0f), 1.5f, new Attenuation(1.0f,0.0f,0.01f), 0.4f));
		
//		int lightFieldWidth = 5;
//		int lightFieldDepth = 5;
//
//		float lightFieldStartX = 0;
//		float lightFieldStartY = 0;
//		float lightFieldStepX = 20;
//		float lightFieldStepY = 20;
//		
//		Random random = new Random();
//
//		for(int i = 0; i < lightFieldWidth; i++)
//		{
//			for(int j = 0; j < lightFieldDepth; j++)
//			{
//				GameObject activeLight = new GameObject();
//				activeLight.addComponent(new PointLight(new Vector3f(random.nextFloat() * 1.0f,random.nextFloat() * 1.0f,random.nextFloat() * 1.0f), 0.2f, new Attenuation(0,0.2f,0)));
//				activeLight.getTransform().getPos().set(new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j));
//				addObject(activeLight);
//			}
//		}
		
		addObject(planeObject);
		addObject(directionalLightObject);
		addObject(boundary1);
		addObject(boundary2);
		addObject(boundary3);
		addObject(boundary4);
		addObject(wall1);
		addObject(wall2);
		addObject(wall3);
		addObject(wall4);
		addObject(wall5);
		addObject(wall6);
		addObject(wall7);
		addObject(wall8);
		addObject(wall9);
		addObject(wall10);
		addObject(wall11);
		addObject(wall12);
		addObject(wall13);
		addObject(wall14);
		addObject(wall15);
		addObject(wall16);
		addObject(spotLight);

		GameObject monkey = new GameObject().addComponent(new Meshrenderer(tempMesh, material));
		
		
		monkey.getTransform().getPos().set(5, 5, 5);
		
		
		Camera cam = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
		camera = new GameObject().addComponent(cam).addComponent(new FreeLook(0.5f));
		camera.addComponent(new FreeMove(20.0f));
		//camera.setPos(new Vector3f(-1.24896f, 49.29807f, 8.450344f));
		addObject(camera);
		monkey.addComponent(new LookAtComponent(camera));
		
		addObject(monkey);
		//directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1,0,0), (float)Math.toRadians(-90)));
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
//		Quaternion newRot = camera.getTransform().getRot();
//		newRot.x = 0.0f;
//		newRot.z = 0.0f;
//		camera.getTransform().setRot(newRot);
		
		cameraCollider.setMinExtents(camera.getPos());
		cameraCollider.setMaxExtents(camera.getPos().add(1));
		
		IntersectData data = cameraCollider.intersectAABB(wall1Collider);
		
		if(data.isDoesIntersect()){
		}
		
		//System.out.println(wall1Collider.getMaxExtents());
		
		spotLight.setTransform(camera.getTransform());
	}
	
    @Override
    public void render(RenderingEngine renderingEngine){
//    	if(ambientLight == null){
//    		ambientLight = new Vector3f(0.001f, 0.001f, 0.001f);
//    		renderingEngine.setAmbientlight(ambientLight);
//    	}
    	super.render(renderingEngine);
    	skybox.render(renderingEngine);
    	
    }

}
