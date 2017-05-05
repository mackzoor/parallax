package com.tda367.parallax.model.parallaxCore.enemies;

import com.tda367.parallax.model.CoreAbstraction.Transformable;
import com.tda367.parallax.model.CoreAbstraction.Updatable;

/**
 * Interface for ai that will try to destroy its target.
 */
public abstract class HunterAI implements Updatable {
    private Transformable target;
    private boolean isActive;
    
    public void setTarget(Transformable collidableObject){
        target = collidableObject;
        isActive = false;
    }
    void start(){
        isActive = true;
    }
    void stop(){
        isActive = false;
    }

    public Transformable getTarget(){
        return target;
    }
}
