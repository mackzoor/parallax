package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;


import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

interface RenderableSpaceCraft {
    void render();

    void setCriticalDamage(boolean enabled);

    void setPosition(Vector3f pos);
    void setRotation(Quat4f rot);
}
