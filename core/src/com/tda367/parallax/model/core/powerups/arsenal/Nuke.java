package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;

/**
 * Clears obstacles and enemies in front of the player.
 */

public class Nuke extends PowerUpBase {

    private static final String COLLISION_MODEL = "";

    Nuke() {
        super();
    }

    @Override
    public void use() {
    }

    @Override
    public void update(int milliSinceLastUpdate) {
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return null;
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        //Do nothing
    }
}
