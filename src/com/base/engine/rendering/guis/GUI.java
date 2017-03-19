package com.base.engine.rendering.guis;

import com.base.engine.core.Vector2f;
import com.base.engine.rendering.Texture;

public class GUI {
	
	private Texture texture;
	private Vector2f position;
	private Vector2f scale;
	
	//private static final float[] VERTICES = {0,1,0,0,1,0,1,1};
			
	public GUI(Texture texture, Vector2f position, Vector2f scale) {
		this.texture = texture;
		this.position = position;
		this.scale = scale;
	}

	public Texture getTexture() {
		return texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public Vector2f getScale() {
		return scale;
	}
	
	public void setTexture(Texture texture){
		this.texture = texture;
	}

}
