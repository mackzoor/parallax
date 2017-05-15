package com.tda367.parallax.model.parallaxcore.world;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.util.Renderable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */

public interface ICourseModule extends Renderable{
    List<? extends Collidable> getBoxObstacles();
    List<? extends Collidable> getContainers();
    float getLength();
    void add3dObjectsToCollisionManager();
    void remove3dObjectsFromCollisionManager();
}
