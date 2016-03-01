package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh 
{
	
	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh()
	{
		vbo = glGenBuffers();
		ibo = glGenBuffers();
	}
	
	public void addVertices(Vertex[] vertices , int[] indices)
	{
		size = indices.length;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo );
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo );
		
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
	public void Draw()
	{
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		
		glVertexAttribPointer(0 , 3 , GL_FLOAT , false , Vertex.SIZE * 4 , 0);
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT , 0);
		
		glDisableVertexAttribArray(0);
		
	}

}
