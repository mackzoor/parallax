package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;

/**
 * Clears obstacles and enemies in front of the player
 */

public class Nuke extends PowerUpBase {

    private static final String COLLISION_MODEL = "";

    @Override
    public void activate(Transformable transformable) {
        super.activate(transformable);
        //TODO Implement Nuke
    }


    @Override
    public void use() {
    }
    @Override
    public void update(int milliSinceLastUpdate) {}
    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
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
