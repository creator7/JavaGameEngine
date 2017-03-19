package com.base.engine.rendering.terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.base.engine.core.Util;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Vertex;

public class Terrain {
	
	private static final float SIZE = 800;
    private static final float MAX_HEIGHT = 40f;
    private static final float MAX_PIXLE_COLOR = 256 * 256 * 256;
    private Vertex[] vertices;
    private int[] indices;
    private float[][] heights;
    private ArrayList<Vertex> vertexs;
    
    public Terrain(String heightMap){
    	vertexs = new ArrayList<>();
    	generateTerrain(heightMap);
    	vertices = new Vertex[vertexs.size()];
        vertexs.toArray(vertices);
    }
	
	private void generateTerrain(String heightMap){
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("res/textures/" + heightMap + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int VERTEX_COUNT = image.getHeight();
		heights = new float[VERTEX_COUNT][VERTEX_COUNT];

        indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
        
        for(int i=0;i<VERTEX_COUNT;i++){
            for(int j=0;j<VERTEX_COUNT;j++){
                Vector3f pos = new Vector3f(0,0,0);
                pos.x = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
                float height = getHeight(j, i, image);
                heights[j][i] = height;
                pos.y = (height);
                pos.z = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
                Vector2f textureCoords = new Vector2f(0, 0);
                textureCoords.x = (float)j/((float)VERTEX_COUNT - 1);
                textureCoords.y = (float)i/((float)VERTEX_COUNT - 1);
                
                vertexs.add(new Vertex(pos, textureCoords));
            }
        }
        int pointer = 0;
        for(int gz=0;gz<VERTEX_COUNT-1;gz++){
            for(int gx=0;gx<VERTEX_COUNT-1;gx++){
                int topLeft = (gz*VERTEX_COUNT)+gx;
                int topRight = topLeft + 1;
                int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
    }
	
	private float getHeight(int x, int z, BufferedImage image){
		if(x<0 || x>= image.getHeight() || z<0 || z>= image.getHeight()){
			return 0;
		}
		float height = image.getRGB(x, z);
		height += MAX_PIXLE_COLOR/2f;
		height /= MAX_PIXLE_COLOR/2f;
		height *= MAX_HEIGHT;
		return height;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public int[] getIndices() {
		return indices;
	}
	
	public float getHeightOfTerrain(float terrainX, float terrainZ){
		//float terrainX = worldX - this.x;
		//float terrainZ = worldZ - this.z;
		
		float gridSquareSize = SIZE / ((float)heights.length - 1);
		
		int gridX = (int) Math.floor(terrainX / gridSquareSize);
		int gridZ = (int) Math.floor(terrainZ / gridSquareSize);
		
		int length = heights.length;
		
		
		if(gridX >= length -1 || gridZ >= length -1 || gridX < 0 || gridZ < 0){
			return 0;
		}
		
		float xCoord = (terrainX % gridSquareSize)/ gridSquareSize;
		float zCoord = (terrainZ % gridSquareSize)/ gridSquareSize;
		float answer;
		if (xCoord <= (1-zCoord)) {
			answer = Util
					.barryCentric(new Vector3f(0, heights[gridX][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ], 0), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		} else {
			answer = Util
					.barryCentric(new Vector3f(1, heights[gridX + 1][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ + 1], 1), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		}
		
		return answer;
	}

}
