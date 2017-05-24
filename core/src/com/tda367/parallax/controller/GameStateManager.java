package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.gamescreens.*;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;
import lombok.Getter;

public final class GameStateManager {

    private GameStateManager() {
    }

    @Getter private static Game GAME;
    @Getter private static CardboardGame CARDBOARDGAME;
    private static MainMenuScreen mainMenuScreen;
    private static CardboardMenuScreen cardboardMenuScreen;
    private static GameScreen gameScreen;
    private static CardboardGameScreen cardboardGameScreen;
    private static GameOverScreen gameOverScreen;
    private static CardboardGameOverScreen cardboardGameOverScreen;

    public static void setGame(Game game) {
        if (GAME == null) {
            GAME = game;
        }
    }

    public static void setCardboardGame(CardboardGame game) {
        if (CARDBOARDGAME == null) {
            CARDBOARDGAME = game;
        }
    }

    public static void setMainMenuScreen(Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(player);
        }
        mainMenuScreen.newMainMenu();
        GAME.setScreen(mainMenuScreen);
    }

    public static void setCardboardMenuScreen(Player player) {
        if (cardboardMenuScreen == null) {
            cardboardMenuScreen = new CardboardMenuScreen(player);
        }
        cardboardMenuScreen.newMainMenu();
        CARDBOARDGAME.setCardboardScreen(cardboardMenuScreen);
    }

    public static void setGameScreen(Player player) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(player);
        }
        gameScreen.newGame();
        GAME.setScreen(gameScreen);
    }

    public static void setCardboardGameScreen(Player player) {
        if (cardboardGameScreen == null) {
            cardboardGameScreen = new CardboardGameScreen(player);
        }
        cardboardGameScreen.newGame();
        CARDBOARDGAME.setCardboardScreen(cardboardGameScreen);
    }

    public static void setGameOverScreen(Player player) {
        if (gameOverScreen == null) {
            gameOverScreen = new GameOverScreen(player);
        }
        gameOverScreen.newGameOver();
        GAME.setScreen(gameOverScreen);
    }

    public static void setCardboardGameOverScreen(Player player) {
        if (cardboardGameOverScreen == null) {
            cardboardGameOverScreen = new CardboardGameOverScreen(player);
        }
        cardboardGameOverScreen.newGameOver();
        CARDBOARDGAME.setScreen(cardboardGameOverScreen);
    }
}
