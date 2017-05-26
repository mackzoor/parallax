package com.tda367.parallax.view.gameover;

import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.model.gameover.GameOverText;
import com.tda367.parallax.view.rendering.Renderer3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating and rendering all {@link GameOverTextView} from {@link GameOver}
 */


public class GameOverView {

    private GameOver model;

    private List<GameOverTextView> gameOverTextViews;

    public GameOverView(GameOver model, boolean isVr) {
        this.model = model;
        Renderer3D.initialize(model.getCamera().getFov(), Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), isVr);
        generateGameOverTextViews();
    }

    private void generateGameOverTextViews() {
        final List<GameOverText> gameOverTexts = this.model.getGameOverTexts();
        this.gameOverTextViews = new ArrayList<GameOverTextView>();
        for (final GameOverText gameOverText : gameOverTexts) {
            this.gameOverTextViews.add(new GameOverTextView(gameOverText));
        }
    }

    public void render() {
        for (final GameOverTextView gameOverTextView : this.gameOverTextViews) {
            gameOverTextView.render();
        }
        Renderer3D.getInstance().renderFrame();
    }

    public void dispose() {

    }
}
