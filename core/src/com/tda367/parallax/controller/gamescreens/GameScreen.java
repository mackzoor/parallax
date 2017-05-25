package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.GameState;
import com.tda367.parallax.controller.GameStateChangeListener;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;

public class GameScreen extends ScreenAdapter {

    private Game game;

    //Almost everything is copied straight of from PlayState. Should be split up in the future
    private Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private Sound sound;
    private AudioQueue audioQueue;
    private CollisionCalculator collisionCalculator;
    private ParallaxView parallaxView;
    private GameStateChangeListener gameStateChangeListener;

    public GameScreen(Player player, GameStateChangeListener gameStateChangeListener) {

        this.audioQueue = AudioQueue.getInstance();
        // Initiate game with space craft "Agelion"
        this.player = player;
        this.gameStateChangeListener = gameStateChangeListener;
        this.sound = new Sound();
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void render(float delta) {
        if (!this.parallaxGame.isGameOver()) {
            this.parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
            this.collisionCalculator.run();
            this.parallaxView.render();
            DeviceManager.getDevice().update();
        } else {
            gameOver();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.parallaxView.setWidth(width);
        this.parallaxView.setHeight(height);
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        Controllers.clearListeners();
        this.audioQueue.clearAllActiveMusic();
        this.collisionCalculator.clear();
        CollisionManager.getInstance().getObservers().clear();
    }

    public void newGame() {
        this.player.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(15));
        this.parallaxGame = new Parallax(this.player);
        this.parallaxView = new ParallaxView(this.parallaxGame, false);
        this.controller = new GameController(this.parallaxGame, this.parallaxView, DeviceManager.getDevice());
    }

    private void gameOver() {
        this.dispose();
        gameStateChangeListener.gameStateChanged(GameState.GAME_OVER_STATE, this.player);
    }
}
