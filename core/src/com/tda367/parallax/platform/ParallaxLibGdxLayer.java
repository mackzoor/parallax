package com.tda367.parallax.platform;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.utils.UBJsonReader;
import com.tda367.parallax.parallaxCore.Agelion;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.parallaxCore.Parallax;
import javafx.scene.input.KeyCode;

public class ParallaxLibGdxLayer implements ApplicationListener, InputProcessor {
	SpriteBatch batch;

	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private Model model;
	private ModelInstance modelInstance;
	private Environment environment;
	private boolean screenShot = false;
	private FrameBuffer frameBuffer;
	private Texture texture = null;
	private TextureRegion textureRegion;
	private SpriteBatch spriteBatch;

	private Player player;

	private Parallax parallaxGame;
	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);

		// Initiate game with space craft "Agelion"
		this.player = new Player(new Agelion());
		this.parallaxGame = new Parallax(player);

		// Create camera sized to screens width/height with Field of View of 75 degrees
		camera = new PerspectiveCamera(
				parallaxGame.getCamera().getFov(),
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		// Move the camera 5 units behind the ship along the z-axis and look at the origin

		// Near and Far (plane) represent the minimum and maximum ranges of the camera in, um, units
		camera.position.set(
				parallaxGame.getCamera().getPos().getX(),
				parallaxGame.getCamera().getPos().getZ(),
				parallaxGame.getCamera().getPos().getY()
		);

		camera.near = 0.1f;
		camera.far = 300.0f;

		// A ModelBatch is like a SpriteBatch, just for models.  Use it to batch up geometry for OpenGL
		modelBatch = new ModelBatch();

		// Model loader needs a binary json reader to decode
		UBJsonReader jsonReader = new UBJsonReader();
		// Create a model loader passing in our json reader
		G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
		// Now load the model by name
		// Note, the model (g3db file ) and textures need to be added to the assets folder of the Android proj
		model = modelLoader.loadModel(Gdx.files.getFileHandle("ship.g3db", Files.FileType.Internal));

		// Now create an instance.  Instance holds the positioning data, etc of an instance of your model
		modelInstance = new ModelInstance(model);

		//DEBUG ONLY
		modelInstance.transform.translate(0, 0, 0);

		// Finally we want some light, or we wont see our color.  The environment gets passed in during
		// the rendering process.  Create one, then create an Ambient ( non-positioned, non-directional ) light.
		environment = new Environment();
//        Texture texture1 = new Texture("bg.jpg");
//        environment.set(new TextureAttribute(TextureAttribute.createAmbient(texture1)));
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1.0f));


		frameBuffer = new FrameBuffer(Pixmap.Format.RGB888,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);
//        Gdx.input.setInputProcessor(this);

		spriteBatch = new SpriteBatch();

		batch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		parallaxGame.update((int)(Gdx.graphics.getDeltaTime() * 1000));

		camera.update();

		camera.position.set(
				parallaxGame.getCamera().getPos().getX(),
				parallaxGame.getCamera().getPos().getZ(),
				parallaxGame.getCamera().getPos().getY()*-1
		);


		modelInstance.transform.setToTranslation(
				player.getSpaceCraft().getPos().getX(),
				player.getSpaceCraft().getPos().getZ(),
				player.getSpaceCraft().getPos().getY()*-1
		);


		// You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		// If the user requested a screenshot, we need to call begin on our framebuffer
		// This redirects output to the framebuffer instead of the screen.
		if(screenShot)
			frameBuffer.begin();

		// Like spriteBatch, just with models!  pass in the box Instance and the environment
		modelBatch.begin(camera);
		modelBatch.render(modelInstance, environment);
		modelBatch.end();

		// Now tell OpenGL that we are done sending graphics to the framebuffer
		if(screenShot)
		{
			frameBuffer.end();
			// get the graphics rendered to the framebuffer as a texture
			texture = frameBuffer.getColorBufferTexture();
			// welcome to the wonderful world of different coordinate systems!
			// simply put, the framebuffer is upside down to normal textures, so we have to flip it
			// Use a TextureRegion to do so
			textureRegion = new TextureRegion(texture);
			// and.... FLIP!  V (vertical) only
			textureRegion.flip(false, true);
		}

		// In the case that we have a texture object to actually draw, we do so
		// using the old familiar SpriteBatch to do so.
		if(texture != null)
		{
			spriteBatch.begin();
			spriteBatch.draw(textureRegion,0,0);
			spriteBatch.end();
			screenShot = false;
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		model.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		float panSpeed = 5;
		spaceShipTurn(keycode, panSpeed);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		float panSpeed = 0;
		spaceShipTurn(keycode, panSpeed);
		return false;
	}

	private void spaceShipTurn(int keycode, float panSpeed){
		if (keycode == Input.Keys.W){
			player.getSpaceCraft().setPanYVelocity(panSpeed);
		} else if (keycode == Input.Keys.A){
			player.getSpaceCraft().setPanXVelocity(-panSpeed);
		} else if (keycode == Input.Keys.S){
			player.getSpaceCraft().setPanYVelocity(-panSpeed);
		} else if (keycode == Input.Keys.D){
			player.getSpaceCraft().setPanXVelocity(panSpeed);
		}
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
