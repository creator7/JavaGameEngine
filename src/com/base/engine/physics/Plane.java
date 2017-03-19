package com.base.engine.physics;

import com.base.engine.core.Vector3f;

public class Plane  extends Collider {
	private Vector3f normal;
	private float distance;
	
	public Plane(Vector3f normal, float distance) {
		super(ColliderType.TYPE_PLANE);
		this.normal = normal;
		this.distance = distance;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public Plane normalized(){
		return new Plane(this.normal.normalized(), distance/this.normal.length());
	}
	
	public IntersectData intersectSphere(BoundingSphere sphere){
		float distanceFromSphereCenter = Math.abs(normal.dot(sphere.getCenter()) + distance);
		float distanceFromSphere = distanceFromSphereCenter - sphere.getRadius();
		
		return new IntersectData(distanceFromSphere < 0, distanceFromSphere);
	}

}
