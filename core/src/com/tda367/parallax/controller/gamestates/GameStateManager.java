package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;

/**
 * Created by Markus on 2017-05-04.
 */

public class GameStateManager {

    private static MenuScreen menuScreen;
    private static GameScreen gameScreen;

    public static void setMenuScreen(Game game) {
        if (menuScreen == null) {
            menuScreen = new MenuScreen(game);
        }
        game.setScreen(menuScreen);
    }

    public static void setGameScreen(Game game) {
        if (gameScreen == null) {
            gameScreen = new GameScreen(game);
        }
        game.setScreen(gameScreen);
    }

    private static void garbageCollect() {
        System.gc();
    }

}
