package com.tda367.parallax.parallaxCore;

/**
 * Interface for ai that will try to destroy its target.
 */
public abstract class HunterAI implements Updatable{
    private Collidable target;
    private boolean isActive;
    
    void setTarget(Collidable collidableObject){
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
