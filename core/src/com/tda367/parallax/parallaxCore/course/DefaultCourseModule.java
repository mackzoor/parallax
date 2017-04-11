package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.IModel;
import com.tda367.parallax.parallaxCore.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 10/04/2017.
 */
public class DefaultCourseModule implements ICourseModule, IModel {
    private List<Collidable> obstacles;
    private List<Collidable> usables;
    private Model model;

    DefaultCourseModule(){
        model = new Model("course.g3db");
        obstacles = new ArrayList<Collidable>();
        usables = new ArrayList<Collidable>();

        //TODO add obstacles in course

        //TODO add usables in course
    }

    @Override
    public Model getModel(){
        return model;
    }

    @Override
    public List<Collidable> getObstacles() {
        return obstacles;
    }

    @Override
    public List<Collidable> getUsables() {
        return usables;
    }
}
