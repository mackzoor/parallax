package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.gamescreens.*;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.parallaxcore.Player;

public final class GameStateManager {

    private GameStateManager() {}

    private static MainMenuScreen mainMenuScreen;
    private static GameScreen gameScreen;
    private static CardboardGameScreen cardboardGameScreen;
    private static CardboardMenuScreen cardboardMenuScreen;
    private static GameOverScreen gameOverScreen;

    public static void setMainMenuScreen(Game game,Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(game,player);
        }
        game.setScreen(mainMenuScreen);
        mainMenuScreen.newMainMenu();
    }

    public static void setGameScreen(Game game,Player player) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(game,player);
        }
        game.setScreen(gameScreen);
        gameScreen.newGame();
    }

    public static void setCardboardGameScreen(CardboardGame game){
        if(cardboardGameScreen == null){
            cardboardGameScreen = new CardboardGameScreen(game);
        }
        game.setCardboardScreen(cardboardGameScreen);
    }

    public static void setCardboardMenuScreen(CardboardGame game){
        if(cardboardMenuScreen == null){
            cardboardMenuScreen = new CardboardMenuScreen(game);
        }
        game.setCardboardScreen(cardboardMenuScreen);
    }

    public static void setGameOverScreen(Game game,Player player){
        if(gameOverScreen == null){
            gameOverScreen = new GameOverScreen(game,player);
        }
        game.setScreen(gameOverScreen);
        gameOverScreen.newGameOver();
    }
}
