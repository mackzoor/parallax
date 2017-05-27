package com.tda367.parallax.view.parallaxview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.model.gameover.GameOverPane;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews.GameOverPaneView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews.StringToTextureGenerator;
import com.tda367.parallax.view.rendering.Renderer3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating and rendering all {@link GameOverPaneView} from {@link GameOver}
 */

public class GameOverView {

    private final GameOver model;
    private final StringToTextureGenerator stringToTextureGenerator;
    private List<GameOverPaneView> gameOverPaneViews;

    public GameOverView(GameOver model, boolean isVr, boolean particlesEnabled) {
        this.model = model;
        this.stringToTextureGenerator = new StringToTextureGenerator(
                model.getGameOverText(),
                GameOverPaneView.getWIDTH(), GameOverPaneView.getHEIGHT()
        );
        Renderer3D.initialize(model.getCamera().getFov(), Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), isVr, particlesEnabled);
        generateGameOverTextViews();
    }

    private void generateGameOverTextViews() {
        final List<GameOverPane> gameOverPanes = this.model.getGameOverPanes();
        final Texture paneTexture = this.stringToTextureGenerator.generateTexture();
        this.gameOverPaneViews = new ArrayList<GameOverPaneView>();
        for (final GameOverPane gameOverPane : gameOverPanes) {
            this.gameOverPaneViews.add(new GameOverPaneView(gameOverPane, paneTexture));
        }
    }

    public void render() {
        for (final GameOverPaneView gameOverPaneView : this.gameOverPaneViews) {
            gameOverPaneView.render();
        }
        Renderer3D.getInstance().renderFrame();
    }

    public void dispose() {

    }
}
