package com.tda367.parallax.model.core.powerups;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.util.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * LaserEyes fires a laserbeam that is controlled by the player using google cardboard.
 */

public class LaserEyes implements IPowerUp {

    private boolean isActive;
    private boolean isDead;

    public void activate(Transformable ship) {

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
    public void update(int milliSinceLastUpdate) {

    }

    @Override
    public boolean collisionActivated() {
        return false;
    }

    @Override
    public void disableCollision() {

    }

    @Override
    public void enableCollision() {

    }

    @Override
    public String getCollisionModelPath() {
        //TODO Placeholder, add lazer 3d model.
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
    public void handleCollision(Collidable collidable) {
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
