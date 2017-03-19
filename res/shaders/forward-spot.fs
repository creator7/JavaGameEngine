#version 120
#include "lighting.glh"

in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor;

uniform sampler2D diffuse;
uniform SpotLight R_spotLight;
uniform float tiling;



void main()
{
	vec4 light = calcSpotLight(R_spotLight, normalize(normal0), worldPos0);
	
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
