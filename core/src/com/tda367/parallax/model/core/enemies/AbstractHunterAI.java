package com.tda367.parallax.model.core.enemies;

import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.core.util.Updatable;
import lombok.Getter;
import lombok.Setter;

/**
 * Interface for ai that will try to destroy its target.
 */

public abstract class AbstractHunterAI implements Updatable {
    @Getter
    private Transformable target;
    @Getter
    @Setter
    private boolean isActive;

    public void setTarget(Transformable collidableObject) {
        this.target = collidableObject;
        this.isActive = false;
    }

    void start() {
        this.isActive = true;
    }

    void stop() {
        this.isActive = false;
    }
}
