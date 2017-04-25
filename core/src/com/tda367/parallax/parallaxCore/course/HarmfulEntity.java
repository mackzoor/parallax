package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

/**
 * Created by xoxLU on 2017-04-24.
 */
public class HarmfulEntity implements Collidable, Renderable, Updatable {

    private Vector3f pos;
    private Quat4f rot;
    private float velocity;
    private Model model;
    private int time;

    public HarmfulEntity(Float shipVelocity, String modelName, String modelDirectory){
        velocity = shipVelocity + 10;
        pos = new Vector3f();
        rot = new Quat4f();
        model = new Model(modelName, modelDirectory);
        time = 1000;
    }

    public HarmfulEntity(Float shipVelocity, String modelName, String modelDirectory, Vector3f pos){
        velocity = shipVelocity + 10;
        this.pos = pos;
        rot = new Quat4f();
        model = new Model(modelName, modelDirectory);
        time = 1000;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void addToRenderManager()  {
        RenderManager.getInstance().addRenderTask(this);
    }

    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        advanceHarmfulEntity(milliSinceLastUpdate);
    }

    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
    }

    public int getTime() {
        return time;
    }

    public void removeTime(int time){
        this.time = this.time-time;
    }

    private void advanceHarmfulEntity(int timeMilli){
        //System.out.println(this.getPos());
        float posYAdded = velocity * ((float)timeMilli/1000);
        pos.add((Tuple3f)new Vector3f(0, posYAdded, 0));
        System.out.println(timeMilli);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void disableCollision() {

    }

    @Override
    public void enableCollision() {

    }

    @Override
    public Model getCollisionModel() {
        return null;
    }
}
