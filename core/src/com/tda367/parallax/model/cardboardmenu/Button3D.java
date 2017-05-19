package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public abstract class Button3D implements Collidable {

    Vector3f pos;
    Quat4f rot;
    boolean isHit;
    String collisionModel;
    boolean collisionEnabled;

    protected Button3D(Vector3f pos,Quat4f rot){
        this.pos = pos;
        this.rot = rot;
        collisionEnabled = true;
        isHit = false;

    }

    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
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
    public String getCollisionModelPath() {
        return collisionModel;
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
        if(collidable.getCollidableType() == CollidableType.HARMFUL){
            isHit = true;
        }
    }

    public boolean isCollided(){
        return isHit;
    }
}