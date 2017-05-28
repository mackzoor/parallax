package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Interface for all renderable powerups.
 */
interface RenderablePowerUp {
    void playActivationSound();

    void render();

    /**
     * Will tell the powerup to kill itself.
     */
    void kill();

    /**
     * Will tell if a powerup has completed all of its effects and is no longer needed.
     * @return true if no longer needed to be rendered.
     */
    boolean isDead();


    void enableEffects(boolean value);

    void setPos(Vector3f post);

    void setRot(Quat4f rot);
}
