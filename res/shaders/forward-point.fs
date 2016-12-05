#version 330
#include "lighting.glh"

in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;

uniform sampler2D diffuse;
uniform PointLight pointLight;


void main()
{
	gl_FragColor = texture(diffuse, texCoord0.xy) * calcPointLight(pointLight,normalize(normal0), worldPos0);	
}