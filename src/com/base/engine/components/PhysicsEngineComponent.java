package com.base.engine.components;

import com.base.engine.physics.PhysicsEngine;

public class PhysicsEngineComponent extends GameComponent{
	private PhysicsEngine physicsEngine;
	
	public PhysicsEngineComponent(PhysicsEngine engine) {
		this.physicsEngine = engine;
	}
	
	public PhysicsEngine getPhysicsEngine(){
		return physicsEngine;
	}
	
	public void update(float delta){
		physicsEngine.simulate(delta);
		physicsEngine.handleCollisions();
	}
	
}
