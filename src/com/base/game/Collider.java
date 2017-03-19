package com.base.game;

import com.base.engine.core.Vector3f;
import com.base.engine.physics.AABB;

public class Collider {
	private static float distances[];
	private static int index;
	
    public static final Vector3f faces[] = { 

            new Vector3f(-1,  0,  0), // 'left' face normal (-x direction)

            new Vector3f( 1,  0,  0), // 'right' face normal (+x direction)

            new Vector3f( 0, -1,  0), // 'bottom' face normal (-y direction)

            new Vector3f( 0,  1,  0), // 'top' face normal (+y direction)

            new Vector3f( 0,  0, -1), // 'far' face normal (-z direction)

            new Vector3f( 0,  0,  1), // 'near' face normal (+x direction)

        };
	
	public static IntersectData collide(AABB a, AABB b){
		
		distances = new float[] {(b.getMaxExtents().x - a.getMinExtents().x), 
				(a.getMaxExtents().x - b.getMinExtents().x),
				(b.getMaxExtents().y - a.getMinExtents().y),
				(a.getMaxExtents().y - b.getMinExtents().y),
				(b.getMaxExtents().z - a.getMinExtents().z),
				(a.getMaxExtents().z - b.getMinExtents().z)
		};
		
		for(int i = 0; i < 6; i ++){

		 if(distances[i] < 0.0f) 
		     return new IntersectData(new Vector3f(0, 0, 0), false);

		 if((i == 0) || (distances[i] < 0)) 
		     index = i;
		}
		
		return new IntersectData(faces[index], true);
	}

}
