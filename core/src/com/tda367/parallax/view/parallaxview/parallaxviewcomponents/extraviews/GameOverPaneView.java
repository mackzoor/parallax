package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.tda367.parallax.model.gameover.GameOverPane;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import lombok.Getter;

/**
 * Class responsible for drawing a panel of text containing player's score and
 * high score.
 */

public class GameOverPaneView implements View {

    @Getter private static final int WIDTH = 512;
    @Getter private static final int HEIGHT = 256;
    private static final String INTERNAL_PATH = "3dModels/hudpane/hudPane.g3db";

    private final GameOverPane gameOverPane;
    private final Renderable3dObject textPane;

    public GameOverPaneView(GameOverPane gameOverPane, Texture textTexture) {
        this.gameOverPane = gameOverPane;
        this.textPane = new Renderable3dObject(
                gameOverPane.getPosition(),
                gameOverPane.getDirection(),
                ResourceLoader.getInstance().getModel(INTERNAL_PATH),
                1f
        );
        this.setTexture(textTexture);
        this.textPane.setPos(gameOverPane.getPosition());
    }

    @Override
    public boolean isObsolete() {
        return this.gameOverPane.isObsolete();
    }

    private void setTexture(Texture texture) {
        final Material material = this.textPane.getModelInstance().materials.get(0);
        final TextureAttribute textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
        material.set(textureAttribute);
    }

    @Override
    public void render() {Renderer3D.getInstance().addObjectToFrame(this.textPane);
    }
}
