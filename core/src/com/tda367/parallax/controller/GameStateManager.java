package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.gamescreens.GameOverScreen;
import com.tda367.parallax.controller.gamescreens.GameScreen;
import com.tda367.parallax.controller.gamescreens.MainMenuScreen;
import com.tda367.parallax.model.core.Player;

/**
 * Manages Screens for the desktop and Android application
 */

public final class GameStateManager implements GameStateChangeListener {

    private GameStateManager() {}

    private static Game GAME;
    private static MainMenuScreen mainMenuScreen;
    private static GameScreen gameScreen;
    private static GameOverScreen gameOverScreen;

    public static void setGame(Game game) {
        if (GAME == null) {
            GAME = game;
        }
    }

    private static void setMainMenuScreen(Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(player, new GameStateManager());
        }
        mainMenuScreen.newMainMenu();
        GAME.setScreen(mainMenuScreen);
    }

    private static void setGameScreen(Player player) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(player, new GameStateManager());
        }
        gameScreen.newGame();
        GAME.setScreen(gameScreen);
    }

    private static void setGameOverScreen(Player player) {
        if (gameOverScreen == null) {
            gameOverScreen = new GameOverScreen(player, new GameStateManager());
        }
        gameOverScreen.newGameOver();
        GAME.setScreen(gameOverScreen);
    }

    public static void setGameState(GameState nextState, Player player) {
        if (nextState == GameState.MAIN_MENU_STATE) {
            setMainMenuScreen(player);
        } else if (nextState == GameState.GAME_STATE) {
            setGameScreen(player);
        } else if (nextState == GameState.GAME_OVER_STATE) {
            setGameOverScreen(player);
        }
    }

    @Override
    public void gameStateChanged(GameState nextState, Player player) {
        setGameState(nextState, player);
    }
}
