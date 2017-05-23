package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.view.gameovermenu.GameOverView;

/**
 * Class handling the "game over" screen for Desktop and Android.
 * Shows player's score and high score
 */

public class GameOverScreen extends ScreenAdapter {
    private GameOver model;
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
        if (!model.isObsolete()) {
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
        this.model = new GameOver(this.player);
        this.view = new GameOverView(this.model);
    }
}


