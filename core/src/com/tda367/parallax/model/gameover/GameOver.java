package com.tda367.parallax.model.gameover;


import com.tda367.parallax.model.core.Camera;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.score.ScoreHandler;
import lombok.Getter;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating and holding four {@link GameOverPane} surrounding the camera.
 */

public class GameOver {
    private static final int ACTIVE_TIME = 5000;
    private static final float DISTANCE_TO_TEXT = 3f;

    @Getter
    private List<GameOverPane> gameOverPanes;

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
    @Getter
    private final String gameOverText;
    private float timeShowed;

    public GameOver(Player player) {
        this.player = player;
        this.timeShowed = 0;
        this.obsolete = false;
        this.camera = new Camera();
        handleScore();
        this.gameOverText = this.generateText();
        generateGameOverPanes();
    }

    private void generateGameOverPanes() {
        this.gameOverPanes = new ArrayList<GameOverPane>();
        this.gameOverPanes.add(new GameOverPane(
                new Vector3f(DISTANCE_TO_TEXT, 0, 0))
        );
        this.gameOverPanes.add(new GameOverPane(
                new Vector3f(-DISTANCE_TO_TEXT, 0, 0))
        );
        this.gameOverPanes.add(new GameOverPane(
                new Vector3f(0, DISTANCE_TO_TEXT, 0))
        );
        this.gameOverPanes.add(new GameOverPane(
                new Vector3f(0, -DISTANCE_TO_TEXT, 0))
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
            for (final GameOverPane gameOverPane : this.gameOverPanes) {
                gameOverPane.setObsolete(true);
            }
        }
    }

    private boolean isNewHighScore() {
        return this.highScore < this.playerScore;
    }

    private String generateText() {
        if (this.isNewHighScore()) {
            return "YOU DEAD!"
                    + "\nYour score: " + this.playerScore
                    + "\nWow! New High score!";
        } else {
            return "YOU DEAD!"
                    + "\nYour score: " + this.playerScore
                    + "\nHigh score: " + this.highScore;
        }
    }
}
