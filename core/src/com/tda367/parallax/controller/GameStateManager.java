package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.gamescreens.GameOverScreen;
import com.tda367.parallax.controller.gamescreens.GameScreen;
import com.tda367.parallax.controller.gamescreens.MainMenuScreen;
import com.tda367.parallax.model.core.Player;
import lombok.Getter;

public final class GameStateManager {

    private GameStateManager() {}

    @Getter private static Game GAME;
    private static MainMenuScreen mainMenuScreen;
    private static GameScreen gameScreen;
    private static GameOverScreen gameOverScreen;

    public static void setGame(Game game) {
        if (GAME == null) {
            GAME = game;
        }
    }

    public static void setMainMenuScreen(Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(player);
        }
        mainMenuScreen.newMainMenu();
        GAME.setScreen(mainMenuScreen);
    }

    public static void setGameScreen(Player player) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(player);
        }
        gameScreen.newGame();
        GAME.setScreen(gameScreen);
    }

    public static void setGameOverScreen(Player player) {
        if (gameOverScreen == null) {
            gameOverScreen = new GameOverScreen(player);
        }
        gameOverScreen.newGameOver();
        GAME.setScreen(gameOverScreen);
    }
}
