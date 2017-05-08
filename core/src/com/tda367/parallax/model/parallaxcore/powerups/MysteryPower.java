package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Gives the player a random powerUp
 */

public class MysteryPower implements IPowerUp {

    public void usePU(Vector3f pos, Quat4f rot) {
        //TODO Implement MysteryPower
    }

    @Override
    public void activate() {}

    @Override
    public void update(int milliSinceLastUpdate) {}

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void disableCollision() {}

    @Override
    public void enableCollision() {}

    @Override
    public Model getCollisionModel() {
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
    public void handleCollision(CollidableType type) {
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

    @Override
    public void addToRenderManager() {}

    @Override
    public void removeFromRenderManager() {}
}
