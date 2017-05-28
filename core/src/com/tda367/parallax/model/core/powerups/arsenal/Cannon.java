package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * The cannon PowerUp fires a cannon shot towards the direction it is pointed at.
 */

public class Cannon extends PowerUpBase {
    private static final String COLLISION_MODEL = "3dModels/laser/hitbox.obj";
    private static final int SEC_TO_MILLISEC = 1000;
    private static final int LIFE_LENGTH = 4000;
    private static final float SPEED = 60;
    private static final float ACTIVATION_TIME = 150;

    @Getter
    @Setter
    private Vector3f velocity;

    @Getter
    @Setter
    private Vector3f enemyTargetPosition;

    private int timeAlive;

    Cannon() {
        super();
        this.timeAlive = 0;
        this.velocity = new Vector3f();
        this.enemyTargetPosition = new Vector3f();
    }

    @Override
    public void activate(Transformable transformable) {
        super.activate(transformable);
        super.addToCollisionManager();

        super.setPos(new Vector3f(transformable.getPos()));
        super.getPos().add(new Vector3f(0, 1, 0));

        this.velocity = MathUtilities.rotateVectorByQuat(
                new Vector3f(0, 1, 0),
                new Quat4f(-1 * transformable.getRot().x,
                transformable.getRot().y ,
                transformable.getRot().z * -1,
                transformable.getRot().w)
        );
        this.velocity.scale(SPEED);
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        if (super.isActive()) {

            if (this.timeAlive > ACTIVATION_TIME) {
                super.enableCollision();
            }
            this.timeAlive += milliSinceLastUpdate;
            updatePosition(milliSinceLastUpdate);
        }
        if (this.timeAlive > LIFE_LENGTH) {
            die();
        }
    }

    private void die() {
        super.disableCollision();
        super.removeFromCollisionManager();
        super.setActive(false);
        super.setDead(true);
    }

    private void updatePosition(int milliSinceLastUpdate) {
        super.getPos().add(new Vector3f(
                this.velocity.getX() * ((float) milliSinceLastUpdate / SEC_TO_MILLISEC),
                this.velocity.getY() * ((float) milliSinceLastUpdate / SEC_TO_MILLISEC),
                this.velocity.getZ() * ((float) milliSinceLastUpdate / SEC_TO_MILLISEC)
        ));
        this.setRot(MathUtilities.vectorToQuat(this.velocity));
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.CANNON;
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (this.timeAlive > ACTIVATION_TIME && CollidableType.OBSTACLE == collidable.getCollidableType()) {
            this.die();
        }
    }
}

