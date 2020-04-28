package comp3170.ass1.sceneobjects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.Shader;

public class Tree extends SceneObject {

	private float[] leafVertices = new float[] {
			 0.0f,	2.0f,
			-0.5f,	1.0f,
			 0.5f,	1.0f,
			 
			 0.0f,	1.5f,
			-0.6f,	0.5f,
			 0.6f,	0.5f,
			 
			 0.0f,	1.0f,
			-0.7f,	0.0f,
			 0.7f,	0.0f,
	};
;
	private int leafVertexBuffer;

	private float[] stumpVertices = new float[] {
			// Left
			-0.2f,	-0.0f, // Bottom-left
			 0.2f,	-0.0f, // Bottom-right
			 0.2f,	-1.0f, // Top-right

			 // Right
			 0.2f,	-1.0f, //Top-right
			-0.2f,  -1.0f, //Top-left
			-0.2f,  -0.0f //Bottom-left
	};
	
	private int stumpVertexBuffer;
	
	private float[] leafColour = {0.0f, 0.40f, 0.50f}; // green

	private float[] stumpColour = {0.50f, 0.50f, 0}; // brown

	
	public Tree(Shader shader) {		
	    this.leafVertexBuffer = shader.createBuffer(this.leafVertices);
	    this.stumpVertexBuffer = shader.createBuffer(this.stumpVertices);    
	}
	
	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		shader.setAttribute("a_position", this.leafVertexBuffer);	    
		shader.setUniform("u_colour", this.leafColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.leafVertices.length / 2);           	

		shader.setAttribute("a_position", this.stumpVertexBuffer);	    
		shader.setUniform("u_colour", this.stumpColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.stumpVertices.length / 2);           	

	}
	
	
}
