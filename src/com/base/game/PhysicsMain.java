package com.base.game;

import com.base.engine.core.Vector3f;
import com.base.engine.physics.AABB;
import com.base.engine.physics.BoundingSphere;
import com.base.engine.physics.IntersectData;
import com.base.engine.physics.Plane;

public class PhysicsMain {
	
	public static void main(String[] args){
		BoundingSphere sphere1 = new BoundingSphere(new Vector3f(0, 0, 0), 1.0f);
		BoundingSphere sphere2 = new BoundingSphere(new Vector3f(0, 3, 0), 1.0f);
		BoundingSphere sphere3 = new BoundingSphere(new Vector3f(0, 0, 2), 1.0f);
		BoundingSphere sphere4 = new BoundingSphere(new Vector3f(1.9f, 0, 0), 1.0f);
		
		IntersectData sphereIntersectSphere2 = sphere1.intersectBoundingSphere(sphere2);
		IntersectData sphereIntersectSphere3 = sphere1.intersectBoundingSphere(sphere3);
		IntersectData sphereIntersectSphere4 = sphere1.intersectBoundingSphere(sphere4);
		
		System.out.println("Sphere1 Intersect sphere2: " + sphereIntersectSphere2.isDoesIntersect()
		+ ", Distance: " + sphereIntersectSphere2.getDistance());
		
		System.out.println("Sphere1 Intersect sphere3: " + sphereIntersectSphere3.isDoesIntersect()
		+ ", Distance: " + sphereIntersectSphere3.getDistance());
		
		System.out.println("Sphere1 Intersect sphere4: " + sphereIntersectSphere4.isDoesIntersect()
		+ ", Distance: " + sphereIntersectSphere4.getDistance());
		
		AABB aabb1 = new AABB(new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		AABB aabb2 = new AABB(new Vector3f(1, 1, 1), new Vector3f(2, 2, 2));
		AABB aabb3 = new AABB(new Vector3f(1, 0, 0), new Vector3f(2, 1, 1));
		AABB aabb4 = new AABB(new Vector3f(0, 0, -2), new Vector3f(1, 1, -1));
		AABB aabb5 = new AABB(new Vector3f(0, 0.5f, 0), new Vector3f(1, 1.5f, 1));
		
		IntersectData aabb1Intersectaabb2 = aabb1.intersectAABB(aabb2);
		IntersectData aabb1Intersectaabb3 = aabb1.intersectAABB(aabb3);
		IntersectData aabb1Intersectaabb4 = aabb1.intersectAABB(aabb4);
		IntersectData aabb1Intersectaabb5 = aabb1.intersectAABB(aabb5);
		
		System.out.println("AABB1 Intersect AABB2: " + aabb1Intersectaabb2.isDoesIntersect()
		+ ", Distance: " + aabb1Intersectaabb2.getDistance());
		
		System.out.println("AABB1 Intersect AABB3: " + aabb1Intersectaabb3.isDoesIntersect()
		+ ", Distance: " + aabb1Intersectaabb3.getDistance());
		
		System.out.println("AABB1 Intersect AABB4: " + aabb1Intersectaabb4.isDoesIntersect()
		+ ", Distance: " + aabb1Intersectaabb4.getDistance());
		
		System.out.println("AABB1 Intersect AABB5: " + aabb1Intersectaabb5.isDoesIntersect()
		+ ", Distance: " + aabb1Intersectaabb5.getDistance());
		
		Plane plane1 = new Plane(new Vector3f(0, 1, 0), 0.0f);
		
		IntersectData planeIntersectSphere1 = plane1.intersectSphere(sphere1);
		IntersectData planeIntersectSphere2 = plane1.intersectSphere(sphere2);
		IntersectData planeIntersectSphere3 = plane1.intersectSphere(sphere3);
		IntersectData planeIntersectSphere4 = plane1.intersectSphere(sphere4);
		
		System.out.println("Plane Intersect Sphere1: " + planeIntersectSphere1.isDoesIntersect()
		+ ", Distance: " + planeIntersectSphere1.getDistance());
		
		System.out.println("Plane Intersect Sphere2: " + planeIntersectSphere2.isDoesIntersect()
		+ ", Distance: " + planeIntersectSphere2.getDistance());
		
		System.out.println("Plane Intersect Sphere3: " + planeIntersectSphere3.isDoesIntersect()
		+ ", Distance: " + planeIntersectSphere3.getDistance());
		
		System.out.println("Plane Intersect Sphere4: " + planeIntersectSphere4.isDoesIntersect()
		+ ", Distance: " + planeIntersectSphere4.getDistance());
		
		
//		PhysicsObject test = new PhysicsObject(new Vector3f(0, 1, 0), new Vector3f(1, 2, 3), 1.0f);
//		
//		test.integrate(20);
//		Vector3f testPos = test.getPosition();
//		Vector3f testVelocity = test.getVelocity();
//		
//		System.out.println("Object Position: " + testPos.x + "," + testPos.y + "," +  testPos.z
//		+ ", Velocity: " + testVelocity.x + "," +  testVelocity.y + "," +  testVelocity.z);
		
		
	}

}
