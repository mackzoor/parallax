package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;

import java.util.Locale;

/**
 * Generates a dynamic texture for the player hud.
 */
public class HudTextureGenerator {

    private static final int WITDH = 512;
    private static final int HEIGHT = 256;
    private static final float OPACITY = 0.3f;

    private final Texture generatedTexture;

    private int lives;
    private PowerUpType powerUp;
    private int score;

    HudTextureGenerator(int lives) {
        this.lives = lives;

        final Pixmap pMap = new Pixmap(WITDH, HEIGHT, Pixmap.Format.RGBA8888);
        this.generatedTexture = new Texture(pMap);
    }

    void setScore(int score) {
        this.score = score;
    }

    void setWeapon(PowerUpType powerUp) {
        this.powerUp = powerUp;
    }

    void setLives(int lives) {
        this.lives = lives;
    }

    Texture generateTexture() {
        final Pixmap pMap = renderText(Color.WHITE);
        this.generatedTexture.draw(pMap, 0, 0);
        pMap.dispose();

        return this.generatedTexture;
    }

    private Pixmap renderText(Color fgColor) {
        final SpriteBatch spriteBatch = new SpriteBatch();

        Gdx.gl.glClearColor(1, 1, 1, OPACITY);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, WITDH, HEIGHT);
        spriteBatch.setProjectionMatrix(normalProjection);

        spriteBatch.begin();
        spriteBatch.setColor(fgColor);

        final BitmapFont font = new BitmapFont(true);
        font.setColor(fgColor);
        font.getData().setScale(1);
        font.draw(spriteBatch,
                " Score: " + this.score
                        + "\n Lives: "
                        + this.lives
                        + "\n PowerUp: "
                        + ((this.powerUp == null)
                        ? "None" : this.powerUp.toString().toLowerCase(Locale.ENGLISH)),
                0,
                0
        );

        spriteBatch.end();
        final Pixmap pMap = ScreenUtils.getFrameBufferPixmap(0, 0, WITDH, HEIGHT);

        font.dispose();
        spriteBatch.dispose();

        return pMap;
    }
}
