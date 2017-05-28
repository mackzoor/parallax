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
import lombok.Setter;

/**
 * A class that generates a texture containing the text from a String.
 */

public class StringToTextureGenerator {

    @Setter
    private String string;
    private int width;
    private int height;
    private final Texture generatedTexture;

    public StringToTextureGenerator(String string, int width, int height) {
        this.string = string;
        this.width = width;
        this.height = height;
        final Pixmap pMap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        this.generatedTexture = new Texture(pMap);
    }

    public Texture generateTexture() {
        final Pixmap pMap = renderText();

        final Pixmap clear = new Pixmap(this.width, this.height, Pixmap.Format.RGBA8888);
        clear.setColor(new Color(0, 0, 0, 1));
        clear.fillRectangle(0, 0, this.width, this.height);

        this.generatedTexture.draw(clear, 0, 0);
        this.generatedTexture.draw(pMap, 0, 0);

        //Important!
        pMap.dispose();

        clear.dispose();

        return this.generatedTexture;
    }

    private Pixmap renderText() {
        SpriteBatch spriteBatch = new SpriteBatch();

        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        final Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, this.width, this.height);
        spriteBatch.setProjectionMatrix(normalProjection);

        spriteBatch.begin();

        //Draw text
        BitmapFont font = new BitmapFont(true);

        font.setColor(Color.WHITE);
        font.getData().setScale(1);
        font.draw(spriteBatch, this.string, 0, 0);

        //finish write to buffer
        spriteBatch.end();

        //write frame buffer to Pixmap
        final Pixmap pMap = ScreenUtils.getFrameBufferPixmap(0, 0, this.width, this.height);

        //Dispose of c++ objects.
        font.dispose();
        font = null;
        spriteBatch.dispose();
        spriteBatch = null;

        return pMap;
    }
}
