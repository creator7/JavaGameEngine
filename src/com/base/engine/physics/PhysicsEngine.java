package com.base.engine.physics;

import java.util.ArrayList;

public class PhysicsEngine {
	
	private ArrayList<PhysicsObject> objects;
	private static final float friction = -0.5f;
	
	public PhysicsEngine() {
		objects = new ArrayList<>();
	}
	
	public void addObject(PhysicsObject object){
		objects.add(object);
	}
	
	public void simulate(float delta){
		for(PhysicsObject m_object: objects){
			m_object.integrate(delta);
		}
	}
	
	public PhysicsObject getObject(int index){
		return objects.get(index);
	}
	
	public int getNumObjects(){
		return objects.size();
	}
	
	public void handleCollisions(){
		for(int i = 0; i < objects.size(); i++){
			for(int j = i+1; j< objects.size(); j++){
				IntersectData intersectData = objects.get(i).getCollider().intersect(objects.get(j).getCollider());
				
				if(intersectData.isDoesIntersect()){
					objects.get(i).setVelocity(objects.get(i).getVelocity().mul(-1));
					objects.get(j).setVelocity(objects.get(j).getVelocity().mul(-1));
				}
			}
		}
	}
}
