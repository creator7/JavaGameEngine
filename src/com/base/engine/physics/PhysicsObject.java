package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class PhysicsObject {
	
	private Vector3f position;
	private Vector3f velocity;
	private Collider collider;
	private Vector3f oldPosition;
	
	public PhysicsObject(Collider collider, Vector3f velocity) {
		this.position = collider.getCenter();
		this.velocity = velocity;
		this.oldPosition = collider.getCenter();
		this.collider = collider;
	}
	
	public PhysicsObject(PhysicsObject other) {
		this.position = other.getPosition();
		this.velocity = other.getVelocity();
		this.oldPosition = other.oldPosition;
		this.collider = other.collider;
		this.collider.addReference();
	}
	
	@Override
	protected void finalize(){
		if(collider.removeReference()){
			collider = null;
		}
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}
	
	public void integrate(float delta){
		position = position.add(velocity.mul(delta));
	}
	
	
	public Collider getCollider(){
		
		Vector3f translation = position.sub(oldPosition);
		oldPosition = position;
		collider.transform(translation);
		return collider;
	}

}
