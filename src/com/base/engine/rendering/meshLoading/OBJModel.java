package com.base.engine.rendering.meshLoading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.base.engine.core.Util;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;

public class OBJModel {

	private ArrayList<Vector3f> positions;
	private ArrayList<Vector2f> texCoords;
	private ArrayList<Vector3f> normals;
	private ArrayList<OBJIndex> indices;
	
	private boolean hasTexCoords;
	private boolean hasNormals;

	public OBJModel(String fileName) {

		this.positions = new ArrayList<Vector3f>();
		this.texCoords = new ArrayList<Vector2f>();
		this.normals = new ArrayList<Vector3f>();
		this.indices = new ArrayList<OBJIndex>();
		
		hasTexCoords = false;
		hasNormals = false;

		BufferedReader meshReader = null;

		try {
			meshReader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = meshReader.readLine()) != null) {
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyStrings(tokens);

				if (tokens.length == 0 || tokens[0].equals("#"))
					continue;
				else if (tokens[0].equals("v")) {
					positions.add(
							new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3])));
				}

				else if (tokens[0].equals("vt")) {
					texCoords.add(new Vector2f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2])));
				}
				
				else if (tokens[0].equals("vn")) {
					normals.add(
							new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3])));
				}
				
				else if(tokens[0].equals("f")){
					
					for(int i = 0; i < tokens.length - 3; i++){
						indices.add(parseOBJIndex(tokens[1]));
						indices.add(parseOBJIndex(tokens[2 + i]));
						indices.add(parseOBJIndex(tokens[3 + i]));
					}
				}
			}
			
			meshReader.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public IndexedModel toIndexedmodel(){
		IndexedModel result = new IndexedModel();
		for(int i=0; i<indices.size();i++){
			
			OBJIndex currentIndex = indices.get(i);
			
			Vector3f currentPosition = positions.get(currentIndex.vertexIndex);
			Vector2f currentTexCoord;
			Vector3f currentNormal;
			
			if(hasTexCoords)
				currentTexCoord = texCoords.get(currentIndex.texCoordIndex);
			else
				currentTexCoord = new Vector2f(0, 0);
			
			if(hasNormals)
				currentNormal = normals.get(currentIndex.normalIndex);
			else
				currentNormal = new Vector3f(0, 0, 0);
			
//			int previousVertexIndex = -1;
//			
//			for(int j = 0; j<i;j++){
//				OBJIndex 
//				
//			}
			result.getPositions().add(currentPosition);
			result.getNormals().add(currentNormal);
			result.getTexCoords().add(currentTexCoord);
			result.getIndices().add(i);
			
		}
		return result;
	}
	
	private OBJIndex parseOBJIndex(String token){
	
		String[] values = token.split("/");
		OBJIndex result = new OBJIndex();
		
		result.vertexIndex = Integer.parseInt(values[0]) - 1;
		
		if(values.length > 1){
			
			hasTexCoords = true;
			result.texCoordIndex = Integer.parseInt(values[1]) - 1;
			
			if(values.length > 2){
				
				hasNormals = true;
				result.normalIndex = Integer.parseInt(values[2]) - 1;
			}
		}
		
		return result;
	}

}
