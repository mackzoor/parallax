package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.gameover.GameOverModel;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.view.gameovermenu.GameOverView;


public class GameOverScreen extends ScreenAdapter {
    private GameOverModel model;
    private GameOverView view;
    private Game game;
    private Player player;

    public GameOverScreen(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void render(float delta) {
        model.update(delta);
        if (model.isActive()) {
            this.view.render();
        } else {
            dispose();
            GameStateManager.setMainMenuScreen(this.game, this.player);
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        this.view.dispose();
    }

    public void newGameOver() {
        this.model = new GameOverModel(this.player);
        this.view = new GameOverView(this.model);
    }
}


