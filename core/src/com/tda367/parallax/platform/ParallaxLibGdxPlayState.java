package com.tda367.parallax.platform;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.*;
import com.tda367.parallax.controller.GameController;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.CoreAbstraction.SoundManager;
import com.tda367.parallax.model.parallaxCore.Parallax;
import com.tda367.parallax.model.parallaxCore.Player;
import com.tda367.parallax.model.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.view.Renderer;
import com.tda367.parallax.view.Sound;

public class ParallaxLibGdxPlayState implements ApplicationListener {

    private PerspectiveCamera camera;
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private GameController controller;
    private Device device;
    private Sound sound;
    GameStateManager gameStateManager;
    SoundManager soundManager;
    CollisionCalculator collisionCalculator;

    public ParallaxLibGdxPlayState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.device = DeviceManager.getGameModeState(this);
        soundManager = SoundManager.getInstance();
        this.collisionCalculator = new CollisionCalculator();

        // Initiate game with space craft "Agelion"
        this.player = new Player();
        this.player.addSpaceCraft(new Agelion(10));
        this.parallaxGame = new Parallax(player);
        this.parallaxGame.setCollisionCalculator(collisionCalculator);
        controller = new GameController(parallaxGame, device);

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
                parallaxGame.getCamera().getPos().getY() * -1
        );

        renderer = new Renderer(camera);
        sound = new Sound();


    }

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }


    @Override
    public synchronized void render() {

        //Updates Parallax game logic
        parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));

        camera.position.set(
                parallaxGame.getCamera().getPos().getX(),
                parallaxGame.getCamera().getPos().getZ(),
                parallaxGame.getCamera().getPos().getY()*-1
        );
        camera.update();

        renderer.renderAll();
        device.update();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Controllers.clearListeners();
        parallaxGame.getRenderManager().getRenderables().clear();
        soundManager.stopActiveMusic("sounds/music/track.mp3");
    }
}
