package com.base.engine.components;

import com.base.engine.physics.PhysicsObject;

public class PhysicsObjectComponent extends GameComponent{
	
	private PhysicsObject physicsObject;
	
	public PhysicsObjectComponent(PhysicsObject object){
		physicsObject = object;
	}
	
	public void update(float delta){
		getTransform().setPos(physicsObject.getPosition());
	}
}
