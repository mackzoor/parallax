package com.tda367.parallax.parallaxCore;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Interface for objects that are transformable in 3d space.
 */
public interface Transformable {
    Vector3f getPos();
    Quat4f getRot();
}
