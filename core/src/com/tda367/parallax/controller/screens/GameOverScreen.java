package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.view.parallaxview.GameOverView;

import static com.tda367.parallax.controller.screens.ScreenState.MAIN_MENU;

/**
 * Class handling the "game over" screen for Desktop and Android.
 * Shows player's score and high score
 */

public class GameOverScreen extends ScreenAdapter {
    private static final int SEC_TO_MILLISEC = 1000;
    private GameOver model;
    private GameOverView view;
    private final Player player;
    private final ScreenChanger screenChanger;
    private final boolean particlesEnabled;

    public GameOverScreen(Player player, ScreenChanger screenChanger, boolean particlesEnabled) {
        super();
        this.particlesEnabled = particlesEnabled;
        this.player = player;
        this.screenChanger = screenChanger;
    }

    @Override
    public void render(float delta) {
        this.model.update((int) (delta * SEC_TO_MILLISEC));
        if (this.model.isObsolete()) {
            dispose();
            this.screenChanger.requestScreenChange(MAIN_MENU, this.player);
        } else {
            this.view.render();
        }

    }

    @Override
    public void dispose() {
        this.view.dispose();
    }

    public void newGameOver() {
        this.model = new GameOver(this.player);
        this.view = new GameOverView(this.model, false, this.particlesEnabled);
    }
}


