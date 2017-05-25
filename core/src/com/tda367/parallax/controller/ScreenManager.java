package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.screens.*;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.model.core.Player;

/**
 * Manages Screens for the desktop and Android application
 */

public final class ScreenManager implements ScreenChanger {

    private ScreenManager() {}

    private static Game game;
    private static com.tda367.parallax.controller.screens.MainMenuScreen mainMenuScreen;
    private static com.tda367.parallax.controller.screens.GameScreen gameScreen;
    private static com.tda367.parallax.controller.screens.GameOverScreen gameOverScreen;

    public static void setGame(Game game) {
        if (ScreenManager.game == null) {
            ScreenManager.game = game;
        }
    }

    private static void setMainMenuScreen(Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new com.tda367.parallax.controller.screens.MainMenuScreen(player, GameStateManagerSingleton.INSTANCE);
        }
        mainMenuScreen.newMainMenu();
        game.setScreen(mainMenuScreen);
    }

    private static void setGameScreen(Player player) {
        if (gameScreen == null) {
            gameScreen = new com.tda367.parallax.controller.screens.GameScreen(player, GameStateManagerSingleton.INSTANCE);
        }
        gameScreen.newGame();
        game.setScreen(gameScreen);
    }

    private static void setGameOverScreen(Player player) {
        if (gameOverScreen == null) {
            gameOverScreen = new com.tda367.parallax.controller.screens.GameOverScreen(player, GameStateManagerSingleton.INSTANCE);
        }
        gameOverScreen.newGameOver();
        game.setScreen(gameOverScreen);
    }

    public static void setGameState(ScreenState nextState, Player player) {
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

    private static class GameStateManagerSingleton {
        private static final ScreenChanger INSTANCE = new ScreenManager();
    }
}
