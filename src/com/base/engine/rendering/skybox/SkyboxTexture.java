package com.base.engine.rendering.skybox;

import java.io.FileInputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.base.engine.rendering.resourceManagement.TextureResource;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class SkyboxTexture {
	
	private TextureResource resource;
	
	public SkyboxTexture(String[] textureFiles){
		resource = new TextureResource();
		loadCubeMap(textureFiles);
	}
	
	private void loadCubeMap(String[] textureFiles){
		
		bind();
		
		for(int i =0; i<textureFiles.length;i++){
			int width = 0;
			int height = 0;
			ByteBuffer buffer = null;
			try {
				FileInputStream in = new FileInputStream("./res/textures/" + textureFiles[i]);
				PNGDecoder decoder = new PNGDecoder(in);
				width = decoder.getWidth();
				height = decoder.getHeight();
				buffer = ByteBuffer.allocateDirect(4 * width * height);
				decoder.decode(buffer, width * 4, Format.RGBA);
				buffer.flip();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Tried to load texture " + textureFiles[i] + ", didn't work");
				System.exit(-1);
			}
			
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		}
		
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);

	}
	
	public void bind(){
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, resource.getid());
	}

	public TextureResource getResource() {
		return resource;
	}

}
