package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;

/**
 * Gives the player a shield that last for several seconds.
 */

public class Shield extends PowerUpBase {

    private static final CollidableType COLLIDABLE_TYPE = CollidableType.OBSTACLE;
    private static final String COLLISION_MODEL = "";

    Shield() {
        super();
    }

    @Override
    public void update(int milliSinceLastUpdate) {

    }

    @Override
    public void use() {

    }

    @Override
    public CollidableType getCollidableType() {
        return COLLIDABLE_TYPE;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        //Do nothing?
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.SHIELD;
    }
}
