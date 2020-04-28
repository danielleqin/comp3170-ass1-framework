package comp3170.ass1.sceneobjects;

import java.awt.Canvas;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.Shader;

public class River extends SceneObject {

	private float[] riverVertices = new float[] { 
			// Left
		    -10.0f,	0.0f, // Bottom-left
			 15.0f,	0.0f, // Bottom-right
			 15.0f,	2.0f, // Top-right

			 // Right
			 15.0f,	2.0f, // Top-right
			-10.0f,  2.0f, // Top-left
			-10.0f,  0.0f // Bottom-left
	};

	;

	private int riverVertexBuffer;

	private float[] riverColour = { 0.0f, 0.2f, 0.8f }; // blue

	public River(Shader shader) {
		this.riverVertexBuffer = shader.createBuffer(this.riverVertices);

	}

	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		shader.setAttribute("a_position", this.riverVertexBuffer);
		shader.setUniform("u_colour", this.riverColour);
		gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.riverVertices.length / 2);

	}
}
