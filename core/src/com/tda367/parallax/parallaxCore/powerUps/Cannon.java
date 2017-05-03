package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.CoreAbstraction.Model;
import com.tda367.parallax.CoreAbstraction.RenderManager;
import com.tda367.parallax.CoreAbstraction.SoundManager;
import com.tda367.parallax.CoreAbstraction.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The cannon PowerUp gives Agelion the ability to fire a shot towards the direction it is pointed at
 */

public class Cannon implements IPowerUp {

    SoundManager soundManager;
    Transformable transformable;

    public Cannon(Transformable transformable){
        soundManager = SoundManager.getInstance();
        this.transformable = transformable;
    }

    //Creates a laser-beam and calls upon a sound.
    public void usePU(Vector3f pos, Quat4f rot) {
        //Adds a HarmfulEntity (object) to the laser-beam list
//        laserBeams.add(new HarmfulEntity(25f, "laser.g3db", "3dModels/laser"));

        //Sets the rotation of the laser to the correct values
        rot = new Quat4f(0,0,0.7071f,0.7071f);

        //Set laser rotation
//        setLaserRot(rot, laserBeams.size()-1);

        //Set laser position to the ships current position
//        setLaserPos(new Vector3f(transformable.getPos().getX(),transformable.getPos().getY(),transformable.getPos().getZ()), laserBeams.size()-1);

        //Adds the latest added laser-beam to the render task
//        RenderManager.getInstance().addRenderTask(laserBeams.get(laserBeams.size()-1));

        //Plays a sound for the laser
        playLaserSound();
    }

    //Calls upon the sound class to play the laser sound based on a String name and String directory.
    private void playLaserSound(){
        Random rand = new Random();
        int randomSong = rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots
        if(randomSong > 199){
            soundManager.playSound("cannonLow.mp3","sounds/effects", new Float(0.8f));
        } else {
            soundManager.playSound("cannon.mp3","sounds/effects", new Float(0.8f));
        }
    }

    //Sets the laser rotation at the given position in the laserBeams list.
    private void setLaserRot(Quat4f rot, int placeInList){
//        laserBeams.get(placeInList).getRot().set(rot);
//        laserBeams.get(placeInList).getRot().normalize();
    }

    //Sets the laser position at the given position in the laserBeams list.
    private void setLaserPos(Vector3f pos, int placeInList){
        /*laserBeams.get(placeInList).getPos().set(pos);*/
    }

    @Override
    public void activate() {

    }

    //Calls upon the chain of update classes. Adding this to the rendering and updating. Also checks if the beam has been in the course the right amount of time. (the time set in the constructor
    public void update(int milliSinceLastUpdate){/*
        for(int i = 0; i< laserBeams.size(); i++){
            laserBeams.get(i).update(milliSinceLastUpdate);
            laserBeams.get(i).removeTime(milliSinceLastUpdate);
            if(laserBeams.get(i).getTime() < 0){
                RenderManager.getInstance().removeRenderTask(laserBeams.get(i));
                laserBeams.remove(i);
                i--;
            }
        }*/
    }

    @Override
    public Model getModel() {
        return null;
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

    @Override
    public Vector3f getPos() {
        return null;
    }

    @Override
    public Quat4f getRot() {
        return null;
    }

    @Override
    public void addToRenderManager() {

    }

    @Override
    public void removeFromRenderManager() {

    }
}

