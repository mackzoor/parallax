package com.tda367.parallax.model.parallaxCore.course;

import com.tda367.parallax.model.CoreAbstraction.Collidable;
import com.tda367.parallax.model.CoreAbstraction.Renderable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */
public interface ICourseModule extends Renderable{
    List<? extends Collidable> getBoxObstacles();
    List<? extends Collidable> getUsables();
    float getLength();
}
