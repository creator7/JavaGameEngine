package com.base.engine.core;

public class Vector3f 
{
	public float x;
	public float y;
	public float z;
	
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x * x + y * y + z * z);
	}

	public float max()
	{
		return Math.max(x, Math.max(y, z));
	}

	public float dot(Vector3f r)
	{
		return x * r.x + y * r.y + z * r.z;
	}
	
	public Vector3f cross(Vector3f r)
	{
		float x_ = y * r.z - z * r.y;
		float y_ = z * r.x - x * r.z;
		float z_ = x * r.y - y * r.x;
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f normalized()
	{
		float length = length();
		
		return new Vector3f(x / length, y / length, z / length);
	}
	
	public Vector3f rotate(Vector3f axis, float angle)
	{
		return this.rotate(new Quaternion(axis, angle));
	}
	
	public Vector3f rotate(Quaternion rotation)
	{
		Quaternion conjugate = rotation.conjugate();

		Quaternion w = rotation.mul(this).mul(conjugate);

		return new Vector3f(w.x, w.y, w.z);
	}

	public Vector3f lerp(Vector3f dest, float lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector3f add(Vector3f r)
	{
		return new Vector3f(x + r.x, y + r.y, z + r.z);
	}
	
	public Vector3f add(float r)
	{
		return new Vector3f(x + r, y + r, z + r);
	}
	
	public Vector3f sub(Vector3f r)
	{
		return new Vector3f(x - r.x, y - r.y, z - r.z);
	}
	
	public Vector3f sub(float r)
	{
		return new Vector3f(x - r, y - r, z - r);
	}
	
	public Vector3f mul(Vector3f r)
	{
		return new Vector3f(x * r.x, y * r.y, z * r.z);
	}
	
	public Vector3f mul(float r)
	{
		return new Vector3f(x * r, y * r, z * r);
	}
	
	public Vector3f div(Vector3f r)
	{
		return new Vector3f(x / r.x, y / r.y, z / r.z);
	}
	
	public Vector3f div(float r)
	{
		return new Vector3f(x / r, y / r, z / r);
	}
	
	public Vector3f abs()
	{
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public String toString()
	{
		return "(" + x + " " + y + " " + z + ")";
	}

	public Vector2f getXY() { return new Vector2f(x, y); }
	public Vector2f getYZ() { return new Vector2f(y, z); }
	public Vector2f getZX() { return new Vector2f(z, x); }

	public Vector2f getYX() { return new Vector2f(y, x); }
	public Vector2f getZY() { return new Vector2f(z, y); }
	public Vector2f getXZ() { return new Vector2f(x, z); }

	public Vector3f set(float x, float y, float z) {
		this.x = x; 
		this.y = y; 
		this.z = z;
		
		return this;
	}
	
	public Vector3f set(Vector3f r) { 
		return set(r.x,r.y,r.z);
	}

	public boolean equals(Vector3f r)
	{
		return x == r.x && y == r.y && z == r.z;
	}
	
	public Vector3f max(Vector3f other){
		Vector3f result = new Vector3f(0, 0, 0);
		result.x = (Math.max(this.x, other.x));
		result.y = (Math.max(this.y, other.y));
		result.z = (Math.max(this.z, other.z));
		
		return result;
	}
}