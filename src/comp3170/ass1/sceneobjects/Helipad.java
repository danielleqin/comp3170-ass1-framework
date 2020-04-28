package comp3170.ass1.sceneobjects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.Shader;

public class Helipad extends SceneObject {

	private float[] groundVertices = new float[] {
			// Left
		    -1.0f,	0.0f, // Bottom-left
			 1.0f,	0.0f, // Bottom-right
			 1.0f,	2.0f, // Top-right

			 // Right
			 1.0f,	2.0f, // Top-right
			-1.0f,  2.0f, // Top-left
			-1.0f,  0.0f // Bottom-left
	};
;
	private int groundVertexBuffer;

	private float[] hVertices = new float[] {
			// Left Left
		    -0.6f,	0.3f, // Bottom-left
			-0.2f,	0.3f, // Bottom-right
			-0.2f,	1.7f, // Top-right

			 // Left Right
			-0.2f,	1.7f, // Top-right
			-0.6f,  1.7f, // Top-left
			-0.6f,  0.3f, // Bottom-left
			
			// Mid Left
		   -0.3f,	0.80f, // Bottom-left
			0.3f,	0.80f, // Bottom-right
			0.3f,	1.2f, // Top-right

			 // Mid Right
			 0.3f,  1.2f, // Top-right
			-0.3f,  1.2f, // Top-left
			-0.3f,  0.80f, // Bottom-left
			
			
			// Right Left
		    0.6f,	0.3f, // Bottom-left
			0.2f,	0.3f, // Bottom-right
			0.2f,	1.7f, // Top-right

			 // Right Right
			0.2f,  1.7f, // Top-right
			0.6f,  1.7f, // Top-left
			0.6f,  0.3f // Bottom-left

	};
	
	private int hVertexBuffer;

	private float[] groundColour = {0.5f, 0.5f, 0.5f}; // light grey
	private float[] hColour = {0.1f, 0.1f, 0.1f}; // dark grey
	
	public Helipad (Shader shader) {		
	    this.groundVertexBuffer = shader.createBuffer(this.groundVertices);
	    this.hVertexBuffer = shader.createBuffer(this.hVertices);    
	}
	
	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		shader.setAttribute("a_position", this.groundVertexBuffer);	    
		shader.setUniform("u_colour", this.groundColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.groundVertices.length / 2);           	

		shader.setAttribute("a_position", this.hVertexBuffer);	    
		shader.setUniform("u_colour", this.hColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.hVertices.length / 2);           	

	}
	
	
}
