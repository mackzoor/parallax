package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.CoreAbstraction.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by xoxLU on 2017-04-24.
 */
public class HarmfulEntity implements com.tda367.parallax.CoreAbstraction.Collidable, Renderable, com.tda367.parallax.CoreAbstraction.Updatable {

    private Vector3f pos;
    private Quat4f rot;
    private float velocity;
    private com.tda367.parallax.CoreAbstraction.Model model;
    private int time;

    public HarmfulEntity(Float shipVelocity, String modelName, String modelDirectory){
        setStartingVelocity(shipVelocity, modelName);
        pos = new Vector3f();
        rot = new Quat4f();
        model = new com.tda367.parallax.CoreAbstraction.Model(modelName, modelDirectory);
        time = 1000;
    }

    public HarmfulEntity(Float shipVelocity, String modelName, String modelDirectory, Vector3f pos){
        velocity = shipVelocity + 10;
        this.pos = pos;
        rot = new Quat4f();
        model = new com.tda367.parallax.CoreAbstraction.Model(modelName, modelDirectory);
        time = 1000;
    }

    private void setStartingVelocity(Float shipVelocity, String modelName) {
        //Check to see what type of HarmfulEntity it is and then put the right velocity.

        //Slower for missile, faster for laser. (And everything else at this moment)
        if (modelName.equals("agelion.g3db")) {
            velocity = shipVelocity + 7;
        } else {
            velocity = shipVelocity + 20;
        }
    }


    @Override
    public void addToRenderManager()  {
        com.tda367.parallax.CoreAbstraction.RenderManager.getInstance().addRenderTask(this);
    }

    @Override
    public void removeFromRenderManager() {
        com.tda367.parallax.CoreAbstraction.RenderManager.getInstance().removeRenderTask(this);
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
        float posYAdded = velocity * ((float)timeMilli/1000);
        pos.add(new Vector3f(0, posYAdded, 0));
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
    public com.tda367.parallax.CoreAbstraction.Model getCollisionModel() {
        return null;
    }


    @Override
    public Model getModel() {
        return model;
    }
}
