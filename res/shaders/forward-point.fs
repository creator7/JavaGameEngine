#version 330
#include "lighting.glh"

in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor;

uniform sampler2D diffuse;
uniform PointLight R_pointLight;
uniform float tiling;


void main()
{
	vec4 light = calcPointLight(R_pointLight,normalize(normal0), worldPos0);
	
	if(terrain == 1){	
		fragColor = terrainLight(diffuse, texCoord0) * light;
	}
	else{
		if(tiling > 0)
			fragColor = texture2D(diffuse, texCoord0.xy * tiling) * light;
		else
			fragColor = texture2D(diffuse, texCoord0.xy) * light;
	}
}