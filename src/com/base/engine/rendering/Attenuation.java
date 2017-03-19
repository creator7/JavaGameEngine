package com.base.engine.rendering;

import com.base.engine.core.Vector3f;

public class Attenuation extends Vector3f{
	
	public Attenuation(float constant, float linear, float exponent) {
		super(constant, linear, exponent);
	}

	public float getConstant()
	{
		return x;
	}

	public float getLinear()
	{
		return y;
	}

	public float getExponent()
	{
		return z;
	}

}
