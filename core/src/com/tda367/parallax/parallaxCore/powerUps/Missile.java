package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.CoreAbstraction.RenderManager;
import com.tda367.parallax.CoreAbstraction.SoundManager;
import com.tda367.parallax.CoreAbstraction.Transformable;
//import com.tda367.parallax.parallaxCore.course.HarmfulEntity;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * A tracking "fire and forget" missle.
 */
public class Missile implements IPowerUp {

    SoundManager soundManager;
    Transformable transformable;

    public Missile(Transformable transformable){
        soundManager = SoundManager.getInstance();
        this.transformable = transformable;
    }

    //Creates a missile and calls upon a sound.
    public void usePU(Vector3f pos, Quat4f rot){

//        missiles.add(new HarmfulEntity(25f, "missile.g3db", "3dModels/missile"));

//        missiles.get(missiles.size()-1).getPos().set(new Vector3f(transformable.getPos().getX(),transformable.getPos().getY(),transformable.getPos().getZ()));

//        RenderManager.getInstance().addRenderTask(missiles.get(missiles.size()-1));

        soundManager.playSound("MissileDemo.mp3","sounds/effects", new Float(0.6f));
    }

    @Override
    public void activate() {

    }

    public void update(int milliSinceLastUpdate){
/*        for(int i = 0; i<missiles.size(); i++){
            missiles.get(i).update(milliSinceLastUpdate);
            missiles.get(i).removeTime(milliSinceLastUpdate);
            if(missiles.get(i).getTime() < 0){
                RenderManager.getInstance().removeRenderTask(missiles.get(i));
                missiles.remove(i);
                i--;
            }
        }
        */
    }
}
