package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;

/**
 * LaserEyes fires a laserbeam that is controlled by the player using google cardboard.
 */

public class LaserEyes extends PowerUpBase {

    private static final CollidableType COLLIDABLE_TYPE = CollidableType.HARMFUL;

    LaserEyes(){
        super();
    }

    @Override
    public void activate(Transformable ship) {

    }
    @Override
    public void use() {

    }
    @Override
    public CollidableType getCollidableType() {
        return COLLIDABLE_TYPE;
    }
    @Override
    public void update(int milliSinceLastUpdate) {

    }
    @Override
    public void handleCollision(Collidable collidable) {
        //Do nothing
    }
    @Override
    public String getCollisionModelPath() {
        return null;
    }
}
