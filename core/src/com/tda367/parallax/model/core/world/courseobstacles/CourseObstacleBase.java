package com.tda367.parallax.model.core.world.courseobstacles;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.util.Updatable;
import com.tda367.parallax.model.core.world.ICourseModule;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Base class for all course obstacles in {@link ICourseModule}'s.
 */
public abstract class CourseObstacleBase implements Collidable, Updatable {

    private Vector3f pos;
    private Quat4f rot;

    private boolean collisionEnabled;
    @Getter
    private boolean destroyed;

    CourseObstacleBase(Vector3f pos, Quat4f rot) {
        this.pos = pos;
        this.rot = rot;
        collisionEnabled = true;
        destroyed = false;
    }

    //Transformable
    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
    }


    //Collidable
    @Override
    public void enableCollision() {
        collisionEnabled = true;
    }

    @Override
    public void disableCollision() {
        collisionEnabled = false;
    }

    @Override
    public boolean collisionActivated() {
        return collisionEnabled;
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
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT) {
            disableCollision();
        } else if (collidable.getCollidableType() == CollidableType.HARMFUL) {
            disableCollision();
            destroyed = true;
        }
    }

    public abstract ObstacleType getObstacleType();

    @Override
    public void update(int milliSinceLastUpdate) {
    }
}
