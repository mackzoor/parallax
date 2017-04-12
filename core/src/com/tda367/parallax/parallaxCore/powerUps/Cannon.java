package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.parallaxCore.SoundManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;


/**
 * The cannon PowerUp gives Agelion the ability to fire a shot towards the direction it is pointed at
 */

public class Cannon extends PowerUp {

    SoundManager soundManager;

    public Cannon(){
        soundManager = SoundManager.getInstance();
    }

    @Override
    public void usePU(Vector3f pos, Quat4f rot) {
        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong < 95){
            soundManager.playSound("cannonLow.mp3", 0.8f);
        } else {
            soundManager.playSound("cannon.mp3", 0.8f);
        }

    }
}
