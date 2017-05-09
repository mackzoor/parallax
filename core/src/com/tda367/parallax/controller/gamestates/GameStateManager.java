package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;

public final class GameStateManager {

    private GameStateManager() {}

    private static MainMenuScreen mainMenuScreen;
    private static GameScreen gameScreen;

    public static void setMainMenuScreen(Game game) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(game);
        }
        game.setScreen(mainMenuScreen);
    }

    public static void setGameScreen(Game game) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(game);
        }
        game.setScreen(gameScreen);
    }
}
