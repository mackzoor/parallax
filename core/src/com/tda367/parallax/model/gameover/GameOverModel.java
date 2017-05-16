package com.tda367.parallax.model.gameover;

import com.tda367.parallax.model.parallaxcore.Player;

/**
 * Created by Rasmus on 2017-05-16.
 */
public class GameOverModel {
    private Player player;
    private boolean exitGameOver;

    public GameOverModel(Player player) {
        this.player = player;
        this.exitGameOver = false;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void exit() {
        this.exitGameOver = true;
    }

    public boolean restart() {
        return this.exitGameOver;
    }
}
