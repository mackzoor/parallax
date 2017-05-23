package com.tda367.parallax.model.core.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Base class for all course obstacles in {@link com.tda367.parallax.model.core.world.ICourseModule}'s.
 */
public abstract class CourseObstacleBase implements com.tda367.parallax.model.core.collision.Collidable {

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
        com.tda367.parallax.model.core.collision.CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public void removeFromCollisionManager() {
        com.tda367.parallax.model.core.collision.CollisionManager.getInstance().removeCollisionCheck(this);
    }
    @Override
    public abstract String getCollisionModelPath();
    @Override
    public com.tda367.parallax.model.core.collision.CollidableType getCollidableType() {
        return com.tda367.parallax.model.core.collision.CollidableType.OBSTACLE;
    }
    @Override
    public void handleCollision(com.tda367.parallax.model.core.collision.Collidable collidable) {
        if (collidable.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.SPACECRAFT){
            disableCollision();
        }
    }

    public abstract ObstacleType getObstacleType();
}
