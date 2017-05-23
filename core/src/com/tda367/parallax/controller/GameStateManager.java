package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.gamescreens.*;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;

public final class GameStateManager {

    private GameStateManager() {}

    private static MainMenuScreen mainMenuScreen;
    private static CardboardMenuScreen cardboardMenuScreen;
    private static GameScreen gameScreen;
    private static CardboardGameScreen cardboardGameScreen;
    private static GameOverScreen gameOverScreen;
    private static CardboardGameOverScreen cardboardGameOverScreen;

    public static void setMainMenuScreen(Game game,Player player) {
        if (mainMenuScreen == null) {
            mainMenuScreen = new MainMenuScreen(game,player);
        }
        mainMenuScreen.newMainMenu();
        game.setScreen(mainMenuScreen);
    }

    public static void setCardboardMenuScreen(CardboardGame game){
        if(cardboardMenuScreen == null){
            cardboardMenuScreen = new CardboardMenuScreen(game);
        }
        game.setCardboardScreen(cardboardMenuScreen);
    }

    public static void setGameScreen(Game game,Player player) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(game,player);
        }
        gameScreen.newGame();
        game.setScreen(gameScreen);
    }

    public static void setCardboardGameScreen(CardboardGame game){
        if(cardboardGameScreen == null){
            cardboardGameScreen = new CardboardGameScreen(game);
        }
        game.setCardboardScreen(cardboardGameScreen);
    }

    public static void setGameOverScreen(Game game,Player player){
        if(gameOverScreen == null){
            gameOverScreen = new GameOverScreen(game,player);
        }
        gameOverScreen.newGameOver();
        game.setScreen(gameOverScreen);
    }

    public static void setCardboardGameOverScreen(CardboardGame game, Player player) {
        if (cardboardGameOverScreen == null) {
            cardboardGameOverScreen = new CardboardGameOverScreen(game, player);
        }
        cardboardGameOverScreen.newGameOver();
        game.setScreen(cardboardGameOverScreen);
    }
}
