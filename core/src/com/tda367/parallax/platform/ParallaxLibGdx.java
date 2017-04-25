package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.parallaxCore.Camera;
import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import javafx.scene.PerspectiveCamera;



public class ParallaxLibGdx implements ApplicationListener {


    private com.badlogic.gdx.graphics.PerspectiveCamera camera;
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private ParallaxLibGDXController controller;
    private Sound sound;

    @Override
    public void create() {
        Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

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