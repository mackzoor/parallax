package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.IModel;
import com.tda367.parallax.parallaxCore.Model;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 10/04/2017.
 */
public class DefaultCourseModule implements ICourseModule, IModel {
    private Vector3f pos;
    private Quat4f rot;
    private float length;


    private List<Collidable> obstacles;
    private List<Collidable> usables;
    private Model model;

    DefaultCourseModule(){
        pos = new Vector3f();
        rot = new Quat4f();

        model = new Model("course.g3db");
        length = 64;
        obstacles = new ArrayList<Collidable>();
        usables = new ArrayList<Collidable>();

        //TODO add obstacles in course

        //TODO add usables in course
    }

    @Override
    public float getLength(){
        return length;
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

    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
    }
}
