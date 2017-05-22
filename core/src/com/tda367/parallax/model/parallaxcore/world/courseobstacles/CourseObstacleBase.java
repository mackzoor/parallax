package com.tda367.parallax.model.parallaxcore.world.courseobstacles;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.world.ICourseModule;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Base class for all course obstacles in {@link ICourseModule}'s.
 */
public abstract class CourseObstacleBase implements Collidable{

    private Vector3f pos;
    private Quat4f rot;

    private boolean collisionEnabled;

    CourseObstacleBase(Vector3f pos, Quat4f rot){
        this.pos = pos;
        this.rot = rot;
        collisionEnabled = true;
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
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT){
            disableCollision();
        }
    }

    public abstract ObstacleType getObstacleType();
}