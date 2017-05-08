package com.tda367.parallax.model.parallaxcore.enemies;

import com.tda367.parallax.model.coreabstraction.Transformable;
import com.tda367.parallax.model.coreabstraction.Updatable;
import lombok.Getter;

/**
 * Interface for ai that will try to destroy its target.
 */

public abstract class HunterAI implements Updatable {
    @Getter private Transformable target;
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
}
