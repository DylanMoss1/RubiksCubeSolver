#version 140

in vec3 wc_frag_normal;        	// fragment normal in world coordinates (wc_)
in vec2 frag_texcoord;			// texture UV coordinates
in vec3 wc_frag_pos;			// fragment position in world coordinates

out vec3 color;			        // pixel colour

uniform sampler2D tex;  		  // 2D texture sampler
uniform samplerCube skybox;		  // Cubemap texture used for reflections
uniform vec3 wc_camera_position;  // Position of the camera in world coordinates

// Tone mapping and display encoding combined
vec3 tonemap(vec3 linearRGB)
{
    float L_white = 0.7; // Controls the brightness of the image

    float inverseGamma = 1./2.2;
    return pow(linearRGB/L_white, vec3(inverseGamma)); // Display encoding - a gamma
}



void main()
{
	vec3 linear_color = vec3(0, 0, 0);
	// TODO: Calculate colour using Phong illumination model

    vec3 skybox_color;

    vec3 light_source = vec3(2.0,2.0,2.0);

    vec3 light_ray = normalize(wc_frag_pos - light_source);

    vec3 reflected_ray = normalize(reflect(light_ray, wc_frag_normal));

    skybox_color = vec3(texture(skybox,reflect(normalize(wc_frag_pos - wc_camera_position),normalize(wc_frag_normal))));


    float kd = 0.5;
    float ks = 0.2;

    float a = 0.5;

    vec3 Ia = vec3(1.0,1.0,1.0);
    vec3 Cdiff = vec3(texture(tex,frag_texcoord));
    vec3 Cspec = vec3(1.0,1.0,1.0);
    vec3 Ii = vec3(1.0,1.0,1.0);

    float diffMax = max(0,dot(wc_frag_normal,(-1 * light_ray)));
    float specMax = max(0,dot(reflected_ray,(normalize(wc_camera_position))));

    linear_color = (Cdiff * Ia) + (Cdiff * kd * Ii * diffMax) + (Cspec * ks * Ii * pow(specMax,a));

    linear_color = (linear_color * 0.5) + skybox_color;

	// linear_color = ambient + diffuse + specular
	// TODO: Sample the texture and replace diffuse surface colour (C_diff) with texel value


	color = tonemap(linear_color);
}

