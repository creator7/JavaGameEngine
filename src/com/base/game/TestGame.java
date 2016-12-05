package com.base.game;


import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public void init()
	{
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

		int indices[] = { 0, 1, 2,
				2, 1, 3};

		Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, -fieldDepth/ 10), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, fieldDepth/ 10 * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, -fieldDepth/ 10), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, fieldDepth/ 10 * 3), new Vector2f(1.0f, 1.0f))};

		int indices2[] = { 0, 1, 2,
				2, 1, 3};

		Mesh mesh2 = new Mesh(vertices2, indices2, true);

		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material();
		material.addTexture("diffuse", new Texture("test.png"));
		//material.addFloat("specularIntensity", 1);
		//material.addFloat("specularPower", 8);
		
		
		Material material2 = new Material();
		material2.addTexture("diffuse", new Texture("test.png"));
		material2.addFloat("specularIntensity", 1);
		material2.addFloat("specularPower", 8);
		

		Mesh tempMesh = new Mesh("monkey.obj");

		Meshrenderer meshRenderer = new Meshrenderer(mesh, material2);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0.0f,1f,1f), 0.4f);

		directionalLightObject.addComponent(directionalLight);

		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Vector3f(0,0,1)));

		
		int lightFieldWidth = 5;
		int lightFieldDepth = 5;

		float lightFieldStartX = 0;
		float lightFieldStartY = 0;
		float lightFieldStepX = 7;
		float lightFieldStepY = 7;

		for(int i = 0; i < lightFieldWidth; i++)
		{
			for(int j = 0; j < lightFieldDepth; j++)
			{
				GameObject activeLight = new GameObject();
				activeLight.addComponent(new PointLight(new Vector3f(0,0.8f,0.8f), 0.4f, new Vector3f(0,0,1)));
				activeLight.getTransform().getPos().set(new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j));
				addObject(activeLight);
			}
		}
		
		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Vector3f(0,0,0.1f), 0.7f);

		GameObject spotLightObject = new GameObject();
		spotLightObject.addComponent(spotLight);

		spotLightObject.getTransform().getPos().set(5, 0, 5);
		spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90.0f)));

		addObject(planeObject);
		addObject(directionalLightObject);
		addObject(pointLightObject);
		addObject(spotLightObject);

		//getRootObject().addChild(new GameObject().addComponent(new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f)));

		GameObject testMesh1 = new GameObject().addComponent(new Meshrenderer(mesh2, material2));
		GameObject testMesh2 = new GameObject().addComponent(new Meshrenderer(mesh2, material2));
		GameObject monkey = new GameObject().addComponent(new Meshrenderer(tempMesh, material2));

		testMesh1.getTransform().getPos().set(0, 2, 0);
		testMesh1.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), 0.4f));
		
		monkey.getTransform().getPos().set(5, 5, 5);
		monkey.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-70f)));

		testMesh2.getTransform().getPos().set(0, 0, 5);

		testMesh1.addChild(testMesh2);
		testMesh2.addChild(new GameObject().addComponent(new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f)));

		addObject(testMesh1);
		addObject(monkey);
		GameObject boy = new GameObject().addComponent(new Meshrenderer(new Mesh("boy.obj"), material));
		addObject(boy);
		boy.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(-90)));
		boy.getTransform().setPos(new Vector3f(5, -1, 10));

		directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1,0,0), (float)Math.toRadians(-90)));
	}
}
