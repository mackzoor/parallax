package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;
import com.tda367.parallax.CardboardGame;

public final class GameStateManager {

    private GameStateManager() {}

    private static MainMenuScreen mainMenuScreen;
    private static GameScreen gameScreen;
    private static CardboardGameScreen cardboardGameScreen;
    private static CardboardMenuScreen cardboardMenuScreen;

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
}
