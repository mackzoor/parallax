package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.PerspectiveCamera;
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
    private PerspectiveCamera camera;
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
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Updates Parallax game logic
        parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));

        camera.position.set(
                parallaxGame.getCamera().getPos().getX(),
                parallaxGame.getCamera().getPos().getZ(),
                parallaxGame.getCamera().getPos().getY()*-1
        );
        camera.update();
        collisionCalculator.run();
        renderer.renderAll();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
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
