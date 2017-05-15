package com.tda367.parallax.model.parallaxcore.enemies;

import com.tda367.parallax.model.parallaxcore.util.Transformable;
import com.tda367.parallax.model.parallaxcore.util.Updatable;
import lombok.Getter;
import lombok.Setter;

/**
 * Interface for ai that will try to destroy its target.
 */

public abstract class HunterAI implements Updatable {
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
