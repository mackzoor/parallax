package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.tda367.parallax.controller.screens.GameOverScreen;
import com.tda367.parallax.controller.screens.GameScreen;
import com.tda367.parallax.controller.screens.MenuScreen;
import com.tda367.parallax.controller.screens.ScreenChanger;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.model.core.Player;

/**
 * Tells its {@link Game} whether to show a {@link MenuScreen},
 * {@link GameScreen}, or a {@link GameOverScreen}.
 */

public final class ScreenManager implements ScreenChanger {

    private static ScreenManager instance;

    private Game game;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    private boolean highPerformanceMode;

    private ScreenManager(boolean highPerformanceMode) {
        this.highPerformanceMode = highPerformanceMode;
    }

    public static void initialize(boolean highPerformanceMode) {
        instance = new ScreenManager(highPerformanceMode);
    }

    public static ScreenManager getInstance() {
        return instance;
    }


    public void setGame(Game game) {
        if (this.game == null) {
            this.game = game;
        }
    }

    private synchronized void setMenuScreen(Player player) {
        if (this.menuScreen == null) {
            this.menuScreen = new MenuScreen(player, instance, this.highPerformanceMode);
        }
        this.menuScreen.newMainMenu();
        this.game.setScreen(this.menuScreen);
    }

    private synchronized void setGameScreen(Player player) {
        if (this.gameScreen == null) {
            this.gameScreen = new GameScreen(player, instance, this.highPerformanceMode);
        }
        this.gameScreen.newGame();
        this.game.setScreen(this.gameScreen);
    }

    private synchronized void setGameOverScreen(Player player) {
        if (this.gameOverScreen == null) {
            this.gameOverScreen = new GameOverScreen(player, instance, this.highPerformanceMode);
        }
        this.gameOverScreen.newGameOver();
        this.game.setScreen(this.gameOverScreen);
    }

    public void setGameState(ScreenState nextState, Player player) {
        if (nextState == ScreenState.MAIN_MENU) {
            this.setMenuScreen(player);
        } else if (nextState == ScreenState.GAME) {
            this.setGameScreen(player);
        } else if (nextState == ScreenState.GAME_OVER) {
            this.setGameOverScreen(player);
        }
    }

    @Override
    public void requestScreenChange(ScreenState nextState, Player player) {
        this.setGameState(nextState, player);
    }
}
