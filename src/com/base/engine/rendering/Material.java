package com.base.engine.rendering;

import java.util.HashMap;

import com.base.engine.core.Vector3f;

public class Material {
	
	private HashMap<String, Texture> textureHashMap;
	private HashMap<String, Vector3f> vector3fHashMap;
	private HashMap<String, Float> floatHashMap;
	
	public Material() {
		textureHashMap = new HashMap<String, Texture>();
		vector3fHashMap = new HashMap<String, Vector3f>();
		floatHashMap = new HashMap<String, Float>();
	}
	
	public void addTexture(String name, Texture texture){
		textureHashMap.put(name, texture);
	}
	
	public Texture getTexture(String name){
		
		Texture res = textureHashMap.get(name);
		if(res != null){
			return res;
		}
		
		return new Texture("test.png");
	}
	
	public void addVector3f(String name, Vector3f value){
		vector3fHashMap.put(name, value);
	}
	
	public Vector3f getVector3f(String name){
		Vector3f res = vector3fHashMap.get(name);
		
		if(res != null){
			return res;
		}
		
		return new Vector3f(0, 0, 0);
	}
	
	public void addFloat(String name, float value){
		floatHashMap.put(name, value);
	}
	
	public float getFloat(String name){
		Float res = floatHashMap.get(name);
		if(res != null){
			return res;
		}
		return 0;
	}
	
	
}
