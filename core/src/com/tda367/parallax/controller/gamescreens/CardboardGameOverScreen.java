package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.GameState;
import com.tda367.parallax.controller.GameStateChangeListener;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.view.gameover.GameOverView;
import com.tda367.parallax.view.rendering.Renderer3D;

/**
 * Class handling the "game over" screen for Cardboard. Shows player's score and high score
 */

public class CardboardGameOverScreen extends CardboardScreenAdapter {
    private GameOver model;
    private GameOverView view;
    private Player player;
    private GameStateChangeListener gameStateChangeListener;

    public CardboardGameOverScreen(Player player, GameStateChangeListener gameStateChangeListener) {
        this.player = player;
        this.gameStateChangeListener = gameStateChangeListener;
    }

    @Override
    public void onNewFrame(HeadTransform headTransform) {
        this.model.update(Gdx.graphics.getDeltaTime());
        if (this.model.isObsolete()) {
            dispose();
            gameStateChangeListener.gameStateChanged(GameState.MAIN_MENU_STATE, this.player);
        }
    }

    @Override
    public void onDrawEye(Eye eye) {
        Renderer3D.getInstance().onDrawEye(eye);
        this.view.render();
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
