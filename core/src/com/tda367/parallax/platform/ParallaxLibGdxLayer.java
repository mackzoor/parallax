package com.tda367.parallax.platform;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.parallaxCore.Parallax;
import javax.vecmath.Vector2f;

public class ParallaxLibGdxLayer implements ApplicationListener, InputProcessor {
	SpriteBatch batch;

	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private boolean screenShot = false;
	private FrameBuffer frameBuffer;
	private Texture texture = null;
	private Player player;
	private Parallax parallaxGame;
	private Renderer renderer;

	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);

		Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");

		// Initiate game with space craft "Agelion"
		this.player = new Player(new Agelion(10));
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


		frameBuffer = new FrameBuffer(Pixmap.Format.RGB888,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);

		renderer = new Renderer(camera);

		batch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}

	@Override
	public void render () {

		//Updates Parallax game logic
		parallaxGame.update((int)(Gdx.graphics.getDeltaTime() * 1000));

		//Updates camera
		camera.position.set(
				parallaxGame.getCamera().getPos().getX(),
				parallaxGame.getCamera().getPos().getZ(),
				parallaxGame.getCamera().getPos().getY()*-1
		);

		camera.update();

		renderer.renderAll(frameBuffer);


	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
//		modelBatch.dispose();
//		model.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		float panSpeed = 5;
		spaceShipTurn(keycode, panSpeed);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		float panSpeed = -5;
		spaceShipTurn(keycode, panSpeed);
		return false;
	}

	private void spaceShipTurn(int keycode, float panSpeed){
		if (keycode == Input.Keys.W || keycode == Input.Keys.UP){
			player.getSpaceCraft().addPanVelocity(new Vector2f(0,panSpeed));
		} else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT){
			player.getSpaceCraft().addPanVelocity(new Vector2f(-panSpeed,0));
		} else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN){
			player.getSpaceCraft().addPanVelocity(new Vector2f(0,-panSpeed));
		} else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
			player.getSpaceCraft().addPanVelocity(new Vector2f(panSpeed,0));
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
