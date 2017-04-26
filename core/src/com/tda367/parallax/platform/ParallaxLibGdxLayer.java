package com.tda367.parallax.platform;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.parallaxCore.Parallax;

public class ParallaxLibGdxLayer implements ApplicationListener {

	private PerspectiveCamera camera;
	private Player player;
	private Parallax parallaxGame;
	private Renderer renderer;
	private ParallaxLibGDXController controller;
	private Sound sound;

	@Override
	public void create () {

		Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");

		// Initiate game with space craft "Agelion"
		this.player = new Player(new Agelion(10));
		this.parallaxGame = new Parallax(player);
		controller = new ParallaxLibGDXController(parallaxGame, new DesktopMode());

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

		renderer = new Renderer(camera);
		sound = new Sound();
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
                parallaxGame.getCamera().getPos().getY() * -1
        );

		camera.update();

        renderer.renderAll();

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            //controller.drawTouchpad();

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
//		modelBatch.dispose();
//		model.dispose();
	}
}
