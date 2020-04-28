package comp3170.ass1.sceneobjects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.Shader;

public class Helicopter extends SceneObject {
	
	private float[] headVertices = new float[] {
		     0.0f,  2.5f, // Mid
			-0.5f,	2.0f, // Left
			 0.5f,	2.0f, // Right
	};

	private float[] bodyVertices = new float[] {
			// Left
		    -0.5f,	0.0f, // Bottom-left
			 0.5f,	0.0f, // Bottom-right
			 0.5f,	2.0f, // Top-right

			 // Right
			 0.5f,	2.0f, // Top-right
			-0.5f,  2.0f, // Top-left
			-0.5f,  0.0f // Bottom-left
	};
	
	private float[] tailVertices = new float[] {		
		     0.0f, -0.5f, // Mid
			-0.5f,	0.0f, // Left
			 0.5f,	0.0f, // Right
	};
	
	private float[] topRotorVertices = new float[] {
			// Mid
			 0.0f,	2.0f, // Mid
			-0.1f,	3.0f, // Left
			 0.1f,	3.0f, // Right
			 
			 // Left
			 0.0f,	2.0f, // Mid
			-1.0f,	1.0f, // Left
			-1.0f,	1.2f, // Right
			 
			 // Right
			 0.0f,	2.0f, // Mid
			 1.0f,	1.0f, // Left
			 1.0f,	1.2f, // Right
	};
	
	private float[] botRotorVertices = new float[] {
			// Mid
			 0.0f,	0.0f, // Mid
			-0.1f,	1.0f, // Left
			 0.1f,	1.0f, // Right
			 
			 // Left
			 0.0f,	 0.0f, // Mid
			-1.0f,	-1.0f, // Left
			-1.0f,	-1.2f, // Right
			 
			 // Right
			 0.0f,	 0.0f, // Mid
			 1.0f,	-1.0f, // Left
			 1.0f,	-1.2f, // Right
	};
	

	
;

	private int headVertexBuffer;
	private int bodyVertexBuffer;
	private int tailVertexBuffer;
	private int topRotorVertexBuffer;
	private int botRotorVertexBuffer;

	private float[] headColour = {0.3f, 0.3f, 0.3f}; // dark grey
	private float[] bodyColour = {0.4f, 0.4f, 0.4f}; // light grey
	private float[] tailColour = {0.3f, 0.3f, 0.3f}; // dark grey
	private float[] topRotorColour = {0.0f, 0.0f, 0.0f}; // black
	private float[] botRotorColour = {0.0f, 0.0f, 0.0f}; // black
	
	public Helicopter (Shader shader) {		
	    this.headVertexBuffer = shader.createBuffer(this.headVertices);
	    this.bodyVertexBuffer = shader.createBuffer(this.bodyVertices);
	    this.tailVertexBuffer = shader.createBuffer(this.tailVertices);    
	    this.topRotorVertexBuffer = shader.createBuffer(this.topRotorVertices);    
	    this.botRotorVertexBuffer = shader.createBuffer(this.botRotorVertices);    
	}
	
	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();
		
		shader.setAttribute("a_position", this.headVertexBuffer);	    
		shader.setUniform("u_colour", this.headColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.headVertices.length / 2);    

		shader.setAttribute("a_position", this.bodyVertexBuffer);	    
		shader.setUniform("u_colour", this.bodyColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.bodyVertices.length / 2);           	

		shader.setAttribute("a_position", this.tailVertexBuffer);	    
		shader.setUniform("u_colour", this.tailColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.tailVertices.length / 2);           
        
		shader.setAttribute("a_position", this.topRotorVertexBuffer);	    
		shader.setUniform("u_colour", this.topRotorColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.topRotorVertices.length / 2);  

		shader.setAttribute("a_position", this.botRotorVertexBuffer);	    
		shader.setUniform("u_colour", this.botRotorColour);	    
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.botRotorVertices.length / 2);  
        
      
	}	
}
