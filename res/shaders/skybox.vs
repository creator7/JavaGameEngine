#version 330

layout( location = 0 ) in vec3 position;
out vec3 textureCoords;

uniform mat4 T_MVP;
uniform mat4 T_model;

void main(void){
	
	gl_Position =  T_MVP * T_model * vec4(position, 1.0);
	textureCoords = position;
	
}
