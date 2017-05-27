package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;

import com.tda367.parallax.model.gameover.GameOverText;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.GameOverView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

/**
 * Class responsible for drawing a panel of text containing player's score and
 * high score for {@link GameOverView}
 */

public class GameOverTextView implements View {

    private static final String INTERNAL_PATH = "3dModels/boxObstacle/boxObstacle.g3db";

    private final GameOverText gameOverText;
    private final Renderable3dObject renderable3dObject;

    public GameOverTextView(GameOverText gameOverText) {
        this.gameOverText = gameOverText;
        this.renderable3dObject = new Renderable3dObject(
                gameOverText.getPosition(),
                gameOverText.getDirection(),
                ResourceLoader.getInstance().getModel(INTERNAL_PATH),
                1f
        );
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return this.gameOverText.isObsolete();
    }
}
