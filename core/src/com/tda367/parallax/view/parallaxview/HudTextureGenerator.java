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

    private static Texture generatedTexture;
    private Pixmap combined;

    private int lives;
    private IPowerUp powerUp;
    private int score;

    public HudTextureGenerator(int lives) {
        this.lives = lives;
        combined = new Pixmap(512,512, Pixmap.Format.RGBA8888);
        generatedTexture = new Texture(render(Color.BLACK,Color.BLUE));
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
        Pixmap pm = render(Color.WHITE,new Color(1f,1f,1f,0.25f));
        pm = outLine(pm);
        generatedTexture.draw(pm,0,0);
        pm.dispose(); //Important!


        return generatedTexture;
    }

    private int counter = 0;
    private Pixmap render(Color fg_color, Color bg_color) {
        counter++;
        SpriteBatch spriteBatch = new SpriteBatch();

        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, 512, 512, false);
        frameBuffer.begin();

        Gdx.gl.glClearColor(bg_color.r, bg_color.g, bg_color.b, bg_color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, 512,  512);
        spriteBatch.setProjectionMatrix(normalProjection);

        spriteBatch.begin();
        spriteBatch.setColor(fg_color);

        //Draw text
        BitmapFont font = new BitmapFont(true);
        font.setColor(fg_color);
        font.getData().setScale(3);
        font.draw(spriteBatch, "Score: " + score + "\n Lives: " + String.valueOf(lives),  0, 0);
        spriteBatch.end();//finish write to buffer
        Pixmap pm = ScreenUtils.getFrameBufferPixmap(0, 0, 512, 512);//write frame buffer to Pixmap
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
        combined.dispose();
    }
}
