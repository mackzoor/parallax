package com.tda367.parallax.model.gameover;

import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.score.ScoreAdapter;

import java.util.List;

import lombok.Getter;


public class GameOverModel {
    @Getter private Player player;
    @Getter private boolean active;
    @Getter List<Integer> highScores;
    @Getter List<String> highScoreHolders;
    @Getter int playerScore;
    private float timeShowed;
    private final float activeTime = 3f;

    public GameOverModel(Player player) {
        this.player = player;
        timeShowed = 0;
        this.active = true;
        ScoreAdapter scoreAdapter = new ScoreAdapter();
        playerScore = player.getScore();
        scoreAdapter.storeHighScore(player.getUserName(), playerScore);
        highScores = scoreAdapter.getHighScores();
        highScoreHolders = scoreAdapter.getHighScoresHolders();
    }


    public void update(float delta) {
        if (timeShowed < activeTime) {
            timeShowed += delta;
        } else {
            this.active = false;
        }
    }
}
