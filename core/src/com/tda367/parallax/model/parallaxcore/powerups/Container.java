package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.util.Updatable;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Container that holds a {@link IPowerUp}.
 */
public class Container implements Collidable, Updatable, IContainer {

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
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT) {
            isCollected = true;
            removeFromCollisionManager();
        }
    }

    @Override
    public IPowerUp getPowerUp() {
        return usable;
    }
}
