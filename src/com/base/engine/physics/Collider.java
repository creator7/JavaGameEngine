package com.base.engine.physics;

import com.base.engine.core.ReferenceCounter;
import com.base.engine.core.Vector3f;

public class Collider extends ReferenceCounter{
	private ColliderType type;
	
	public Collider(ColliderType type){
		super();
		this.type = type;
	}
	
	public ColliderType getType(){
		return type;
	}
	
	public IntersectData intersect(Collider other){
		if(type == ColliderType.TYPE_SPHERE && other.getType() == ColliderType.TYPE_SPHERE){
			BoundingSphere self = (BoundingSphere)this;
			return self.intersectBoundingSphere((BoundingSphere)other);
		}
		
		else if(type == ColliderType.TYPE_PLANE && other.getType() == ColliderType.TYPE_SPHERE){
			Plane self = (Plane)this;
			return self.intersectSphere((BoundingSphere)other);
		}
		
		else if(type == ColliderType.TYPE_SPHERE && other.getType() == ColliderType.TYPE_PLANE){
			BoundingSphere self = (BoundingSphere)this;
			return self.intersectPlane((Plane)other);
		}
		
		System.err.println("ERROR: Collisions not implemented between specified collider " + type + other.getType());
		System.exit(1);
		
		return new IntersectData(false, 0);
	}
	
	public void transform(Vector3f translation){}
	
	public Vector3f getCenter(){
		return new Vector3f(0, 0, 0);
	}

}
