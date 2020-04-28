package comp3170.ass1;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import comp3170.GLException;
import comp3170.InputManager;
import comp3170.Shader;
import comp3170.ass1.sceneobjects.Helicopter;
import comp3170.ass1.sceneobjects.Helipad;
import comp3170.ass1.sceneobjects.House;
import comp3170.ass1.sceneobjects.River;
import comp3170.ass1.sceneobjects.SceneObject;
import comp3170.ass1.sceneobjects.Terrain;
import comp3170.ass1.sceneobjects.Tree;

public class Assignment1 extends JFrame implements GLEventListener {

	private GLCanvas canvas;
	private Shader shader;

	final private File DIRECTORY = new File("src/comp3170/ass1");
	final private String VERTEX_SHADER = "vertex.glsl";
	final private String FRAGMENT_SHADER = "fragment.glsl";

	private InputManager input;

	private SceneObject root;

	private Matrix4f worldMatrix;

	private Animator animator;

	public Assignment1() {
		super("COMP3170 Assignment 1");

		// create an OpenGL 4 canvas and add this as a listener

		GLProfile profile = GLProfile.get(GLProfile.GL4);
		GLCapabilities capabilities = new GLCapabilities(profile);
		this.canvas = new GLCanvas(capabilities);
		this.canvas.addGLEventListener(this);
		this.add(canvas);

		// create an input manager to listen for keypresses and mouse events

		this.input = new InputManager();
		input.addListener(this);
		input.addListener(this.canvas);

		// Add an animator to regularly redraw the screen

		this.animator = new Animator(canvas);
		this.animator.start();

		// set up the JFrame

		this.setSize(1000, 1000);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	@Override
	/**
	 * Initialise the GLCanvas
	 */
	public void init(GLAutoDrawable drawable) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		// Compile the shader
		try {
			File vertexShader = new File(DIRECTORY, VERTEX_SHADER);
			File fragementShader = new File(DIRECTORY, FRAGMENT_SHADER);
			this.shader = new Shader(vertexShader, fragementShader);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (GLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		

		// allocate matrices

		this.worldMatrix = new Matrix4f();

		// construct objects and attach to the scene-graph
		this.root = new SceneObject();

		// Create the objects
		House house1 = new House(this.shader);
		house1.localMatrix.translate(0.3f, -0.3f, 0);
		// house1.localMatrix.rotateZ((float) Math.PI / 6);
		house1.localMatrix.scale(0.1f, 0.1f, 1);
		house1.setParent(this.root);
		
		House house2 = new House(this.shader);
		house2.localMatrix.translate(-0.3f, 0.5f, 0);
		house2.localMatrix.scale(0.1f, 0.1f, 1);
		house2.setParent(this.root);
		
		House house3 = new House(this.shader);
		house3.localMatrix.translate(-0.2f, -0.8f, 0);
		house3.localMatrix.scale(0.1f, 0.1f, 1);
		house3.setParent(this.root);
		
		Helicopter helicopter1 = new Helicopter(this.shader);
		helicopter1.localMatrix.translate(-0.3f, -0.2f, 0);
		helicopter1.localMatrix.rotateZ((float) Math.PI / 6);
		helicopter1.localMatrix.scale(0.1f, 0.1f, 1);
		helicopter1.setParent(this.root);

		Tree tree1 = new Tree(this.shader);
		tree1.localMatrix.translate(0.6f, 0.6f, 0);
		tree1.localMatrix.scale(0.1f, 0.1f, 1);
		tree1.setParent(this.root);

		Tree tree2 = new Tree(this.shader);
		tree2.localMatrix.translate(0.3f, -0.6f, 0);
		tree2.localMatrix.scale(0.1f, 0.1f, 1);
		tree2.setParent(this.root);
		
		Tree tree3 = new Tree(this.shader);
		tree3.localMatrix.translate(-0.6f, -0.8f, 0);
		tree3.localMatrix.scale(0.1f, 0.1f, 1);
		tree3.setParent(this.root);

		Helipad helipad1 = new Helipad(this.shader);
		helipad1.localMatrix.translate(0.2f, 0.45f, 0);
		helipad1.localMatrix.scale(0.1f, 0.1f, 1);
		helipad1.setParent(this.root);
		
		River river1 = new River(this.shader);
		river1.localMatrix.translate(-0.3f, 0.2f, 0);
		river1.localMatrix.scale(0.1f, 0.1f, 1);
		river1.setParent(this.root);
	}

	@Override
	/**
	 * Called when the canvas is redrawn
	 */
	public void display(GLAutoDrawable drawable) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		// set the background colour to dark green
		gl.glClearColor(0.1f, 0.6f, 0.1f, 1.0f);
		gl.glClear(GL_COLOR_BUFFER_BIT);

		this.shader.enable();

		this.worldMatrix.identity();
		this.root.draw(shader, worldMatrix);

	}

	@Override
	/**
	 * Called when the canvas is resized
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

	}

	@Override
	/**
	 * Called when we dispose of the canvas
	 */
	public void dispose(GLAutoDrawable drawable) {

	}

	public static void main(String[] args) throws IOException, GLException {
		new Assignment1();
	}
}
