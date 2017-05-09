package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.coreabstraction.SoundManager;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.view.Renderer;
import com.tda367.parallax.view.Sound;

class GameScreen implements Screen {

    private Game game;

    //Almost everything is copied straight of from PlayState. Should be split up in the future
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private GameController controller;
    private Sound sound;
    private SoundManager soundManager;
    private CollisionCalculator collisionCalculator;

    GameScreen(Game game) {
        this.game = game;

        soundManager = SoundManager.getInstance();
        CollisionManager.getInstance().addCollisionCalculator(collisionCalculator = new CollisionCalculator());

        // Initiate game with space craft "Agelion"
        this.player = new Player();
        this.player.addSpaceCraft(new Agelion(10));
        this.parallaxGame = new Parallax(player);
        controller = new GameController(parallaxGame, DeviceManager.getGameModeState(game));

        renderer = new Renderer(
                parallaxGame.getCamera().getFov(),
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );
        sound = new Sound();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Updates Parallax game logic
        parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
        collisionCalculator.run();
        renderer.renderAll();
        DeviceManager.getDevice().update();
    }

    @Override
    public void resize(int width, int height) {
        renderer.setWidth(width);
        renderer.setHeight(height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        Controllers.clearListeners();
        parallaxGame.getRenderManager().getRenderables().clear();
        soundManager.stopActiveMusic("sounds/music/track.mp3");
    }
}
