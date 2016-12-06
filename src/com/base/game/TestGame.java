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
		
		
		Material material2 = new Material();
		material2.addTexture("diffuse", new Texture("test.png"));
		material2.addFloat("specularIntensity", 1);
		material2.addFloat("specularPower", 8);
		
		Material material5 = new Material();
		material5.addTexture("diffuse", new Texture("wood.jpg"));
		material5.addFloat("specularIntensity", 1);
		material5.addFloat("specularPower", 8);
		
		Material material3 = new Material();
		material3.addTexture("diffuse", new Texture("blank.png"));
		material3.addFloat("specularIntensity", 1);
		material3.addFloat("specularPower", 8);
		
		Material material4 = new Material();
		material4.addTexture("diffuse", new Texture("tree.png"));
		material4.addFloat("specularIntensity", 1);
		material4.addFloat("specularPower", 8);
		

		Mesh tempMesh = new Mesh("monkey.obj");
		Mesh tempMesh2 = new Mesh("dragon.obj");
		Mesh tempMesh3 = new Mesh("tree.obj");
		Mesh tempMesh4 = new Mesh("Rixa.obj");
		Mesh tempMesh5 = new Mesh("wood.obj");

		Meshrenderer meshRenderer = new Meshrenderer(mesh, material2);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1.0f,0.5f,0.2f), 0.4f);

		directionalLightObject.addComponent(directionalLight);

		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Attenuation(0,0,1)));

		
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
				activeLight.addComponent(new PointLight(new Vector3f(0,0.8f,0.8f), 0.4f, new Attenuation(0,0,1)));
				activeLight.getTransform().getPos().set(new Vector3f(lightFieldStartX + lightFieldStepX * i,0,lightFieldStartY + lightFieldStepY * j));
				addObject(activeLight);
			}
		}
		
		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Attenuation(0,0,0.1f), 0.7f);

		GameObject spotLightObject = new GameObject();
		spotLightObject.addComponent(spotLight);

		spotLightObject.getTransform().getPos().set(5, 0, 5);
		spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90.0f)));

		addObject(planeObject);
		addObject(directionalLightObject);
		addObject(pointLightObject);
		addObject(spotLightObject);

		//getRootObject().addChild(new GameObject().addComponent(new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f)));

		GameObject monkey = new GameObject().addComponent(new Meshrenderer(tempMesh, material2));
		GameObject bunny = new GameObject().addComponent(new Meshrenderer(tempMesh2, material3));
		GameObject tree = new GameObject().addComponent(new Meshrenderer(tempMesh3, material4));
		GameObject Rixa = new GameObject().addComponent(new Meshrenderer(tempMesh4, material3));
		GameObject boy = new GameObject().addComponent(new Meshrenderer(new Mesh("boy.obj"), material));
		GameObject wood = new GameObject().addComponent(new Meshrenderer(tempMesh5, material5));
		
		
		monkey.getTransform().getPos().set(5, 5, 5);
		monkey.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-70f)));
		
		bunny.getTransform().setScale(new Vector3f(0.4f, 0.4f, 0.4f));
		bunny.getTransform().setPos(new Vector3f(2, -1, 10));
		bunny.addComponent(new FreeMove(10.0f, Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT));
		//tree.getTransform().setScale(new Vector3f(2.3f, 2.3f, 2.3f));
		Rixa.getTransform().setPos(new Vector3f(12, 2f, 20));
		Rixa.getTransform().setScale(new Vector3f(4, 4, 4));
		wood.getTransform().setPos(new Vector3f(2, 1, 3));
		
		Camera cam = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
		GameObject camera = new GameObject().addComponent(cam).addComponent(new FreeLook(0.5f));
		camera.addComponent(new FreeMove(10.0f));
		addObject(camera);
		addObject(tree);
		addObject(Rixa);
		monkey.addComponent(new LookAtComponent());
		
		addObject(bunny);
		addObject(monkey);
		addObject(wood);
		//boy.addComponent(new FreeLook());
		addObject(boy);
		boy.getTransform().setRot(new Quaternion(new Vector3f(1, 0, 0), (float)Math.toRadians(-90)));
		boy.getTransform().setPos(new Vector3f(5, -1, 10));

		directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1,0,0), (float)Math.toRadians(-90)));
	}
}
