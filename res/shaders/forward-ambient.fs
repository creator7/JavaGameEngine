#version 330

in vec2 texCoord0;
out vec4 fragColor;

uniform vec3 R_ambient;
uniform sampler2D diffuse;
uniform int terrain;
uniform sampler2D rTex;
uniform sampler2D gTex;
uniform sampler2D bTex;
uniform sampler2D blendMap;
uniform float tiling;

void main()
{
	vec4 light = vec4(R_ambient,1);

	if(terrain == 1){
		vec4 blendMapColor = texture(blendMap, texCoord0);
		float backTextureAmount = 1 - (blendMapColor.r + blendMapColor.g + blendMapColor.b);
		vec2 tiledCoords = texCoord0 * 40.0;
		vec4 backgroundTexColor = texture(diffuse,tiledCoords) * backTextureAmount;
		vec4 rTexColor = texture(rTex, tiledCoords) * blendMapColor.r;
		vec4 gTexColor = texture(gTex, tiledCoords) * blendMapColor.g;
		vec4 bTexColor = texture(bTex, tiledCoords) * blendMapColor.b;
		
		vec4 totalColor = backgroundTexColor + rTexColor + gTexColor + bTexColor;
		fragColor = totalColor * light;
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