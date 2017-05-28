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

public class GameOverPane {
    @Getter
    private final Vector3f position;
    @Getter
    private final Quat4f direction;
    @Getter
    @Setter
    private boolean obsolete;

    GameOverPane(Vector3f position) {
        this.position = position;
        this.obsolete = false;
        final Vector3f tmpVec = new Vector3f(position);
        this.direction = MathUtilities.vectorToQuat(tmpVec);
    }
}
