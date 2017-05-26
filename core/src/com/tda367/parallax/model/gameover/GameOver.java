package com.tda367.parallax.model.gameover;


import com.tda367.parallax.model.core.Camera;
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
    private static final int ACTIVE_TIME = 3000;
    private static final float DISTANCE_TO_TEXT = 10f;

    @Getter
    private List<GameOverText> gameOverTexts;

    @Getter
    private int highScore;
    @Getter
    private int playerScore;
    @Getter
    private final Player player;
    @Getter
    private boolean obsolete;
    @Getter
    private final Camera camera;
    private float timeShowed;

    public GameOver(Player player) {
        this.player = player;
        this.timeShowed = 0;
        this.obsolete = false;
        this.camera = new Camera();
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

    public void update(int milliSinceLastUpdate) {
        if (this.timeShowed < ACTIVE_TIME) {
            this.timeShowed += milliSinceLastUpdate;
        } else {
            this.obsolete = true;
            for (final GameOverText gameOverText : this.gameOverTexts) {
                gameOverText.setObsolete(true);
            }
        }
    }
}
