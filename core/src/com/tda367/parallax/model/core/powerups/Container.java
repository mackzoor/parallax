package com.tda367.parallax.model.core.powerups;

import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Container that holds a {@link IPowerUp}.
 */
public class Container implements com.tda367.parallax.model.core.collision.Collidable, com.tda367.parallax.model.core.util.Updatable, IContainer {

    @Setter @Getter private Vector3f pos;
    @Setter @Getter private Quat4f rot;

    @Setter @Getter private String collisionModelPath;
    private boolean collisionEnabled;

    private IPowerUp usable;

    @Getter private boolean isCollected;

    public Container(IPowerUp pu){
        this.usable = pu;

        pos = new Vector3f();
        rot = new Quat4f();

        collisionModelPath = "3dModels/box/hitbox.obj";

        collisionEnabled = true;
        isCollected = false;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        //No position change for now
    }

    @Override
    public boolean collisionActivated() {
        return collisionEnabled;
    }
    @Override
    public void disableCollision() {
        collisionEnabled = false;
    }
    @Override
    public void enableCollision() {
        collisionEnabled = true;
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
        return com.tda367.parallax.model.core.collision.CollidableType.CONTAINER;
    }

    @Override
    public void handleCollision(com.tda367.parallax.model.core.collision.Collidable collidable) {
        if (collidable.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.SPACECRAFT) {
            isCollected = true;
            removeFromCollisionManager();
        }
    }

    @Override
    public IPowerUp getPowerUp() {
        return usable;
    }
}
