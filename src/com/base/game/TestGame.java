package com.base.game;

import com.base.engine.components.Camera;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.Meshrenderer;
import com.base.engine.components.PointLight;
import com.base.engine.components.SpotLight;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Quaternion;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;
import com.base.engine.rendering.Window;

public class TestGame extends Game {
	
	GameObject spotLightObject = new GameObject();
	GameObject camera = new GameObject();
	
	public void init(){
		
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
											new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 0.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(0.0f, 1.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indices[] = { 0, 1, 2,
					      2, 1, 3};
		
		Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth/10, 0.0f, -fieldDepth/10), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth/10, 0.0f, fieldDepth/10 * 3), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth/10 * 3, 0.0f, -fieldDepth/10), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth/10 * 3, 0.0f, fieldDepth/10 * 3), new Vector2f(1.0f, 1.0f))};

		int indices2[] = { 0, 1, 2,
						   2, 1, 3};
		
		Mesh mesh = new Mesh(vertices, indices,true);
		Mesh mesh2 = new Mesh(vertices2, indices2,true);
		
		Material material = new Material();
		
		material.addTexture("diffuse", new Texture("test.png"));
		material.addFloat("specularIntensity", 1);
		//material.addFloat("specularPower", 8f);
		Mesh tempMesh = new Mesh("monkey.obj");
		
		
		Meshrenderer meshRenderer = new Meshrenderer(mesh, material);
		
		GameObject plane = new GameObject();
		plane.addComponent(meshRenderer);
		plane.getTransform().getPos().set(0, -1, 5);
		
//		GameObject directionalLightObject = new GameObject();
//		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, 1), 0.4f);
//		directionalLightObject.addComponent(directionalLight);
//		
//		GameObject pointLightObject = new GameObject();	
//		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Vector3f(0,0,1f)));
//		
//		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,new Vector3f(0,0,0.1f), 0.7f);
//		
//		spotLightObject.addComponent(spotLight);
//		spotLightObject.getTransform().getPos().set(0, 5, 0);
//		spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90.0f)));
		
		addObject(plane);
		//addObject(directionalLightObject);
		//addObject(pointLightObject);
		//addObject(spotLightObject);
		
		camera.addComponent(new Camera((float)Math.toRadians(70.0f), (float)(Window.getWidth()/Window.getHeight()), 0.1f, 1000f));
		
		//addObject(camera);
		

		
		GameObject testMesh1 = new GameObject().addComponent(new Meshrenderer(mesh2, material));
		GameObject testMesh2 = new GameObject().addComponent(new Meshrenderer(mesh2, material));
		GameObject testMesh3 = new GameObject().addComponent(new Meshrenderer(tempMesh, material));
		
		testMesh1.getTransform().setPos(new Vector3f(0, 2, 0));
		testMesh1.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), 0.4f));
		testMesh2.getTransform().setPos(new Vector3f(0, 0, 5));
		
		testMesh2.addChild(camera);
		
		testMesh1.addChild(testMesh2);
		
		addObject(testMesh1);
		addObject(testMesh3);
//		directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(-45f)));
		
	}
	
	public void update (){
		
		spotLightObject.getTransform().setPos(camera.getTransform().getPos());
		spotLightObject.getTransform().setRot(camera.getTransform().getRot());
		
	}
}
