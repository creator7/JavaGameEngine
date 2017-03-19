#version 330

layout( location = 0 ) in vec2 positions;
out vec2 textureCoords;

uniform mat4 T_model;

void main(void){
	
	gl_Position =  T_model * vec4(positions, 0.0, 1.0);
	textureCoords = vec2((positions.x+1.0)/2.0, 1 - (positions.y+1.0)/2.0);
	
}