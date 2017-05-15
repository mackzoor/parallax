package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.util.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Gives the player a random powerUp
 */

public class MysteryPower implements IPowerUp {

    private boolean isActive;
    private boolean isDead;

    public void activate(Transformable ship) {
        //TODO Implement MysteryPower
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
        CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public void removeFromCollisionManager() {
        CollisionManager.getInstance().removeCollisionCheck(this);
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
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
