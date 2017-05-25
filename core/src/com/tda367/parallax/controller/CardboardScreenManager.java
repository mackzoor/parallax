package com.tda367.parallax.controller;

import com.tda367.parallax.controller.screens.CardboardGameOverScreen;
import com.tda367.parallax.controller.screens.CardboardGameScreen;
import com.tda367.parallax.controller.screens.CardboardMenuScreen;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.controller.screens.ScreenChanger;
import com.tda367.parallax.controller.screens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;

import static com.tda367.parallax.controller.screens.ScreenState.GAME;
import static com.tda367.parallax.controller.screens.ScreenState.GAME_OVER;
import static com.tda367.parallax.controller.screens.ScreenState.MAIN_MENU;

/**
 * Manages Screens for the Cardboard application
 */

public class CardboardScreenManager implements ScreenChanger {

    private CardboardScreenManager() {}

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
            cardboardMenuScreen = new CardboardMenuScreen(player, ScreenChangerSingleton.INSTANCE);
        }
        cardboardMenuScreen.newMainMenu();
        cardboardGame.setCardboardScreen(cardboardMenuScreen);
    }

    private static void setCardboardGameScreen(Player player) {
        if (cardboardGameScreen == null) {
            cardboardGameScreen = new CardboardGameScreen(player, ScreenChangerSingleton.INSTANCE);
        }
        cardboardGameScreen.newGame();
        cardboardGame.setCardboardScreen(cardboardGameScreen);
    }

    private static void setCardboardGameOverScreen(Player player) {
        if (cardboardGameOverScreen == null) {
            cardboardGameOverScreen = new CardboardGameOverScreen(player, ScreenChangerSingleton.INSTANCE);
        }
        cardboardGameOverScreen.newGameOver();
        cardboardGame.setScreen(cardboardGameOverScreen);
    }

    public static void setGameState(ScreenState nextState, Player player) {
        if (nextState == MAIN_MENU) {
            setCardboardMenuScreen(player);
        } else if (nextState == GAME) {
            setCardboardGameScreen(player);
        } else if (nextState == GAME_OVER) {
            setCardboardGameOverScreen(player);
        }
    }

    @Override
    public void requestScreenChange(ScreenState nextState, Player player) {
        setGameState(nextState, player);
    }

    private static class ScreenChangerSingleton {
        private static final ScreenChanger INSTANCE = new CardboardScreenManager();
    }
}
