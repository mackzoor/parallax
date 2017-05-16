package com.tda367.parallax.model.parallaxcore.world;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A cube that is renderable and collidable.
 */

public class BoxObstacle implements Collidable {
    private Vector3f pos;
    private Quat4f rot;

    private String collisionModel;

    private boolean collisionEnabled;

    public BoxObstacle(){
        collisionModel = "3dModels/boxObstacle/boxObstacle.g3db";
        pos = new Vector3f();
        rot = new Quat4f();
        collisionEnabled = true;
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
    public void enableCollision(){
        collisionEnabled = true;
    }
    @Override
    public void disableCollision(){
        collisionEnabled = false;
    }
    @Override
    public boolean collisionActivated() {
        return collisionEnabled;
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
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT){
            disableCollision();
        }
    }
}
