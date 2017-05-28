package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.screens.ScreenChanger;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.model.core.Player;

/**
 * Manages Screens for the desktop and Android application.
 */

public final class ScreenManager implements ScreenChanger {

    private static ScreenManager instance;

    private Game game;
    private com.tda367.parallax.controller.screens.MainMenuScreen mainMenuScreen;
    private com.tda367.parallax.controller.screens.GameScreen gameScreen;
    private com.tda367.parallax.controller.screens.GameOverScreen gameOverScreen;
    private boolean highPerformanceMode;

    private ScreenManager(boolean highPerformanceMode) {
        this.highPerformanceMode = highPerformanceMode;
    }

    public static void initialize(boolean highPerformanceMode) {
        instance = new ScreenManager(highPerformanceMode);
    }

    public static ScreenManager getInstance() {
        return instance;
    }

    public void setGame(Game game) {
        if (this.game == null) {
            this.game = game;
        }
    }

    private synchronized void setMainMenuScreen(Player player) {
        if (this.mainMenuScreen == null) {
            this.mainMenuScreen = new com.tda367.parallax.controller.screens.MainMenuScreen(player, instance, this.highPerformanceMode);
        }
        this.mainMenuScreen.newMainMenu();
        this.game.setScreen(this.mainMenuScreen);
    }

    private synchronized void setGameScreen(Player player) {
        if (this.gameScreen == null) {
            this.gameScreen = new com.tda367.parallax.controller.screens.GameScreen(player, instance, this.highPerformanceMode);
        }
        this.gameScreen.newGame();
        this.game.setScreen(this.gameScreen);
    }

    private synchronized void setGameOverScreen(Player player) {
        if (this.gameOverScreen == null) {
            this.gameOverScreen = new com.tda367.parallax.controller.screens.GameOverScreen(player, instance, this.highPerformanceMode);
        }
        this.gameOverScreen.newGameOver();
        this.game.setScreen(this.gameOverScreen);
    }

    public void setGameState(ScreenState nextState, Player player) {
        if (nextState == ScreenState.MAIN_MENU) {
            this.setMainMenuScreen(player);
        } else if (nextState == ScreenState.GAME) {
            this.setGameScreen(player);
        } else if (nextState == ScreenState.GAME_OVER) {
            this.setGameOverScreen(player);
        }
    }

    @Override
    public void requestScreenChange(ScreenState nextState, Player player) {
        this.setGameState(nextState, player);
    }
}
