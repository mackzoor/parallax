package com.tda367.parallax.model.menu.button3d;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public abstract class Button3D implements Collidable {

    @Setter
    private String collisionModel;

    private final Vector3f pos;
    private final Quat4f rot;
    private boolean isHit;
    private boolean collisionEnabled;

    Button3D(Vector3f pos, Quat4f rot) {
        this.pos = pos;
        this.rot = rot;
        this.collisionEnabled = true;
        this.isHit = false;

    }

    @Override
    public Vector3f getPos() {
        return this.pos;
    }

    @Override
    public Quat4f getRot() {
        return this.rot;
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
    public String getCollisionModelPath() {
        return this.collisionModel;
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
        return CollidableType.OBSTACLE;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() == CollidableType.HARMFUL) {
            this.isHit = true;
        }
    }

    public boolean isCollided() {
        return this.isHit;
    }
}
