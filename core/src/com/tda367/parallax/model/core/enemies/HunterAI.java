package com.tda367.parallax.model.core.enemies;

import com.tda367.parallax.model.core.util.Transformable;

import lombok.Getter;
import lombok.Setter;

/**
 * Interface for ai that will try to destroy its target.
 */

public abstract class HunterAI implements com.tda367.parallax.model.core.util.Updatable {
    @Getter private Transformable target;
    @Getter @Setter private boolean isActive;
    
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
