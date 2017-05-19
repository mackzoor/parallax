package com.tda367.parallax.model.gameover;

import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.score.ScoreAdapter;
import lombok.Getter;


public class GameOverModel {
    @Getter private Player player;
    private boolean exitGameOver;
    @Getter private ScoreAdapter scoreAdapter;

    public GameOverModel(Player player) {
        this.player = player;
        this.exitGameOver = false;
        scoreAdapter = new ScoreAdapter();
        scoreAdapter.storeHighScore(player.getUserName(), player.getScore());
    }

    public void exit() {
        this.exitGameOver = true;
    }

    public boolean restart() {
        return this.exitGameOver;
    }
}
