package com.tda367.parallax.controller;

import com.tda367.parallax.controller.gamescreens.CardboardGameOverScreen;
import com.tda367.parallax.controller.gamescreens.CardboardGameScreen;
import com.tda367.parallax.controller.gamescreens.CardboardMenuScreen;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;

import lombok.Getter;

/**
 * Manages Screens for the Cardboard application
 */

public class CardboardGameStateManager {

    private CardboardGameStateManager() {}

    @Getter private static CardboardGame CARDBOARDGAME;
    private static CardboardMenuScreen cardboardMenuScreen;
    private static CardboardGameScreen cardboardGameScreen;
    private static CardboardGameOverScreen cardboardGameOverScreen;

    public static void setCardboardGame(CardboardGame game) {
        if (CARDBOARDGAME == null) {
            CARDBOARDGAME = game;
        }
    }

    public static void setCardboardMenuScreen(Player player) {
        if (cardboardMenuScreen == null) {
            cardboardMenuScreen = new CardboardMenuScreen(player);
        }
        cardboardMenuScreen.newMainMenu();
        CARDBOARDGAME.setCardboardScreen(cardboardMenuScreen);
    }

    public static void setCardboardGameScreen(Player player) {
        if (cardboardGameScreen == null) {
            cardboardGameScreen = new CardboardGameScreen(player);
        }
        cardboardGameScreen.newGame();
        CARDBOARDGAME.setCardboardScreen(cardboardGameScreen);
    }

    public static void setCardboardGameOverScreen(Player player) {
        if (cardboardGameOverScreen == null) {
            cardboardGameOverScreen = new CardboardGameOverScreen(player);
        }
        cardboardGameOverScreen.newGameOver();
        CARDBOARDGAME.setScreen(cardboardGameOverScreen);
    }
}
