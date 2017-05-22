package com.tda367.parallax.model.gameover;

import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.score.ScoreAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

import lombok.Getter;


public class GameOver {
    @Getter private Player player;
    @Getter private boolean obsolete;
    @Getter int highScore;
    @Getter int playerScore;
    @Getter List<GameOverText> gameOverTexts;
    private float timeShowed;

    private static final float activeTime = 3f;
    private static final float distanceToText = 10f;

    public GameOver(Player player) {
        this.player = player;
        timeShowed = 0;
        this.obsolete = false;
        handleScore();
        generateGameOverTexts(playerScore, highScore);
    }

    private void generateGameOverTexts(int playerScore, int highScore) {
        gameOverTexts = new ArrayList<GameOverText>();
        gameOverTexts.add(new GameOverText(
                new Vector3f(distanceToText,0,0), playerScore, highScore)
        );
        gameOverTexts.add(new GameOverText(
                new Vector3f(-distanceToText,0,0), playerScore, highScore)
        );
        gameOverTexts.add(new GameOverText(
                new Vector3f(0,distanceToText,0), playerScore, highScore)
        );
        gameOverTexts.add(new GameOverText(
                new Vector3f(0,-distanceToText,0), playerScore, highScore)
        );
    }

    private void handleScore() {
        ScoreAdapter scoreAdapter = new ScoreAdapter();
        scoreAdapter.storeHighScore(player.getUserName(), player.getScore());
        playerScore = player.getScore();
        highScore = scoreAdapter.getHighScores().get(0);
    }

    public void update(float delta) {
        if (timeShowed < activeTime) {
            timeShowed += delta;
        } else {
            this.obsolete = true;
            for(GameOverText gameOverText : gameOverTexts) {
                gameOverText.setObsolete(true);
            }
        }
    }
}
