package com.base.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.base.engine.core.Util;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.meshLoading.IndexedModel;
import com.base.engine.rendering.meshLoading.OBJModel;

public class Mesh 
{
	
	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh(String fileName){
		initMeshData();
		loadMesh(fileName);
	}
	
	public Mesh(Vertex[] vertices , int[] indices){
		this(vertices, indices, false);
	}
	
	public Mesh(Vertex[] vertices , int[] indices, boolean calcNormals)
	{
		initMeshData();
		addVertices(vertices, indices, calcNormals);
	}
	
	private void initMeshData(){
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		size = 0;
	}
	
	private void addVertices(Vertex[] vertices , int[] indices, boolean calcNormals)
	{
		
		if(calcNormals){
			calcNormals(vertices, indices);
		}
		
		size = indices.length;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo );
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo );
		
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
	public void Draw()
	{
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		
		glVertexAttribPointer(0 , 3 , GL_FLOAT , false , Vertex.SIZE * 4 , 0);
		glVertexAttribPointer(1 , 2, GL_FLOAT , false , Vertex.SIZE * 4 , 12);
		glVertexAttribPointer(2 , 3, GL_FLOAT , false , Vertex.SIZE * 4 , 20);
		
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT , 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		
	}
	
	private Mesh loadMesh(String fileName)
	{
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		
		
		if(!ext.equals("obj"))
		{
			System.err.println("File Format not Supported: " + ext);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		OBJModel test = new OBJModel("./res/models/" + fileName);
		IndexedModel model = test.toIndexedmodel();
		model.calcNormals();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		for(int i = 0;i < model.getPositions().size(); i++){
			vertices.add(new Vertex(model.getPositions().get(i),
					model.getTexCoords().get(i),
					model.getNormals().get(i)));
		}
		
		Vertex[] vertexData = new Vertex[vertices.size()];
		vertices.toArray(vertexData);
		
		Integer[] indexData = new Integer[model.getIndices().size()];
		model.getIndices().toArray(indexData);
		
		addVertices(vertexData, Util.toIntArray(indexData), false);
		
		return null;
	}
	
	private void calcNormals(Vertex[] vertices, int[] indices){
		for (int i = 0; i < indices.length; i += 3 ) {
			int i0 = indices[i];
			int i1 = indices[i+1];
			int i2 = indices[i+2];
			
			Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
			Vector3f v2 = vertices[i2].getPos().sub(vertices[i1].getPos());
			
			Vector3f normal = v1.cross(v2).normalized();
			
			vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
		}
		
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].setNormal(vertices[i].getNormal().normalized());
		}
	}

}
