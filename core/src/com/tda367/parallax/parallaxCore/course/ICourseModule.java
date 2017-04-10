package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.Collidable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */
public interface ICourseModule {
    List<Collidable> getObstacles();
    List<Collidable> getUsables();


    //TODO Get start point & get end point?
}
