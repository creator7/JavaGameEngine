package com.base.game;

import com.base.engine.core.CoreEngine;

public class Main {
	public static void main(String[] args){
		
		TestGame game = new TestGame();
		CoreEngine engine = new CoreEngine(1366, 768, 60.0, game);
		engine.createWindow("3D Game Engine");
		engine.start();
	}
}
