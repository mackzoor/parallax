package com.tda367.parallax.parallaxCore.enemies;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.Updatable;

/**
 * Interface for ai that will try to destroy its target.
 */
public abstract class HunterAI implements Updatable {
    private Collidable target;
    private boolean isActive;
    
    public void setTarget(Collidable collidableObject){
        target = collidableObject;
        isActive = false;
    }
    void start(){
        isActive = true;
    }
    void stop(){
        isActive = false;
    }
    
    public Collidable getTarget(){
        return target;
    }
}
