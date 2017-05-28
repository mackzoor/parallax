package com.tda367.parallax.model.core.powerups.container;


import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Container that holds a {@link IPowerUp}.
 */
public class Container implements IContainer {

    @Setter
    @Getter
    private Vector3f pos;
    @Setter
    @Getter
    private Quat4f rot;

    @Setter
    @Getter
    private String collisionModelPath;
    private boolean collisionEnabled;

    private final IPowerUp powerUp;

    @Getter
    private boolean isCollected;

    public Container(IPowerUp pu) {
        this.powerUp = pu;

        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.collisionModelPath = "3dModels/box/hitbox.obj";

        this.collisionEnabled = true;
        this.isCollected = false;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        //No need to update anything.
    }

    @Override
    public boolean collisionActivated() {
        return this.collisionEnabled;
    }

    @Override
    public void disableCollision() {
        this.collisionEnabled = false;
    }

    @Override
    public void enableCollision() {
        this.collisionEnabled = true;
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
        return CollidableType.CONTAINER;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        this.isCollected = true;
        this.removeFromCollisionManager();
        this.collisionEnabled = false;
    }

    @Override
    public IPowerUp getPowerUp() {
        return this.powerUp;
    }
}
