package com.tda367.parallax.view.gameovermenu;

import com.tda367.parallax.model.gameover.GameOverText;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;

/**
 * Class responsible for drawing a panel of text containing player's score and
 * high score for {@link GameOverView}
 */

public class GameOverTextView implements View {

    private GameOverText gameOverText;
    private final static String INTERNAL_PATH = "3dModels/boxObstacle/boxObstacle.g3db";
    private Renderable3dObject renderable3dObject;

    GameOverTextView(GameOverText gameOverText) {
        this.gameOverText = gameOverText;
        renderable3dObject = new Renderable3dObject(
                gameOverText.getPosition(),
                gameOverText.getDirection(),
                ResourceLoader.getInstance().getModel(INTERNAL_PATH),
                1f
        );
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return gameOverText.isObsolete();
    }
}
