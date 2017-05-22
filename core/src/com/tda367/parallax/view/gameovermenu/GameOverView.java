package com.tda367.parallax.view.gameovermenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tda367.parallax.model.gameover.GameOver;


public class GameOverView {

    private SpriteBatch batch;
    private GameOver model;
    private BitmapFont font;

    public GameOverView(GameOver model){
        this.model = model;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0,1);
        batch.begin();
        font.draw(batch,"YOU DIED", Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if (model.getPlayerScore() < model.getHighScore()) {
            font.draw(batch,"Your score: " + model.getPlayerScore(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
            font.draw(batch,"High score: " + model.getHighScore(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2.5f);
        } else {
            font.draw(batch,"New high score: " + model.getPlayerScore(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        }

        batch.end();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
