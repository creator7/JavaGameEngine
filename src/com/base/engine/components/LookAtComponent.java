package com.base.engine.components;

import com.base.engine.core.GameObject;
import com.base.engine.core.Quaternion;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class LookAtComponent extends GameComponent{
	
	private RenderingEngine renderingEngine;
	private GameObject object;
	
	public LookAtComponent(GameObject object) {
		this.object = object;
	}

	@Override
	public void update(float delta)
	{
		if(renderingEngine != null)
		{
			Quaternion newRot = getTransform().GetLookAtRotation(object.getTransform().getTransformedPos(),
					getTransform().getRot().getUp());
			//new Vector3f(0, 1, 0));

			//getTransform().setRot(getTransform().getRot().nLerp(newRot, delta * 5.0f, true));
			getTransform().setRot(getTransform().getRot().sLerp(newRot, delta * 5.0f, true));
		}
	}

	@Override
	public void render(Shader shader, RenderingEngine renderingEngine)
	{
		this.renderingEngine = renderingEngine;
	}

}
