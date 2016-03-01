package com.base.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLoader
{
	public static String loadShader(String fileName)
	{
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		
		try 
		{
			shaderReader = new BufferedReader(new FileReader("./res/shaders/"+ fileName));
			String line;
			try 
			{
				while((line = shaderReader.readLine()) != null)
				{
					shaderSource.append(line).append("\n");
				}
				shaderReader.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return shaderSource.toString();
	}

}
