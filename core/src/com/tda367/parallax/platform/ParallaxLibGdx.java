package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;


public class ParallaxLibGdx implements ApplicationListener{

    private GameStateManager gameStateManager;

    @Override
    public void create() {
        Gdx.graphics.setTitle("Galactica space force of justice, ultimate edition");
        Gdx.input.setCatchBackKey(true); //Stops game from exiting when user presses back key
        gameStateManager = GameStateManager.getInstance();
    }

    @Override
    public void resize(int width, int height) {
        gameStateManager.getGameState(gameStateManager.getState()).resize(width,height);

    }

    @Override
    public void render() {
       gameStateManager.render();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}