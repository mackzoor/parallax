package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.parallaxCore.RenderManager;
import com.tda367.parallax.parallaxCore.SoundManager;
import com.tda367.parallax.parallaxCore.course.HarmfulEntity;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * A tracking "fire and forget" missle.
 */
public class Missile implements IPowerUp {

    SoundManager soundManager;
    Agelion spaceCraft;

    private List<HarmfulEntity> missiles = new ArrayList<HarmfulEntity>();

    public Missile(Agelion spaceCraft){
        soundManager = SoundManager.getInstance();
        this.spaceCraft = spaceCraft;
    }

    //Creates a missile and calls upon a sound.
    public void usePU(Vector3f pos, Quat4f rot){

        missiles.add(new HarmfulEntity(spaceCraft.getForwardVelocity(), "missile.g3db", "3dModels/missile"));

        missiles.get(missiles.size()-1).getPos().set(new Vector3f(spaceCraft.getPos().getX(),spaceCraft.getPos().getY(),spaceCraft.getPos().getZ()));

        RenderManager.getInstance().addRenderTask(missiles.get(missiles.size()-1));

        soundManager.playSound("MissileDemo.mp3","sounds/effects", new Float(0.6f));
    }

    @Override
    public void activate(ISpaceCraft agelion) {

    }

    public void update(int milliSinceLastUpdate){
        for(int i = 0; i<missiles.size(); i++){
            missiles.get(i).update(milliSinceLastUpdate);
            missiles.get(i).removeTime(milliSinceLastUpdate);
            if(missiles.get(i).getTime() < 0){
                RenderManager.getInstance().removeRenderTask(missiles.get(i));
                missiles.remove(i);
                i--;
            }
        }
    }

}
