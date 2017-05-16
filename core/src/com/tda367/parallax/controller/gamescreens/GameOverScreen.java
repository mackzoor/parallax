package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.gamecontrollers.GameOverController;
import com.tda367.parallax.model.gameover.GameOverModel;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.view.gameovermenu.GameOverView;


public class GameOverScreen implements Screen {
    private GameOverModel model;
    private GameOverController controller;
    private GameOverView view;
    private Game game;
    private Player player;

    public GameOverScreen(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public void show() {
    }

    public void render(float delta) {
        if (this.model.restart()) {
            GameStateManager.setMainMenuScreen(this.game, this.player);
        } else {
            this.view.render();
        }

    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        this.view.dispose();
    }

    public void newGameOver() {
        this.model = new GameOverModel(this.player);
        this.view = new GameOverView(this.model);
        this.controller = new GameOverController(this.model, DeviceManager.getDevice());
    }
}


