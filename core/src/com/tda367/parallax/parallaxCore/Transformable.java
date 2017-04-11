package com.tda367.parallax.parallaxCore;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Anthony on 11/04/2017.
 */
public interface Transformable {
    Vector3f getPos();
    Quat4f getRot();
}
