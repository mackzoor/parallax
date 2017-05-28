package com.tda367.parallax.model.core.world.courseobstacles;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.util.Updatable;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Base class for all course obstacles.
 */
public abstract class CourseObstacleBase implements Collidable, Updatable {

    private final Vector3f pos;
    private final Quat4f rot;

    private boolean collisionEnabled;
    @Getter
    private boolean destroyed;

    CourseObstacleBase(Vector3f pos, Quat4f rot) {
        this.pos = pos;
        this.rot = rot;
        this.collisionEnabled = true;
        this.destroyed = false;
    }

    //Transformable
    @Override
    public Vector3f getPos() {
        return this.pos;
    }

    @Override
    public Quat4f getRot() {
        return this.rot;
    }


    //Collidable
    @Override
    public void enableCollision() {
        this.collisionEnabled = true;
    }

    @Override
    public void disableCollision() {
        this.collisionEnabled = false;
    }

    @Override
    public boolean collisionActivated() {
        return this.collisionEnabled;
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
    public abstract String getCollisionModelPath();

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.OBSTACLE;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT
            || collidable.getCollidableType() == CollidableType.HARMFUL) {
            this.disableCollision();
            this.destroyed = true;
        }
    }

    public abstract ObstacleType getObstacleType();

    @Override
    public void update(int milliSinceLastUpdate) {
    }
}
