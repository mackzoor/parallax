package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.screens.ScreenChanger;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.model.core.Player;

/**
 * Manages Screens for the desktop and Android application
 */

public final class ScreenManager implements ScreenChanger {

    private static ScreenManager instance;

    private Game game;
    private com.tda367.parallax.controller.screens.MainMenuScreen mainMenuScreen;
    private com.tda367.parallax.controller.screens.GameScreen gameScreen;
    private com.tda367.parallax.controller.screens.GameOverScreen gameOverScreen;
    private boolean highPerformanceMode;

    public static void initialize(boolean highPerformanceMode){
        instance = new ScreenManager(highPerformanceMode);
    }

    public static ScreenManager getInstance(){
        return instance;
    }


    private ScreenManager(boolean highPerformanceMode) {
        this.highPerformanceMode = highPerformanceMode;
    }

    public void setGame(Game game) {
        if (this.game == null) {
            this.game = game;
        }
    }

    private synchronized void setMainMenuScreen(Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new com.tda367.parallax.controller.screens.MainMenuScreen(player, instance, highPerformanceMode);
        }
        mainMenuScreen.newMainMenu();
        game.setScreen(mainMenuScreen);
    }

    private synchronized void setGameScreen(Player player) {
        if (gameScreen == null) {
            gameScreen = new com.tda367.parallax.controller.screens.GameScreen(player, instance, highPerformanceMode);
        }
        gameScreen.newGame();
        game.setScreen(gameScreen);
    }

    private synchronized void setGameOverScreen(Player player) {
        if (gameOverScreen == null) {
            gameOverScreen = new com.tda367.parallax.controller.screens.GameOverScreen(player, instance,highPerformanceMode);
        }
        gameOverScreen.newGameOver();
        game.setScreen(gameOverScreen);
    }

    public void setGameState(ScreenState nextState, Player player) {
        if (nextState == ScreenState.MAIN_MENU) {
            setMainMenuScreen(player);
        } else if (nextState == ScreenState.GAME) {
            setGameScreen(player);
        } else if (nextState == ScreenState.GAME_OVER) {
            setGameOverScreen(player);
        }
    }

    @Override
    public void requestScreenChange(ScreenState nextState, Player player) {
        setGameState(nextState, player);
    }
}
