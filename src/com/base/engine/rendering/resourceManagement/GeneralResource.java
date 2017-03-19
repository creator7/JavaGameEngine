package com.base.engine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import org.lwjgl.opengl.GL30;

public class GeneralResource {
	
	private int vbo;
	private int vao;
	private int size;
	private int refCount;
	
	public GeneralResource(int size) {
		vbo = glGenBuffers();
		vao = GL30.glGenVertexArrays();
		this.size = size;
		this.refCount = 1;
	}
	
	public void addReference(){
		refCount++;
	}
	
	public boolean removeReference(){
		refCount--;
		return refCount == 0;
	}
	
	@Override
	protected void finalize(){
		glDeleteBuffers(vbo);
		glDeleteBuffers(vao);
		
	}

	public int getVbo() {
		return vbo;
	}

	public int getVao() {
		return vao;
	}
	
	public int getSize(){
		return size;
	}


}
