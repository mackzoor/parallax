package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;

import java.util.Random;

/**
 * Generates a dynamic texture for the player hud in the game {@link Parallax}
 */
public class HudTextureGenerator {

    private static final int WITDH = 512;
    private static final int HEIGHT = 256;

    private final Texture generatedTexture;

    private int lives;
    private IPowerUp powerUp;
    private int score;

    HudTextureGenerator(int lives) {
        this.lives = lives;
//        generatedTexture = new Texture(renderText(Color.BLACK));
        Pixmap pMap = new Pixmap(WITDH,HEIGHT, Pixmap.Format.RGBA8888);
        generatedTexture = new Texture(pMap);
    }

    void setScore(int score) {
        this.score = score;
    }

    void setWeapon(IPowerUp powerUp) {
        this.powerUp = powerUp;
    }

    void setLives(int lives) {
        this.lives = lives;
    }

    Texture generateTexture() {
        Pixmap pMap = renderText(Color.WHITE);

        Pixmap clear = new Pixmap(WITDH,HEIGHT, Pixmap.Format.RGBA8888);
        clear.setColor(new Color(0,0,0,1));
        clear.fillRectangle(0,0,WITDH,HEIGHT);

        generatedTexture.draw(clear,0,0);
        generatedTexture.draw(pMap, 0, 0);


        pMap.dispose(); //Important!
        clear.dispose();


        return this.generatedTexture;
    }

    private Pixmap renderBackground(Pixmap pMap) {
        final int triangleOffset = 64;

//        pMap.setColor(Color.WHITE);
        pMap.setColor(new Color(0, 0, 1f, 0.7f));

        //Right rectangle
//        pMap.fillRectangle(triangleOffset,0,WITDH-triangleOffset,HEIGHT);

        //Bottom rectangle
//        pMap.fillRectangle(0,triangleOffset,triangleOffset,HEIGHT-triangleOffset);

        //Top left triangle
//        pMap.fillTriangle(0,triangleOffset,triangleOffset,triangleOffset,triangleOffset,0);


        pMap.fillTriangle(0, triangleOffset, 0, 0, triangleOffset, 0);

        return pMap;
    }

    private Pixmap renderText(Color fgColor) {
        SpriteBatch spriteBatch = new SpriteBatch();
//

        Gdx.gl.glClearColor(1, 1, 1, 0.3f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, WITDH, HEIGHT);
        spriteBatch.setProjectionMatrix(normalProjection);

        spriteBatch.begin();
        spriteBatch.setColor(fgColor);

        //Draw text
        BitmapFont font = new BitmapFont(true);
        font.setColor(fgColor);
        font.getData().setScale(1);
        font.draw(spriteBatch, "Score: " + this.score + "\n Lives: " + this.lives, 0, 0);

        //finish write to buffer
        spriteBatch.end();

        //write frame buffer to Pixmap
        final Pixmap pMap = ScreenUtils.getFrameBufferPixmap(0, 0, WITDH, HEIGHT);

        //Dispose of c++ objects.
        font.dispose();
        font = null;
        spriteBatch.dispose();
        spriteBatch = null;



        return pMap;
    }

    private Pixmap outLine(Pixmap pMap) {
        pMap.setColor(Color.WHITE);

        final int lineWidth = 3;

        //Top line
        pMap.fillRectangle(0, 0, pMap.getWidth(), lineWidth);

        //Right line
        pMap.fillRectangle(pMap.getWidth() - lineWidth, 0, pMap.getWidth(), pMap.getHeight());

        //Bottom line
        pMap.fillRectangle(0, pMap.getHeight() - lineWidth, pMap.getWidth(), pMap.getHeight());

        //Left line
        pMap.fillRectangle(0, 0, lineWidth, pMap.getHeight());

        return pMap;
    }

    public void dispose() {
    }
}
