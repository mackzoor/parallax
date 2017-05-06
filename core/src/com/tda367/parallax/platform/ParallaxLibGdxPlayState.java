package com.tda367.parallax.platform;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.GameController;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.coreabstraction.SoundManager;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.view.Renderer;
import com.tda367.parallax.view.Sound;

public class ParallaxLibGdxPlayState implements ApplicationListener {
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private GameController controller;
    private Device device;
    private Sound sound;
    GameStateManager gameStateManager;
    SoundManager soundManager;

    public ParallaxLibGdxPlayState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.device = DeviceManager.getGameModeState(this);
        soundManager = SoundManager.getInstance();
        this.collisionCalculator = new CollisionCalculator();
        CollisionManager.getInstance().addCollisionCalculator(collisionCalculator);
        // Initiate game with space craft "Agelion"
        this.player = new Player();
        this.player.addSpaceCraft(new Agelion(10));
        this.parallaxGame = new Parallax(player);
        controller = new GameController(parallaxGame, device);

        renderer = new Renderer(
                parallaxGame.getCamera().getFov(),
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );

        sound = new Sound();
    }

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
        renderer.setWidth(width);
        renderer.setHeight(height);
    }

    @Override
    public synchronized void render() {
        parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
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
