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
        font.draw(batch,"Score: " + model.getPlayerScore(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        StringBuilder highScoreBuilder = new StringBuilder();
        highScoreBuilder.append("Top players: ");

        for(int i = 0; i < model.getHighScores().size(); i++){
            highScoreBuilder.append("\n" + Integer.toString(i+1) + ". "
                    + model.getHighScoreHolders().get(i)
                    + " med " +  model.getHighScores().get(i) + "poäng");
        }

        String highScoreString = highScoreBuilder.toString();

        font.draw(batch, highScoreString, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/2);
        batch.end();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
