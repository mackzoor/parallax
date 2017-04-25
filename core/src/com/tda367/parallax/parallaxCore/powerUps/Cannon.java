package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.parallaxCore.RenderManager;
import com.tda367.parallax.parallaxCore.SoundManager;
import com.tda367.parallax.parallaxCore.course.HarmfulEntity;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.rmi.activation.ActivationGroupID;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The cannon PowerUp gives Agelion the ability to fire a shot towards the direction it is pointed at
 */

public class Cannon extends PowerUp {

    SoundManager soundManager;
    Agelion spaceCraft;

    private List<HarmfulEntity> laserBeems = new ArrayList<HarmfulEntity>();

    public Cannon(Agelion spaceCraft){
        soundManager = SoundManager.getInstance();
        this.spaceCraft = spaceCraft;
    }


    @Override
    public void usePU(Vector3f pos, Quat4f rot) {
        Random rand = new Random();
        rot = new Quat4f(0,0,0.7071f,0.7071f);
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        laserBeems.add(new HarmfulEntity(spaceCraft.getVelocity(), "laser.g3db", "3dModels/laser"));

        laserBeems.get(laserBeems.size()-1).getPos().set(new Vector3f(spaceCraft.getPos().getX(),spaceCraft.getPos().getY(),spaceCraft.getPos().getZ()));


        laserBeems.get(laserBeems.size()-1).getRot().set(rot);
        laserBeems.get(laserBeems.size()-1).getRot().normalize();

        RenderManager.getInstance().addRenderTask(laserBeems.get(laserBeems.size()-1));

        if(randomSong > 99){
            soundManager.playSound("cannonLow.mp3","sounds/effects", new Float(0.8f));
        } else {
            soundManager.playSound("cannon.mp3","sounds/effects", new Float(0.8f));
        }
    }

    public void update(int milliSinceLastUpdate){
            for(int i = 0; i<laserBeems.size(); i++){
                laserBeems.get(i).update(milliSinceLastUpdate);
                laserBeems.get(i).removeTime(milliSinceLastUpdate);
                if(laserBeems.get(i).getTime() < 0){
                    RenderManager.getInstance().removeRenderTask(laserBeems.get(i));
                    laserBeems.remove(i);
                    i--;
                }
            }
        }
    }

