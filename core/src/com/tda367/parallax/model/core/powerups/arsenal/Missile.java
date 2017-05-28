package com.tda367.parallax.model.core.powerups.arsenal;


import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Vector3f;

import static com.tda367.parallax.model.core.collision.CollidableType.HARMFUL;
import static com.tda367.parallax.model.core.collision.CollidableType.NEUTRAL;

public class Missile extends PowerUpBase {
    private static final int SEC_TO_MILLISEC = 1000;
    private static final int FALL_TIME = 500;
    private static final int ACTIVE_TIME = 5000;
    private static final int TIME_TRACKING_TRANS = 700;
    private static final float ACCELERATION = 0.8f;
    private static final float MAXIMUM_VELOCITY = 80;
    private static final float VELOCITY_MULTIPLIER_X = 10f;
    private static final float VELOCITY_MULTIPLIER_Y = 1f;
    private static final float VELOCITY_MULTIPLIER_Z = 10f;
    private static final float FALL_MULTIPLIER = 2.5f;
    private static final Vector3f MISSILE_TARGET =  new Vector3f(0, 100, 0);
    private static final String COLLISION_MODEL = "3dModels/missile/hitbox.obj";

    @Setter
    private float velocity;
    private float startVelocity;

    private int timeAccelerated;
    private int timeStorage;

    private Transformable transformable;
    private Vector3f posLastUpdate;
    @Getter
    private final Vector3f enemyTargetPosition;

    Missile() {
        super();
        this.enemyTargetPosition = new Vector3f();
    }

    @Override
    public void activate(Transformable transformable) {
        super.activate(transformable);
        super.addToCollisionManager();
        super.enableCollision();
        this.transformable = transformable;


        this.enemyTargetPosition.set(new Vector3f(super.getPos()));
        this.enemyTargetPosition.add(MISSILE_TARGET);

        this.posLastUpdate = new Vector3f(transformable.getPos());
        super.getPos().set(this.transformable.getPos());
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.MISSILE;
    }

    @Override
    public CollidableType getCollidableType() {
        return  this.timeStorage > TIME_TRACKING_TRANS ? HARMFUL : NEUTRAL;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT && this.timeStorage > TIME_TRACKING_TRANS) {
            removeMissile();
        }
        if (collidable.getCollidableType() == CollidableType.OBSTACLE && this.timeStorage > 250) {
            removeMissile();
        }
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        if (!super.isDead() && super.isActive()) {
            this.timeStorage = this.timeStorage + milliSinceLastUpdate;
            if (this.velocity == 0) {
                generateVelocity(this.posLastUpdate,
                        this.transformable.getPos(),
                        milliSinceLastUpdate);
            } else {
                moveTheMissile(milliSinceLastUpdate, this.timeStorage);
            }
            this.posLastUpdate = new Vector3f(this.transformable.getPos());
        }
    }

    private void generateVelocity(Vector3f firstPosition, Vector3f secondPosition, int milliSinceLastUpdate) {
        setVelocity((secondPosition.getY() - firstPosition.getY()) / ((float) milliSinceLastUpdate) * SEC_TO_MILLISEC);
    }

    private void moveTheMissile(int milliSinceLastUpdate, int timeStorage) {
        if (timeStorage <= TIME_TRACKING_TRANS
                && this.posLastUpdate.getZ() > this.transformable.getPos().getZ()) {
            super.getPos().add(new Vector3f(0, 0,
                    this.transformable.getPos().getZ() - this.posLastUpdate.getZ())
            );
        }
        if (timeStorage <= FALL_TIME) {
            this.duringFallTime(milliSinceLastUpdate);
        } else if (timeStorage <= ACTIVE_TIME) {
            this.duringActiveTime(milliSinceLastUpdate);
            if (timeStorage > TIME_TRACKING_TRANS) {
                final Vector3f directionalVector = generateDirectionVector(getEnemyTargetPosition());
                moveOnDirectionVector(directionalVector, milliSinceLastUpdate);
            }

        } else {
            removeMissile();
        }
    }

    private void duringFallTime(int milliSinceLastUpdate) {
        moveOnVelocity(milliSinceLastUpdate);
        fall(milliSinceLastUpdate);
    }

    private void duringActiveTime(int milliSinceLastUpdate) {
        super.enableCollision();
        accelerateMissile(milliSinceLastUpdate);
        moveOnVelocity(milliSinceLastUpdate);
    }

    private void accelerateMissile(int milliSinceLastUpdate) {

        this.timeAccelerated = this.timeAccelerated + milliSinceLastUpdate;

        if (this.startVelocity == 0) {
            this.startVelocity = this.velocity;
        }

        if (this.velocity < MAXIMUM_VELOCITY) {
            this.velocity = this.startVelocity * ((float) Math.pow(1 + ACCELERATION, ((double) this.timeAccelerated) / SEC_TO_MILLISEC));
        } else {
            this.velocity = MAXIMUM_VELOCITY;
        }
    }

    private void removeMissile() {
        removeFromCollisionManager();
        super.setDead(true);
        super.setActive(false);
        super.setCollisionEnabled(false);
    }

    private void fall(int milliSinceLastUpdate) {
        super.getPos().add(new Vector3f(0, 0, -(((float) milliSinceLastUpdate) / SEC_TO_MILLISEC) * FALL_MULTIPLIER));
    }

    private void moveOnVelocity(int milliSinceLastUpdate) {
        final float posYAdded = this.velocity * ((float) milliSinceLastUpdate / SEC_TO_MILLISEC);
        super.getPos().add(new Vector3f(0, posYAdded, 0));
    }

    private Vector3f generateDirectionVector(Vector3f target) {
        final Vector3f directionalVector = new Vector3f(target.getX() - getPos().getX(),
                target.getY() - getPos().getY(),
                target.getZ() - getPos().getZ());

        directionalVector.normalize();
        return directionalVector;
    }

    private void moveOnDirectionVector(Vector3f directionalVector, int milliSinceLastUpdate) {
        getPos().add(new Vector3f((directionalVector.getX() * (float) milliSinceLastUpdate / SEC_TO_MILLISEC) * VELOCITY_MULTIPLIER_X,
                (directionalVector.getY() * (float) milliSinceLastUpdate / SEC_TO_MILLISEC) * VELOCITY_MULTIPLIER_Y,
                (directionalVector.getZ() * (float) milliSinceLastUpdate / SEC_TO_MILLISEC) * VELOCITY_MULTIPLIER_Z));
        this.setRot(MathUtilities.vectorToQuat(directionalVector));
    }
}
