package com.base.game;

import com.base.engine.components.Meshrenderer;
import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;

public class TestGame extends Game {
	
	public void init(){
		
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
											new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 0.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(0.0f, 1.0f)),
											new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indices[] = { 0, 1, 2,
					      2, 1, 3};
		
		Mesh mesh = new Mesh(vertices, indices,true);
		Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1),1.0f,8.0f);
		
		Meshrenderer meshRenderer = new Meshrenderer(mesh, material);
		
		GameObject plane = new GameObject();
		plane.addComponent(meshRenderer);
		plane.getTransform().setPos(0, -1, 5);
		getRootObject().addChild(plane);
		
	}
}
