package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

interface RenderablePowerUp {
    void playActivationSound();

    void render();

    void kill();

    boolean isDead();

    void enableEffects(boolean value);

    void setPos(Vector3f post);

    void setRot(Quat4f rot);
}
