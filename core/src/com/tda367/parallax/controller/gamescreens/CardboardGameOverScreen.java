package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.CardboardGameStateManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.gameover.GameOverView;

/**
 * Class handling the "game over" screen for Cardboard. Shows player's score and high score
 */

public class CardboardGameOverScreen extends CardboardScreenAdapter {
    private GameOver model;
    private GameOverView view;
    private CardboardGame game;
    private Player player;

    public CardboardGameOverScreen(Player player) {
        this.player = player;
    }

    @Override
    public void onNewFrame(HeadTransform headTransform) {
        model.update(Gdx.graphics.getDeltaTime());
        if (model.isObsolete()) {
            dispose();
            CardboardGameStateManager.setCardboardMenuScreen(player);
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
