package com.tda367.parallax.model.gameover;

import com.tda367.parallax.utilities.MathUtilities;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import lombok.Getter;

/**
 * Created by Markus on 2017-05-22.
 */

public class GameOverText {
    @Getter private final Vector3f position;
    @Getter private final Quat4f direction;
    @Getter private final int playerScore;
    @Getter private final int highScore;

    GameOverText(Vector3f position, int playerScore, int highScore) {
        this.position = position;
        this.playerScore = playerScore;
        this.highScore = highScore;
        Vector3f tmpVec = new Vector3f(position);
        tmpVec.negate();
        direction = MathUtilities.vectorToQuat(tmpVec);
    }

    public boolean isNewHighScore() {
        return playerScore >= highScore;
    }
}
