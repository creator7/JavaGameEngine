package com.base.game;

import com.base.engine.core.Vector3f;

public class IntersectData {
	private Vector3f face;
	private boolean intersect;
	
	public IntersectData(Vector3f face, boolean intersect) {
		this.face = face;
		this.intersect = intersect;
	}

	public Vector3f getFace() {
		return face;
	}

	public boolean isIntersect() {
		return intersect;
	}

}
