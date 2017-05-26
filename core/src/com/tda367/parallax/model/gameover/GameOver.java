package com.tda367.parallax.model.gameover;

import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.score.ScoreHandler;
import lombok.Getter;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating and holding four {@link GameOverText} surrounding the camera.
 */

public class GameOver {
    private static final float ACTIVE_TIME = 3f;
    private static final float DISTANCE_TO_TEXT = 10f;

    @Getter
    List<GameOverText> gameOverTexts;

    @Getter
    int highScore;
    @Getter
    int playerScore;
    @Getter
    private Player player;
    @Getter
    private boolean obsolete;
    private float timeShowed;

    public GameOver(Player player) {
        this.player = player;
        this.timeShowed = 0;
        this.obsolete = false;
        handleScore();
        generateGameOverTexts(this.playerScore, this.highScore);
    }

    private void generateGameOverTexts(int playerScore, int highScore) {
        this.gameOverTexts = new ArrayList<GameOverText>();
        this.gameOverTexts.add(new GameOverText(
                new Vector3f(DISTANCE_TO_TEXT, 0, 0), playerScore, highScore)
        );
        this.gameOverTexts.add(new GameOverText(
                new Vector3f(-DISTANCE_TO_TEXT, 0, 0), playerScore, highScore)
        );
        this.gameOverTexts.add(new GameOverText(
                new Vector3f(0, DISTANCE_TO_TEXT, 0), playerScore, highScore)
        );
        this.gameOverTexts.add(new GameOverText(
                new Vector3f(0, -DISTANCE_TO_TEXT, 0), playerScore, highScore)
        );
    }

    private void handleScore() {
        final ScoreHandler scoreAdapter = new ScoreHandler();
        scoreAdapter.storeHighScore(this.player.getUserName(), this.player.getScore());
        this.playerScore = this.player.getScore();
        this.highScore = scoreAdapter.getHighScores().get(0);
    }

    public void update(float delta) {
        if (this.timeShowed < ACTIVE_TIME) {
            this.timeShowed += delta;
        } else {
            this.obsolete = true;
            for (final GameOverText gameOverText : this.gameOverTexts) {
                gameOverText.setObsolete(true);
            }
        }
    }
}
