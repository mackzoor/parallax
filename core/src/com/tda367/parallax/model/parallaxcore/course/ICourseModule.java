package com.tda367.parallax.model.parallaxcore.course;

import com.tda367.parallax.model.coreabstraction.Collidable;
import com.tda367.parallax.model.coreabstraction.Renderable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */
public interface ICourseModule extends Renderable{
    List<? extends Collidable> getBoxObstacles();
    List<? extends Collidable> getUsables();
    float getLength();
}
