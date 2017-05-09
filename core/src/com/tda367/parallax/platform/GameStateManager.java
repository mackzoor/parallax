package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import lombok.Getter;
import lombok.Setter;

import static com.tda367.parallax.platform.GameStateManager.State.MAIN_MENU;
import static com.tda367.parallax.platform.GameStateManager.State.PLAY;

/**
    Manager Class that manages which State should be rendered.
 */

public class GameStateManager implements ApplicationListener {

    @Getter @Setter private State state;
    private State previousState;
    private static GameStateManager instance;
    private ParallaxLibGdxPlayState parallaxLibGdxPlayState;
    private MainMenuState mainMenuState;

    public enum State {
        PLAY,
        MAIN_MENU,
        PAUSE;
    }

    private GameStateManager() {
        this.setState(MAIN_MENU);
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        if (state != previousState) {
            instance.getGameState(previousState).dispose();
        }
        instance.getGameState(state).render();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        this.getGameState(state).dispose();
    }

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new com.tda367.parallax.platform.GameStateManager();
            return instance;
        } else {
            return instance;
        }
    }

    //TODO Split this method into several or simplify
    /**
     * Method returns the current state which is to be rendered
     */
    public ApplicationListener getGameState(State state){

        if (state == PLAY && previousState != PLAY) {
            previousState = state;
            this.parallaxLibGdxPlayState = new ParallaxLibGdxPlayState(this);
            return this.parallaxLibGdxPlayState;
        } else if (state == PLAY /*&& previousState == PLAY*/) {
            previousState = state;
            return this.parallaxLibGdxPlayState;
        } else if (state == MAIN_MENU && previousState != MAIN_MENU) {
            previousState = state;
            this.mainMenuState = new MainMenuState(this);
            return this.mainMenuState;
        } else {
            previousState = state;
            return this.mainMenuState;
        }
    }

}