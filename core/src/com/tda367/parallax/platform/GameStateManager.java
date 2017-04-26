package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;

import static com.tda367.parallax.platform.GameStateManager.State.MAIN_MENU;
import static com.tda367.parallax.platform.GameStateManager.State.PLAY;


/*
    Manager Class that manages which State should be rendered.
 */
public class GameStateManager implements ApplicationListener {

    private State state;
    private State previousState;
    private static GameStateManager instance;
    private ParallaxLibGdxPlayState parallaxLibGdxPlayState;
    private MainMenuState mainMenuState;
    public enum State {
        PLAY,
        MAIN_MENU,
        PAUSE;
    }

    private GameStateManager(){
        this.setState(PLAY);
        this.getGameState(state);
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        instance.getGameState(state).render();
        instance.getGameState(previousState).dispose();

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


    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

    public static GameStateManager getInstance() {
        if (instance == null) instance = new com.tda367.parallax.platform.GameStateManager();
        return instance;
    }

    public ApplicationListener getGameState(State state){

        if (state == PLAY && previousState != PLAY) {
            previousState = state;
            this.parallaxLibGdxPlayState = new ParallaxLibGdxPlayState(this);
            return parallaxLibGdxPlayState;
        } else if (state == PLAY && previousState == PLAY) {
            previousState = state;
            return parallaxLibGdxPlayState;
        } else if (state == MAIN_MENU && previousState != MAIN_MENU) {
            previousState = state;
            return mainMenuState = new MainMenuState(this);
        } else {
            previousState = state;
            return mainMenuState;
        }
    }

}