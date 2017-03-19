package com.base.engine.rendering.skybox;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.base.engine.core.Util;
import com.base.engine.rendering.resourceManagement.GeneralResource;

public class SkyboxMesh {
	
	private GeneralResource resource;
	private SkyboxTexture texture;
	private static String[] TEXTURE_FILES = {"right.png", "left.png", "top.png", "bottom.png", "back.png","front.png"};
	//private Texture texture;
	
	public SkyboxMesh(float[] vertices, String[] files){
		TEXTURE_FILES = files;
		setMesh(vertices);
	}
	
	public SkyboxMesh(float[] vertices)
	{	
		setMesh(vertices);
	}
	
	private void setMesh(float[] vertices){
		this.texture = new SkyboxTexture(TEXTURE_FILES);
		resource = new GeneralResource(vertices.length);
		
		GL30.glBindVertexArray(resource.getVao());
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo() );
		FloatBuffer buffer = Util.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0,3,GL11.GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	public void draw(){
		GL30.glBindVertexArray(resource.getVao());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, resource.getSize()/3);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public SkyboxTexture getTexture() {
		return texture;
	}

}
