package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.Collidable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */
public abstract class CourseModule {
    private List<Collidable> obstacles;
    private List<Collidable> usables;

    abstract List<Collidable> getObstacles();
    abstract List<Collidable> getUsables();


    //TODO Get start point & get end point?
}
