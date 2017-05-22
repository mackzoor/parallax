package com.tda367.parallax.view.parallaxview;

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
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;

/**
 * Generates a dynamic texture for the player hud in the game {@link com.tda367.parallax.model.parallaxcore.Parallax}
 */
public class HudTextureGenerator {

    private final static int WITDH = 512;
    private final static int HEIGHT = 256;

    private static Texture generatedTexture;


    private int lives;
    private IPowerUp powerUp;
    private int score;

    public HudTextureGenerator(int lives) {
        this.lives = lives;
        generatedTexture = new Texture(renderText(Color.BLACK));
    }

    void setScore(int score){
       this.score = score;
    }
    void setWeapon(IPowerUp powerUp) {
        this.powerUp = powerUp;
    }
    void setLives(int lives){
        this.lives = lives;
    }

    Texture generateTexture(){
        Pixmap pm = renderText(Color.WHITE);
//        pm = outLine(pm);
        renderBackground(pm);
        generatedTexture.draw(pm,0,0);
        pm.dispose(); //Important!


        return generatedTexture;
    }

    private int counter = 0;

    private Pixmap renderBackground(Pixmap pm){
        int triangleOffset = 64;

//        pm.setColor(Color.WHITE);
        pm.setColor(new Color(0,0,1f,0.7f));

        //Right rectangle
//        pm.fillRectangle(triangleOffset,0,WITDH-triangleOffset,HEIGHT);

        //Bottom rectangle
//        pm.fillRectangle(0,triangleOffset,triangleOffset,HEIGHT-triangleOffset);

        //Top left triangle
//        pm.fillTriangle(0,triangleOffset,triangleOffset,triangleOffset,triangleOffset,0);


        pm.fillTriangle(0,triangleOffset,0,0,triangleOffset,0);

        return pm;
    }
    private Pixmap renderText(Color fgColor) {
        counter++;
        SpriteBatch spriteBatch = new SpriteBatch();

        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, WITDH, HEIGHT, false);
        frameBuffer.begin();

        Gdx.gl.glClearColor(1,1,1,0.3f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, WITDH, HEIGHT);
        spriteBatch.setProjectionMatrix(normalProjection);

        spriteBatch.begin();
        spriteBatch.setColor(fgColor);

        //Draw text
        BitmapFont font = new BitmapFont(true);
        font.setColor(fgColor);
        font.getData().setScale(3);
        font.draw(spriteBatch, "Score: " + score + "\n Lives: " + String.valueOf(lives),  48, 48);

        spriteBatch.end();//finish write to buffer
        Pixmap pm = ScreenUtils.getFrameBufferPixmap(0, 0, WITDH, HEIGHT); //write frame buffer to Pixmap
        frameBuffer.end();

        //Dispose of c++ objects.
        font.dispose();
        font = null;
        frameBuffer.dispose();
        frameBuffer = null;
        spriteBatch.dispose();
        spriteBatch = null;

        return pm;
    }
    private Pixmap outLine(Pixmap pm){
        pm.setColor(Color.WHITE);

        int lineWidth = 3;

        //Top line
        pm.fillRectangle(0,0,pm.getWidth(),lineWidth);

        //Right line
        pm.fillRectangle(pm.getWidth()-lineWidth,0,pm.getWidth(),pm.getHeight());

        //Bottom line
        pm.fillRectangle(0,pm.getHeight()-lineWidth,pm.getWidth(),pm.getHeight());

        //Left line
        pm.fillRectangle(0,0,lineWidth,pm.getHeight());

        return pm;
    }

    public void dispose(){
    }
}
