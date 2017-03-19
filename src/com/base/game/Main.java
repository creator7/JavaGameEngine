package com.base.game;

import com.base.engine.core.CoreEngine;

public class Main {
	public static void main(String[] args){
		
		//MyGame game = new MyGame();
		CoreEngine engine = new CoreEngine(800, 600, 100.0, new MyGame());
		engine.createWindow("3D Game Engine");
		engine.start();
	}
}
