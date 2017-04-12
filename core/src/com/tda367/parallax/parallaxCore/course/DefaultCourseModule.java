package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Anthony on 10/04/2017.
 */
public class DefaultCourseModule implements ICourseModule, IModel {
    private Vector3f pos;
    private Quat4f rot;
    private float length;


    private List<Obstacle> obstacles;
    private List<Collidable> usables;
    private Model model;


    DefaultCourseModule(Vector3f pos,int obstacleAmmount){
        this.pos = pos;
        this.pos.setY(pos.getY()+getLength()/2);
        rot = new Quat4f();

        model = new Model("course.g3db");
        length = 64;
        this.obstacles = new ArrayList<Obstacle>();
        usables = new ArrayList<Collidable>();

        addObstacles(obstacleAmmount);

        addToRenderManager();
        //TODO add usables in course
    }

    public DefaultCourseModule(Vector3f pos){
        this(pos,10);
    }

    private void addObstacles(int i){
        for (int x = 0; x < i; x++){
            Obstacle obstacleNew = new Obstacle();

            Random rand = new Random();
            Vector3f obstaclePos = new Vector3f(
                    rand.nextFloat()*20-10,
                    rand.nextFloat()*length-length/2+(this.pos.getY()),
                    rand.nextFloat()*20-10
            );

            obstacleNew.getPos().set(obstaclePos);

            this.obstacles.add(obstacleNew);
        }
    }

    private void addToRenderer(Renderable renderable){
        RenderManager.getInstance().addRenderTask(renderable);
    }
    private void removeFromRenderer(Renderable renderable){
        RenderManager.getInstance().removeRenderTask(renderable);
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
    public List<? extends Collidable> getObstacles() {
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

    @Override
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);

        for (Obstacle obstacle : obstacles){
            obstacle.addToRenderManager();
        }

    }

    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);

        for (Obstacle obstacle : obstacles){
            obstacle.removeFromRenderManager();
        }

    }
}
