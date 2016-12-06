package com.base.engine.rendering.resourceManagement;

import java.util.HashMap;

import com.base.engine.core.Vector3f;

public abstract class MappedValues {
	
	private HashMap<String, Vector3f> vector3fHashMap;
	private HashMap<String, Float> floatHashMap;
	
	public MappedValues(){
		vector3fHashMap = new HashMap<String, Vector3f>();
		floatHashMap = new HashMap<String, Float>();
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
