package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.util.Transformable;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Base class for all powerups.
 */
public abstract class PowerUpBase implements IPowerUp {

    @Getter
    @Setter
    private Vector3f pos;
    @Getter
    @Setter
    private Quat4f rot;

    //Flags for if the ship should collide, move and a general variable for its lifecycle
    @Getter
    @Setter
    private boolean collisionEnabled;
    @Getter
    @Setter
    private boolean isDead;
    @Getter
    @Setter
    private boolean isActive;

    PowerUpBase() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.isDead = false;
        this.collisionEnabled = false;
        this.isActive = false;
    }

    @Override
    public abstract void update(int milliSinceLastUpdate);

    @Override
    public void activate(Transformable transformable) {
        this.pos = new Vector3f(transformable.getPos());
        this.rot = new Quat4f(transformable.getRot());
        this.isActive = true;
    }

    @Override
    public abstract void use();

    @Override
    public abstract CollidableType getCollidableType();

    @Override
    public abstract void handleCollision(Collidable collidable);

    @Override
    public abstract String getCollisionModelPath();


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
}
