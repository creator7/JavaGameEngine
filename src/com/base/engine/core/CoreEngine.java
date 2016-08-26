package com.base.engine.core;

import com.base.engine.rendering.Window;

public class CoreEngine 
{
    
    private Game game;
    private RenderingEngine renderingEngine;
    private boolean isRunning;
    private int width;
    private int height;
    private double frameTime;
    
    public CoreEngine(int width, int height, double frameRate, Game game)
    {
    	isRunning = false;
    	this.width = width;
    	this.height = height;
    	this.frameTime = 1.0/frameRate;
    	this.game = game;
    }
    
    public void createWindow(String title){
    	Window.createWindow(width, height, title);
    	this.renderingEngine = new RenderingEngine();
    }
    
    public void start()
    {
    	if(isRunning)
    		return;
        run();
    }
    
    public void stop()
    {
        if(!isRunning)
        	return;
        
        isRunning = false;
    }
    
    private void run()
    {
    	isRunning = true;
    	
    	int frames = 0;
    	long frameCounter = 0;
    	
    	game.init();
    	
    	double lastTime = Time.getTime();
    	double unprocessedTime = 0;
    	
        while(isRunning)
        {
        	
        	boolean render = false;
        	double startTime = Time.getTime();
        	double passedTime = startTime - lastTime;
        	lastTime = startTime;
        	
        	unprocessedTime += passedTime;
        	frameCounter += passedTime;
        	
        	while(unprocessedTime > frameTime){
        		
        		render = true;
        		unprocessedTime -= frameTime;
        		
        		if(Window.isCloseRequested())
            		stop();
        		
        		Input.update();
        		
        		game.input((float)frameTime);
        		//Input.update();
        		renderingEngine.input((float)frameTime);
        		game.update((float)frameTime);
        		
        		if(frameCounter >= 1.0)
        		{
        			System.out.println(frames);
        			frames = 0;
        			frameCounter = 0;
        		}
        		
        	}
        	
        	if(render)
        	{
        		renderingEngine.render(game.getRootObject());
        		Window.render();
        		frames++;
        	}
        	else
        	{
        		try
        		{
					Thread.sleep(1);
				} 
        		catch (InterruptedException e)
        		{
					e.printStackTrace();
				}
        	}
        }
    }
    
    public void cleanUp()
    {
        Window.dispose();
    }  
}