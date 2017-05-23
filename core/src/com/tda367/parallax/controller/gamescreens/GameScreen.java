package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
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

    public GameScreen(Player player) {

        audioQueue = AudioQueue.getInstance();
        // Initiate game with space craft "Agelion"
        this.player = player;
        sound = new Sound();
        collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void render(float delta) {
        if (!parallaxGame.isGameOver()){
            parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
            collisionCalculator.run();
            parallaxView.render();
            DeviceManager.getDevice().update();
        } else {
            gameOver();
        }
    }

    @Override
    public void resize(int width, int height) {
        parallaxView.setWidth(width);
        parallaxView.setHeight(height);
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        Controllers.clearListeners();
        audioQueue.clearAllActiveMusic();
        collisionCalculator.clear();
        CollisionManager.getInstance().getObservers().clear();
    }

    public void newGame() {
        player.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(15));
        parallaxGame = new Parallax(player);
        parallaxView = new ParallaxView(parallaxGame, false);
        controller = new GameController(parallaxGame, parallaxView, DeviceManager.getDevice());
    }

    private void gameOver(){
        dispose();
        GameStateManager.setGameOverScreen(player);
    }
}
