package com.tda367.parallax.model.core.powerups.arsenal;


import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Vector3f;

public class Missile extends PowerUpBase {

     /*
        Variables for toggling the missile movement
    */

    //Time to represent the falling phase, value compared to the timeStorage variable
    private static final int FALL_TIME = 500;

    //Time to represent the active phase, value compared to the timeStorage variable
    private static final int ACTIVE_TIME = 5000;

    //Time that missile is tracking the ships movement, making sure that it doesn't go trough it.
    private static final int TIME_TRACKING_TRANS = 700;

    //Multiplier to change the speed of missile
    private static final float VELOCITY_MULTIPLIER_X = 10f;
    private static final float VELOCITY_MULTIPLIER_Y = 1f;
    private static final float VELOCITY_MULTIPLIER_Z = 10f;
    private static final float FALL_MULTIPLIER = 2.5f;

    private static final String COLLISION_MODEL = "3dModels/missile/hitbox.obj";

    //How fast the missile will accelerate
    private static final double ACCELERATION = 0.8;

    //Maximum velocity that the missile can reach.
    private static final float MAXIMUM_VELOCITY = 80;


    private float startVelocity;

    //Velocity that the missile is moving in.
    @Setter
    private float velocity;
    private int timeAccelerated;

    //Saving variables for the transformable object the missile has its origin from
    private Vector3f transformableEarlierPosition;
    private Transformable transformable;
    //Storing the position of the transformable object since last update, making it possible to track its movement
    private Vector3f transPosLastUpdate;

    private int timeStorage;

    @Getter
    private final Vector3f enemyTargetPosition;

    Missile() {
        super();
        this.enemyTargetPosition = new Vector3f();
        this.transPosLastUpdate = new Vector3f();
    }

    //IPowerUp
    @Override
    public void activate(Transformable transformable) {
        super.activate(transformable);
        super.addToCollisionManager();
        super.enableCollision();
        //Add the missile to the world and make it collidable

        this.transformable = transformable;


        //TODO, Temporary initialization for the enemy, will be controlled elsewhere later.
        this.enemyTargetPosition.set(new Vector3f(super.getPos()));
        this.enemyTargetPosition.add(new Vector3f(0, 100, 20));
        playMissileSound();
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
        if (this.timeStorage > TIME_TRACKING_TRANS) {
            return CollidableType.HARMFUL;
        } else {
            return CollidableType.NEUTRAL;
        }
    }

    @Override
    public void handleCollision(Collidable collidable) {
        //Todo Create explosion
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT && this.timeStorage > TIME_TRACKING_TRANS) {
            removeMissile();
        }
        if (collidable.getCollidableType() == CollidableType.OBSTACLE && this.timeStorage > 250) {
            removeMissile();
        }
    }


    //Usable
    @Override
    public void use() {
    }


    //TODO, remove the fact that missile moves faster towards things further away.

    //Updatable, things that control the missile each update
    @Override
    public void update(int milliSinceLastUpdate) {
        if (!super.isDead()) {
            if (super.isActive()) {
                this.timeStorage = this.timeStorage + milliSinceLastUpdate;

                //Checks to see that the missile has gotten a velocity before moving
                //Before testing velocity, check that velocity generation is allowed,
                //by using previous and current position.
                if (this.transformableEarlierPosition == null) {
                    this.transformableEarlierPosition = new Vector3f(this.transformable.getPos());
                    //Make sure that the missile has the same position as the ship after one cycle.
                    super.getPos().set(this.transformable.getPos());
                } else if (this.velocity == 0) {
                    //Generate velocity for the missile, based on the velocity of the transformable object
                    generateVelocity(this.transformableEarlierPosition,
                            this.transformable,
                            milliSinceLastUpdate);
                } else {
                    moveTheMissile(milliSinceLastUpdate,
                            this.timeStorage);
                }
            }
        }
    }


    //Methods for movement

    private void generateVelocity(Vector3f transformableEarlierPostion, Transformable transformable, int milliSinceLastUpdate) {
        setVelocity((transformable.getPos().getY() - transformableEarlierPostion.getY()) / ((float) milliSinceLastUpdate) * 1000);
    }

    private void moveTheMissile(int milliSinceLastUpdate, int timeStorage) {
        //Method for general missile movement, separated from update for nicer looking code.

        if (timeStorage <= TIME_TRACKING_TRANS) {
            if (this.transPosLastUpdate.getX() == 0
                    && this.transPosLastUpdate.getY() == 0
                    && this.transPosLastUpdate.getZ() == 0) {

                super.setPos(new Vector3f(this.transformable.getPos()));
                this.transPosLastUpdate = new Vector3f(this.transformable.getPos());
            } else {
                if (this.transPosLastUpdate.getZ() > this.transformable.getPos().getZ()) {
                    super.getPos().add(new Vector3f(0,
                            0,
                            this.transformable.getPos().getZ() - this.transPosLastUpdate.getZ()));
                }
            }
            this.transPosLastUpdate = new Vector3f(this.transformable.getPos());
        }

        //Check if the missile is in the falling phase, moving to target phase or if the missile should be removed.
        if (timeStorage <= FALL_TIME) {
            moveOnVelocity(milliSinceLastUpdate);
            fall(milliSinceLastUpdate);


        } else if (timeStorage <= ACTIVE_TIME) {
            super.enableCollision();
            accelerateMissile(milliSinceLastUpdate);
            moveOnVelocity(milliSinceLastUpdate);
            rotateMissile(getEnemyTargetPosition());

            if (timeStorage > TIME_TRACKING_TRANS) {
                final Vector3f directionalVector = generateDirectionVector(getEnemyTargetPosition());
                moveOnDirectionVector(directionalVector, milliSinceLastUpdate);
            }

        } else {
            removeMissile();
        }
    }

    private void accelerateMissile(int milliSinceLastUpdate) {

        this.timeAccelerated = this.timeAccelerated + milliSinceLastUpdate;

        if (this.startVelocity == 0) {
            this.startVelocity = this.velocity;
        }

        if (this.velocity < MAXIMUM_VELOCITY) {
            /*Calculate the velocity based on this formula: x(t) = x0 × (1 + r)^t
            x(t) is the value at time t.
            x0 is the initial value at time t=0.
            r is the growth rate when r>0 or decay rate when r<0, in percent.
            t is the time in discrete intervals and selected time units.
             */
            this.velocity = this.startVelocity * ((float) Math.pow(1 + ACCELERATION, ((double) this.timeAccelerated) / 1000));
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
        super.getPos().add(new Vector3f(0, 0, -(((float) milliSinceLastUpdate) / 1000) * FALL_MULTIPLIER));
    }

    private void moveOnVelocity(int milliSinceLastUpdate) {
        //Method for moving the missile according to the velocity

        final float posYAdded = this.velocity * ((float) milliSinceLastUpdate / 1000);
        super.getPos().add(new Vector3f(0, posYAdded, 0));
    }

    private void rotateMissile(Vector3f target) {
        //TODO rotate missile
    }

    //Method to generate a direction vector (normalized) for the ship's movement.
    private Vector3f generateDirectionVector(Vector3f target) {
        final Vector3f directionalVector = new Vector3f(target.getX() - getPos().getX(),
                target.getY() - getPos().getY(),
                target.getZ() - getPos().getZ());

        directionalVector.normalize();

        return directionalVector;
    }

    //Method for moving the ship based on the direction vector generated from method "generateDirectionalVector"
    private void moveOnDirectionVector(Vector3f directionalVector, int milliSinceLastUpdate) {
        getPos().add(new Vector3f((directionalVector.getX() * (float) milliSinceLastUpdate / 1000) * VELOCITY_MULTIPLIER_X,
                (directionalVector.getY() * (float) milliSinceLastUpdate / 1000) * VELOCITY_MULTIPLIER_Y,
                (directionalVector.getZ() * (float) milliSinceLastUpdate / 1000) * VELOCITY_MULTIPLIER_Z));
        this.setRot(MathUtilities.vectorToQuat(directionalVector));
    }


    //Sound

    private void playMissileSound() {
        AudioQueue.getInstance().playSound("MissileDemo.mp3", "sounds/effects", 0.7f);
    }

}
