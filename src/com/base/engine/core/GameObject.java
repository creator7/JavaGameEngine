package com.base.engine.core;

import java.util.ArrayList;

import com.base.engine.components.GameComponent;
import com.base.engine.components.TerrainRenderer;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class GameObject {
	
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;
	private CoreEngine engine;
	private boolean terrain;
	
	public GameObject(){
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
		terrain = false;
	}
	
	public void addChild(GameObject child){
		children.add(child);
		child.setEngine(engine);
		child.getTransform().setParent(transform);
	}
	
	public GameObject addComponent(GameComponent component){
		components.add(component);
		component.setParent(this);
		if(component.getClass().equals(TerrainRenderer.class)){
			this.terrain = true;
		}
		
		return this;
	}
	
	public void input(float delta){
		
		for(GameComponent component: components){
			component.input(delta);
		}
		
		for(GameObject child: children){
			child.input(delta);
		}
	}
	
	public void update(float delta){
		
		transform.update();
		
		for(GameComponent component: components){
			component.update(delta);
		}
		
		for(GameObject child: children){
			child.update(delta);
		}
	}
	
	public void render(Shader shader, RenderingEngine renderingEngine){
		
		for(GameObject child: children){
			child.render(shader, renderingEngine);
		}
		
		for(GameComponent component: components){
			component.render(shader, renderingEngine);
		}
	}

	public Transform getTransform() {
		return transform;
	}
	
	public void setEngine(CoreEngine engine){
		if(this.engine != engine){
			
			for(GameComponent component: components){
				component.addToEngine(engine);
			}
			
			for(GameObject child: children){
				child.setEngine(engine);
			}
			this.engine = engine;
		}
	}
	
	public boolean isTerrain(){
		return this.terrain;
	}
	
	public void setPos(Vector3f pos){
		this.getTransform().setPos(pos);
	}
	
	public Vector3f getPos(){
		return this.getTransform().getPos();
	}
	
	public void setTransform(Transform transform){
		this.transform = transform;
	}
	
}
