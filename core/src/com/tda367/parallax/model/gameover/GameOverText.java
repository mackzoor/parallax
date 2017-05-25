package com.tda367.parallax.model.gameover;

import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Holds the data for the panels surrounding the camera in {@link GameOver}.
 * Makes sure that the panels are facing the camera.
 */

public class GameOverText {
    @Getter
    private final Vector3f position;
    @Getter
    private final Quat4f direction;
    @Getter
    private final int playerScore;
    @Getter
    private final int highScore;
    @Getter
    @Setter
    private boolean obsolete;

    GameOverText(Vector3f position, int playerScore, int highScore) {
        this.position = position;
        this.playerScore = playerScore;
        this.highScore = highScore;
        this.obsolete = false;
        Vector3f tmpVec = new Vector3f(position);
        tmpVec.negate();
        this.direction = MathUtilities.vectorToQuat(tmpVec);
    }

    public boolean isNewHighScore() {
        return this.playerScore >= this.highScore;
    }
}
