package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tda367.parallax.parallaxCore.*;
/*
    Class that renders and starts the Main Menu
 */
    //TODO Create GUI for the menu

public class MainMenuState implements ApplicationListener {

    Texture texture;
    Stage stage;
    SpriteBatch batch;
    ParallaxLibGdxPlayState parallaxLibGdxPlayState;
    GameStateManager gameStateManager;
    Parallax parallax;

    public MainMenuState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        texture = new Texture("badlogic.jpg");
        stage = new Stage();
        batch = new SpriteBatch();

    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public synchronized void render() {
        batch.begin();
        Gdx.gl.glClearColor(0,1,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(texture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {
        texture.dispose();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
