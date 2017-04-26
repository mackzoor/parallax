package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.parallaxCore.Player;

public class ParallaxLibGdx implements ApplicationListener {

    private com.badlogic.gdx.graphics.PerspectiveCamera camera;
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private ParallaxLibGDXController controller;
    private Sound sound;
    private GameStateManager gameStateManager;

    @Override
    public void create() {
        Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");
        gameStateManager = GameStateManager.getInstance();

    }

    @Override
    public void resize(int width, int height) {
        gameStateManager.getGameState(GameStateManager.State.PLAY).resize(width,height);

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