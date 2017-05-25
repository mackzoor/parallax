package com.tda367.parallax.controller;

import com.tda367.parallax.controller.gamescreens.CardboardGameOverScreen;
import com.tda367.parallax.controller.gamescreens.CardboardGameScreen;
import com.tda367.parallax.controller.gamescreens.CardboardMenuScreen;
import com.tda367.parallax.controller.gamescreens.GameState;
import com.tda367.parallax.controller.gamescreens.GameStateChangeListener;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;

/**
 * Manages Screens for the Cardboard application
 */

public class CardboardGameStateManager implements GameStateChangeListener {

    private CardboardGameStateManager() {}

    private static CardboardGame cardboardGame;
    private static CardboardMenuScreen cardboardMenuScreen;
    private static CardboardGameScreen cardboardGameScreen;
    private static CardboardGameOverScreen cardboardGameOverScreen;

    public static void setCardboardGame(CardboardGame game) {
        if (cardboardGame == null) {
            cardboardGame = game;
        }
    }

    private static void setCardboardMenuScreen(Player player) {
        if (cardboardMenuScreen == null) {
            cardboardMenuScreen = new CardboardMenuScreen(player, GameStateManagerSingleton.INSTANCE);
        }
        cardboardMenuScreen.newMainMenu();
        cardboardGame.setCardboardScreen(cardboardMenuScreen);
    }

    private static void setCardboardGameScreen(Player player) {
        if (cardboardGameScreen == null) {
            cardboardGameScreen = new CardboardGameScreen(player, GameStateManagerSingleton.INSTANCE);
        }
        cardboardGameScreen.newGame();
        cardboardGame.setCardboardScreen(cardboardGameScreen);
    }

    private static void setCardboardGameOverScreen(Player player) {
        if (cardboardGameOverScreen == null) {
            cardboardGameOverScreen = new CardboardGameOverScreen(player, GameStateManagerSingleton.INSTANCE);
        }
        cardboardGameOverScreen.newGameOver();
        cardboardGame.setScreen(cardboardGameOverScreen);
    }

    public static void setGameState(GameState nextState, Player player) {
        if (nextState == GameState.MAIN_MENU_STATE) {
            setCardboardMenuScreen(player);
        } else if (nextState == GameState.GAME_STATE) {
            setCardboardGameScreen(player);
        } else if (nextState == GameState.GAME_OVER_STATE) {
            setCardboardGameOverScreen(player);
        }
    }

    @Override
    public void gameStateChanged(GameState nextState, Player player) {
        setGameState(nextState, player);
    }

    private static class GameStateManagerSingleton {
        private static final GameStateChangeListener INSTANCE = new CardboardGameStateManager();
    }
}
