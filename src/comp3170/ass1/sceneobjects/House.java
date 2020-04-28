package comp3170.ass1.sceneobjects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.Shader;

public class House extends SceneObject {

	private float[] vertices = new float[] {
			-1.0f,	0.0f,
			 1.0f,	0.0f,
			 1.0f,	2.0f,

			 1.0f,	2.0f,
			-1.0f,  2.0f,
			-1.0f,  0.0f
	};
;
	private int vertexBuffer;

	private float[] roofVertices = new float[] {
			 0.0f,	4.0f,
			-1.6f,	2.0f,
			 1.6f,	2.0f,
	};
	
	private int roofVertexBuffer;

	private float[] colour = {0.43f, 0.32f, 0}; // brown
	private float[] roofColour = {0.43f, 0.0f, 0}; // dark red
	
	public House(Shader shader) {		
	    this.vertexBuffer = shader.createBuffer(this.vertices);
	    this.roofVertexBuffer = shader.createBuffer(this.roofVertices);    
	}
	
	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		shader.setAttribute("a_position", this.vertexBuffer);	    
		shader.setUniform("u_colour", this.colour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.vertices.length / 2);           	

		shader.setAttribute("a_position", this.roofVertexBuffer);	    
		shader.setUniform("u_colour", this.roofColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.roofVertices.length / 2);           	

	}
	
	
}
