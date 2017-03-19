package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class BoundingSphere extends Collider{
	
	private Vector3f center;
	private float radius;
	
	public BoundingSphere(Vector3f center, float radius) {
		super(ColliderType.TYPE_SPHERE);
		this.center = center;
		this.radius = radius;
	}
	
	public IntersectData intersectBoundingSphere(BoundingSphere other){
		float radiusDistance = this.radius + other.radius;
		float centerDistance = other.getCenter().sub(center).length();
		float distance = centerDistance - radiusDistance;
		
		return new IntersectData(centerDistance < radiusDistance, distance);
	}
	
	public IntersectData intersectPlane(Plane other){
		return new IntersectData(false, 0);
	}
	
	@Override
	public Vector3f getCenter() {
		return center;
	}
	public void setCenter(Vector3f center) {
		this.center = center;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	@Override
	public void transform(Vector3f translation){
		center = center.add(translation);
	}

}
