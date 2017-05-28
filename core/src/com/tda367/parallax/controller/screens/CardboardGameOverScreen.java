package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.screens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.view.parallaxview.GameOverView;
import com.tda367.parallax.view.rendering.Renderer3D;

import static com.tda367.parallax.controller.screens.ScreenState.MAIN_MENU;

/**
 * Class handling the "game over" screen for Cardboard. Shows player's score and high score
 */

public class CardboardGameOverScreen extends CardboardScreenAdapter {
    private static final int SEC_TO_MILLISEC = 1000;
    private GameOver model;
    private GameOverView view;
    private final Player player;
    private final ScreenChanger screenChanger;
    private final boolean particlesEnabled;

    public CardboardGameOverScreen(Player player, ScreenChanger screenChanger, boolean particlesEnabled) {
        super();
        this.particlesEnabled = particlesEnabled;
        this.player = player;
        this.screenChanger = screenChanger;
    }

    @Override
    public void onNewFrame(HeadTransform headTransform) {
        this.model.update((int) (Gdx.graphics.getDeltaTime() * SEC_TO_MILLISEC));
        if (this.model.isObsolete()) {
            dispose();
            this.screenChanger.requestScreenChange(MAIN_MENU, this.player);
        }
    }

    @Override
    public void onDrawEye(Eye eye) {
        Renderer3D.getInstance().onDrawEye(eye);
        this.view.render();
    }

    @Override
    public void dispose() {
        //Not applicable
    }

    public void newGameOver() {
        this.model = new GameOver(this.player);
        this.view = new GameOverView(this.model, true, this.particlesEnabled);
    }
}
