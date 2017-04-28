package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.Renderable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */
public interface ICourseModule extends Renderable{
    List<? extends Collidable> getBoxObstacles();
    List<? extends Collidable> getUsables();
    float getLength();
}
