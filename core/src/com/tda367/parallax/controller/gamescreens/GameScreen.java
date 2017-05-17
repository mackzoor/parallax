package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.gamecontrollers.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;

public class GameScreen implements Screen {

    private Game game;

    //Almost everything is copied straight of from PlayState. Should be split up in the future
    private Player player;
    private Parallax parallaxGame;
    private Renderer3D renderer3D;
    private GameController controller;
    private Sound sound;
    private AudioQueue audioQueue;
    private CollisionCalculator collisionCalculator;
    private ParallaxView parallaxView;

    public GameScreen(Game game, Player player) {
        this.game = game;

        audioQueue = AudioQueue.getInstance();
        // Initiate game with space craft "Agelion"
        renderer3D = new Renderer3D(0, 0, 0);
        this.player = player;
        sound = new Sound();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (player.getSpaceCraft().getHealth() > 1) {
//        System.out.println("Fps: " + 1/delta);
            //Updates Parallax game logic
            parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
            collisionCalculator.run();
            parallaxView.render();
            DeviceManager.getDevice().update();
//            System.out.println(player.getSpaceCraft().getHealth());
        } else {
            dispose();
            GameStateManager.setGameOverScreen(game, player);
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer3D.setWidth(width);
        renderer3D.setHeight(height);
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
        CollisionManager.getInstance().getCollidables().clear();
        Controllers.clearListeners();
        //TODO I think this below was important.
        audioQueue.clearAllActiveMusic();
        collisionCalculator.dispose();
        CollisionManager.getInstance().getObservers().clear();
    }


    public void newGame() {
        player.addSpaceCraft(new Agelion(15));
        parallaxGame = new Parallax(player);
        parallaxView = new ParallaxView(parallaxGame);
        collisionCalculator = new CollisionCalculator();
        controller = new GameController(parallaxGame, DeviceManager.getDevice());
        renderer3D = Renderer3D.initialize(
                new PerspectiveCamera(
                        parallaxGame.getCamera().getFov(),
                        Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight()
                )
        );
    }
}
