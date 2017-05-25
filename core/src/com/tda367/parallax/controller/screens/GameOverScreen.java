package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.view.gameover.GameOverView;

import static com.tda367.parallax.controller.screens.ScreenState.MAIN_MENU;

/**
 * Class handling the "game over" screen for Desktop and Android.
 * Shows player's score and high score
 */

public class GameOverScreen extends ScreenAdapter {
    private GameOver model;
    private GameOverView view;
    private Player player;
    private ScreenChanger screenChanger;

    public GameOverScreen(Player player, ScreenChanger screenChanger) {
        this.player = player;
        this.screenChanger = screenChanger;
    }

    @Override
    public void render(float delta) {
        this.model.update(delta);
        if (!this.model.isObsolete()) {
            this.view.render();
        } else {
            dispose();
            screenChanger.requestScreenChange(MAIN_MENU, this.player);
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


