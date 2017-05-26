package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

interface RenderablePowerUp {
    void render();
    void kill();
    boolean isDead();

    void enableEffects(boolean value);

    void setPosition(Vector3f pos);
    void setRotation(Quat4f rot);
}
