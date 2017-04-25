package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.tda367.parallax.parallaxCore.RenderManager;


/*
    Manager Class that manages which State should be rendered.
 */
public class GameStateManager {

    private State state;
    private State previousState;
    private static GameStateManager instance;


    public enum State {
        PLAY,
        MAIN_MENU,
        PAUSE;
    }

    private GameStateManager(){

    }
}