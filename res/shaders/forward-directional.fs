#version 330
#include "lighting.glh"


in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor;

uniform sampler2D diffuse;
uniform DirectionalLight R_directionalLight;
uniform float tiling;


void main()
{
	vec4 light = calcDirectionalLight(R_directionalLight,normalize(normal0), worldPos0);
	
	if(terrain == 1){
	
		fragColor = terrainLight(diffuse, texCoord0) * light;
				
	}
	else{
		vec4 texColor;
		if(tiling > 0.0){
			vec2 texCoords = texCoord0 * tiling;
			texColor = texture(diffuse, texCoords);
		}
		else
			texColor = texture(diffuse, texCoord0);
			
		if(texColor.a < 0.5)
			discard;
		fragColor = texColor * light;
		
	}	
}