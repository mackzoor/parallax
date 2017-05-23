package com.tda367.parallax.model.gameover;

import com.tda367.parallax.model.core.Player;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

import com.tda367.parallax.model.score.ScoreHandler;
import lombok.Getter;

/**
 * Class responsible for creating and holding four {@link GameOverText} surrounding the camera.
 */

public class GameOver {
    @Getter private Player player;
    @Getter private boolean obsolete;
    @Getter int highScore;
    @Getter int playerScore;
    @Getter List<GameOverText> gameOverTexts;
    private float timeShowed;

    private static final float ACTIVE_TIME = 3f;
    private static final float DISTANCE_TO_TEXT = 10f;

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
                new Vector3f(DISTANCE_TO_TEXT,0,0), playerScore, highScore)
        );
        gameOverTexts.add(new GameOverText(
                new Vector3f(-DISTANCE_TO_TEXT,0,0), playerScore, highScore)
        );
        gameOverTexts.add(new GameOverText(
                new Vector3f(0, DISTANCE_TO_TEXT,0), playerScore, highScore)
        );
        gameOverTexts.add(new GameOverText(
                new Vector3f(0,-DISTANCE_TO_TEXT,0), playerScore, highScore)
        );
    }

    private void handleScore() {
        ScoreHandler scoreAdapter = new ScoreHandler();
        scoreAdapter.storeHighScore(player.getUserName(), player.getScore());
        playerScore = player.getScore();
        highScore = scoreAdapter.getHighScores().get(0);
    }

    public void update(float delta) {
        if (timeShowed < ACTIVE_TIME) {
            timeShowed += delta;
        } else {
            this.obsolete = true;
            for(GameOverText gameOverText : gameOverTexts) {
                gameOverText.setObsolete(true);
            }
        }
    }
}
