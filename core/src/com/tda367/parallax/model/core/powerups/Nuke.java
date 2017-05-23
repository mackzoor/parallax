package com.tda367.parallax.model.core.powerups;

import com.tda367.parallax.model.core.util.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Clears obstacles and enemies in front of the player
 */

public class Nuke implements IPowerUp {

    private boolean isActive;
    private boolean isDead;

    public void activate(Transformable ship) {
        //TODO Implement Nuke
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void use() {
        isActive = true;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void update(int milliSinceLastUpdate) {}

    @Override
    public boolean collisionActivated() {
        return false;
    }

    @Override
    public void disableCollision() {}

    @Override
    public void enableCollision() {}

    @Override
    public String getCollisionModelPath() {
        return null;
    }

    @Override
    public void addToCollisionManager() {
        com.tda367.parallax.model.core.collision.CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public void removeFromCollisionManager() {
        com.tda367.parallax.model.core.collision.CollisionManager.getInstance().removeCollisionCheck(this);
    }

    @Override
    public com.tda367.parallax.model.core.collision.CollidableType getCollidableType() {
        return com.tda367.parallax.model.core.collision.CollidableType.HARMFUL;
    }

    @Override
    public void handleCollision(com.tda367.parallax.model.core.collision.Collidable collidable) {
        //Do nothing
    }

    @Override
    public Vector3f getPos() {
        return null;
    }

    @Override
    public Quat4f getRot() {
        return null;
    }
}
