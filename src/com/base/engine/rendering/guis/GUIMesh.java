package com.base.engine.rendering.guis;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.base.engine.core.Util;
import com.base.engine.rendering.resourceManagement.GeneralResource;

public class GUIMesh {
	
	private GeneralResource resource;
	
	public GUIMesh(float[] vertices)
	{	
		resource = new GeneralResource(vertices.length);
		setMesh(vertices);
	}
	
	private void setMesh(float[] vertices){
		GL30.glBindVertexArray(resource.getVao());
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo() );
		FloatBuffer buffer = Util.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0,2,GL11.GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	public void draw(){
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, resource.getSize()/2);
	}
	
	public void enableVertexArray(){
		GL30.glBindVertexArray(resource.getVao());
		GL20.glEnableVertexAttribArray(0);
	}
	
	public void disableVertexArray(){
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

}
