//package com.base.engine.rendering;
//
//import com.base.engine.components.BaseLight;
//import com.base.engine.components.DirectionalLight;
//import com.base.engine.components.PointLight;
//import com.base.engine.core.Matrix4f;
//import com.base.engine.core.Transform;
//import com.base.engine.core.Vector3f;
//
//public class PhongShader extends Shader{
//	
//	private static final int MAX_POINT_LIGHTS = 4;
//	private static final int MAX_SPOT_LIGHTS = 4;
//	
//	private static final PhongShader instance = new PhongShader();
//	
//	private static Vector3f ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
//	
//	private static PointLight[] pointLights = new PointLight[] {};
//	private static SpotLight[] spotLights = new SpotLight[] {};
//	
//	private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0), new Vector3f(0, 0, 0));
//	
//	public static PhongShader getInstance(){
//		return instance;
//	}
//	public PhongShader() {
//		super();
//		
//		addVertexShaderFromFile("phongVertex.vs");
//		addFragmentShaderFromFile("phongFragment.fs");
//		
//		compileShader();
//		
//		addUniform("transform");
//		addUniform("transformProjected");
//		addUniform("baseColor");
//		addUniform("ambientLight");
//		
//		addUniform("specularIntensity");
//		addUniform("specularPower");
//		addUniform("eyePos");
//		
//		addUniform("directionalLight.base.color");
//		addUniform("directionalLight.base.intensity");
//		addUniform("directionalLight.direction");
//		
//		for (int i = 0; i < MAX_POINT_LIGHTS; i++) {
//			addUniform("pointLights[" + i + "].base.color");
//			addUniform("pointLights[" + i + "].base.intensity");
//			addUniform("pointLights[" + i + "].atten.constant");
//			addUniform("pointLights[" + i + "].atten.linear");
//			addUniform("pointLights[" + i + "].atten.exponent");
//			addUniform("pointLights[" + i + "].position");
//			addUniform("pointLights[" + i + "].range");
//		}
//		
//		for (int i = 0; i < MAX_SPOT_LIGHTS; i++) {
//			addUniform("spotLights[" + i + "].pointLight.base.color");
//			addUniform("spotLights[" + i + "].pointLight.base.intensity");
//			addUniform("spotLights[" + i + "].pointLight.atten.constant");
//			addUniform("spotLights[" + i + "].pointLight.atten.linear");
//			addUniform("spotLights[" + i + "].pointLight.atten.exponent");
//			addUniform("spotLights[" + i + "].pointLight.position");
//			addUniform("spotLights[" + i + "].pointLight.range");
//			addUniform("spotLights[" + i + "].direction");
//			addUniform("spotLights[" + i + "].cuttoff");
//		}
//		
//	}
//	
//	public void updateUniforms(Transform transform, Material material){
//		
//		Matrix4f worldMatrix = transform.getTransformation();
//		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
//		material.getTexture().bind();
//		
//		setUniform("transformProjected", projectedMatrix);
//		setUniform("transform", worldMatrix);
//		
//		setUniform("baseColor", material.getColor());
//		setUniform("ambientLight", ambientLight);
//		setUniform("directionalLight", directionalLight);
//		
//		setUniformf("specularIntensity", material.getSpecularIntensity());
//		setUniformf("specularPower", material.getSpecularPower());
//		
//		setUniform("eyePos", getRenderingEngine().getMainCamera().getPos());
//		
//		for (int i = 0; i < pointLights.length; i++) {
//			setUniform("pointLights[" + i + "]", pointLights[i]);
//		}
//		
//		for (int i = 0; i < spotLights.length; i++) {
//			setUniform("spotLights[" + i + "]", spotLights[i]);
//		}
//	}
//	
//	
//	public static void setPointLights(PointLight[] pointLights) {
//		
//		if(pointLights.length > MAX_POINT_LIGHTS){
//			System.err.println("You passed in too many Point Lights");
//			new Exception().printStackTrace();
//			System.exit(1);
//		}
//		PhongShader.pointLights = pointLights;
//	}
//	
//	public static void setSpotLights(SpotLight[] spotLights) {
//		
//		if(spotLights.length > MAX_SPOT_LIGHTS){
//			System.err.println("You passed in too many Spot Lights");
//			new Exception().printStackTrace();
//			System.exit(1);
//		}
//		PhongShader.spotLights = spotLights;
//	}
//	
//	public static Vector3f getAmbientLight() {
//		return ambientLight;
//	}
//	
//	public static void setAmbientLight(Vector3f ambientLight) {
//		PhongShader.ambientLight = ambientLight;
//	}
//	
//	
//	public static void setDirectionalLight(DirectionalLight directionalLight) {
//		PhongShader.directionalLight = directionalLight;
//	}
//	
//	public void setUniform(String uniformName , BaseLight baseLight)
//	{
//		setUniform(uniformName + ".color", baseLight.getColor());
//		setUniformf(uniformName + ".intensity", baseLight.getIntensity());
//	}
//	
//	public void setUniform(String uniformName , DirectionalLight directionalLight)
//	{
//		setUniform(uniformName + ".base", directionalLight.getBase());
//		setUniform(uniformName + ".direction", directionalLight.getDirection());
//		
//	}
//
//	public void setUniform(String uniformName , PointLight pointLight){
//		setUniform(uniformName + ".base", pointLight.getBaseLight());
//		setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getConstant());
//		setUniformf(uniformName + ".atten.linear", pointLight.getAtten().getLinear());
//		setUniformf(uniformName + ".atten.exponent", pointLight.getAtten().getExponent());
//		setUniform(uniformName + ".position", pointLight.getPosition());
//		setUniformf(uniformName + ".range", pointLight.getRange());
//	}
//	
//	public void setUniform(String uniformName , SpotLight spotLight){
//		setUniform(uniformName + ".pointLight", spotLight.getPointLight());
//		setUniform(uniformName + ".direction", spotLight.getDirection());
//		setUniformf(uniformName + ".cuttoff", spotLight.getCuttoff());
//	}
//}
