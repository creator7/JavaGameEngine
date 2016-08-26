package com.base.engine.core;

import com.base.engine.rendering.Shader;

public abstract class GameComponent {
	
	public void setGameObject(){
		
	}
	public void input(Transform transform, float delta){}
	public void update(Transform transform, float delta){}
	public void render(Transform transform, Shader shader){}
}
