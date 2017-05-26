package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
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

import static com.tda367.parallax.controller.screens.ScreenState.GAME_OVER;

public class GameScreen extends ScreenAdapter {

    private final Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private final Sound sound;
    private final AudioQueue audioQueue;
    private final CollisionCalculator collisionCalculator;
    private ParallaxView parallaxView;
    private final ScreenChanger screenChanger;

    public GameScreen(Player player, ScreenChanger screenChanger) {

        this.audioQueue = AudioQueue.getInstance();
        // Initiate game with space craft "Agelion"
        this.player = player;
        this.screenChanger = screenChanger;
        this.sound = new Sound();
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void render(float delta) {
        if (!this.parallaxGame.isGameOver()) {
            this.parallaxGame.update((int) (delta * 1000));
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
        this.controller = new GameController(this.parallaxGame,
                this.parallaxView,
                DeviceManager.getDevice());
    }

    private void gameOver() {
        this.dispose();
        this.screenChanger.requestScreenChange(GAME_OVER, this.player);
    }
}
