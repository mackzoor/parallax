package com.tda367.parallax.view.gameovermenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tda367.parallax.model.gameover.GameOverModel;


public class GameOverView {

    private SpriteBatch batch;
    private GameOverModel model;
    private BitmapFont font;

    public GameOverView(GameOverModel model){
        this.model = model;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0,1);
        batch.begin();
        font.draw(batch,"YOU DIED", Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        font.draw(batch,"Score: " + model.getPlayer().getScore(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        batch.end();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
