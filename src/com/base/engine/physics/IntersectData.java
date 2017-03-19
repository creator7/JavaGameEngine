package com.base.engine.physics;

public class IntersectData {
	
	private boolean doesIntersect;
	private float distance;
	
	public IntersectData(boolean doesIntersect, float distance) {
		this.doesIntersect = doesIntersect;
		this.distance = distance;
	}
	
	public boolean isDoesIntersect() {
		return doesIntersect;
	}
	public void setDoesIntersect(boolean doesIntersect) {
		this.doesIntersect = doesIntersect;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	
	

}
