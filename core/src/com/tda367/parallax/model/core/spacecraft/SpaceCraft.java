package com.tda367.parallax.model.core.spacecraft;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;
import com.tda367.parallax.model.core.powerups.container.IContainer;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all spacecraft.
 */
public abstract class SpaceCraft implements ISpaceCraft {
    private static final int SEC_TO_MILLISEC = 1000;
    private static final int MAX_NUMBER_OF_POWER_UPS = 4;
    private static final float FLOAT_ROUNDING_COMPENSATOR = 49f / 50f;
    private static final float COURSE_RADIUS = 5;

    @Getter
    private List<IPowerUp> powerUps = new ArrayList<IPowerUp>();
    @Setter
    @Getter
    private int health;

    @Getter
    private float forwardVelocity;
    @Getter
    private float forwardTargetSpeed;
    @Getter
    private float forwardAcceleration;
    private boolean forwardRelativeVelocityMode;

    private float maxPanVelocity;
    private final Vector2f desiredPanVelocity;
    private Vector2f panAcceleration;
    @Getter
    @Setter
    private Vector2f currentPanVelocity;
    @Getter
    private final Vector2f panAbsoluteTarget;

    @Setter
    private boolean independentRotation;

    @Getter
    private final Vector3f pos;
    @Getter
    private Quat4f rot;

    private boolean collisionEnabled;
    private final SpaceCraftType type;

    SpaceCraft(SpaceCraftMobility mobility, int health, SpaceCraftType type) {

        this.health = health;
        this.type = type;
        this.forwardVelocity = mobility.getForwardVelocity();
        this.maxPanVelocity = mobility.getMaxPanVelocity();
        this.pos = mobility.getPos();
        this.rot = mobility.getRot();
        this.panAcceleration = new Vector2f();
        this.desiredPanVelocity = new Vector2f();
        this.powerUps = new ArrayList<IPowerUp>();
        this.independentRotation = mobility.isIndependentRotation();

        this.panAbsoluteTarget = new Vector2f();
        this.currentPanVelocity = new Vector2f();

        this.forwardRelativeVelocityMode = true;

        this.collisionEnabled = true;
    }


    @Override
    public void setDesiredPanVelocity(float x, float y) {
        this.setDesiredPanVelocity(new Vector2f(x, y));
    }

    public void setMaxPanVelocity(float panVelocity) {
        this.maxPanVelocity = panVelocity;
    }

    @Override
    public void setForwardSpeedTarget(float speed) {
        this.forwardTargetSpeed = speed;
        this.forwardRelativeVelocityMode = false;
    }

    @Override
    public void setForwardAcceleration(float accelerate) {
        this.forwardAcceleration = accelerate;
        this.forwardRelativeVelocityMode = true;
    }

    @Override
    public void enableIndipendantRotation(boolean value) {
        this.independentRotation = value;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return this.powerUps.size() > 0 ? this.powerUps.get(0).getPowerUpType() : null;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        accelerateCraft(milliSinceLastUpdate);
        updatePanAcceleration();
        panRelativeMode(milliSinceLastUpdate);
        advanceCraft(milliSinceLastUpdate);
        outOfBoundsCheck();
        if (!this.independentRotation) {
            rotateInCraftDirection();
        }
    }


    @Override
    public void setDesiredPanVelocity(Vector2f desiredPanVelocity) {
        if (isShipOutsideCourse()) {
            this.desiredPanVelocity.set(onWallSteering(desiredPanVelocity));
        } else {
            this.desiredPanVelocity.set(desiredPanVelocity);
        }
    }

    private Vector2f xzPos(Vector3f vector3f) {
        return new Vector2f(vector3f.getX(), vector3f.getZ());
    }

    private boolean isShipOutsideCourse() {
        final Vector2f vector2f = this.xzPos(getPos());
        return vector2f.length() > COURSE_RADIUS;
    }

    private Vector2f rotateNinetyDeg(Vector2f vector2f) {
        return new Vector2f(-vector2f.getY(),
                vector2f.getX());
    }

    private Vector2f onWallSteering(Vector2f desiredPanVelocity) {
        final Vector2f alongWallVec = this.rotateNinetyDeg(this.xzPos(getPos()));
        final float scalar = desiredPanVelocity.dot(alongWallVec) / alongWallVec.lengthSquared();
        desiredPanVelocity.scale(scalar, alongWallVec);
        return desiredPanVelocity;
    }

    private void outOfBoundsCheck() {
        if (this.isShipOutsideCourse()) {
            final Vector2f tempVec = this.xzPos(getPos());
            tempVec.normalize();
            tempVec.scale(FLOAT_ROUNDING_COMPENSATOR * COURSE_RADIUS);
            getPos().set(new Vector3f(tempVec.getX(), getPos().getY(), tempVec.getY()));
        }
    }

    private void updatePanAcceleration() {
        final Vector2f truePanVector = new Vector2f(this.desiredPanVelocity);
        truePanVector.scale(this.maxPanVelocity);

        truePanVector.sub(this.currentPanVelocity);
        truePanVector.scale(this.maxPanVelocity);

        this.panAcceleration = new Vector2f(truePanVector);
    }

    private void panRelativeMode(int timeMilli) {
        final Vector2f addedVelocity = new Vector2f(this.panAcceleration);
        addedVelocity.scale((float) timeMilli / SEC_TO_MILLISEC);

        this.currentPanVelocity.add(addedVelocity);
        this.currentPanVelocity.clamp(-this.maxPanVelocity, this.maxPanVelocity);


        final float addedXPos = distanceCalc(this.currentPanVelocity.getX(), timeMilli);
        final float addedZPos = distanceCalc(this.currentPanVelocity.getY(), timeMilli);

        this.pos.add(new Vector3f(addedXPos, 0, addedZPos));
    }

    private float distanceCalc(float speed, float timeMilli) {
        return speed * (timeMilli / SEC_TO_MILLISEC);
    }

    private void accelerateCraft(int timeMilli) {
        if (this.forwardRelativeVelocityMode) {
            this.forwardVelocity = this.forwardVelocity + this.forwardAcceleration * ((float) timeMilli / SEC_TO_MILLISEC);
        } else {
            if (this.forwardVelocity < this.forwardTargetSpeed) {
                final float speedIncrease = this.forwardVelocity + this.forwardAcceleration * ((float) timeMilli / SEC_TO_MILLISEC);

                if (this.forwardTargetSpeed < speedIncrease + this.forwardVelocity) {
                    this.forwardVelocity = this.forwardTargetSpeed;
                } else {
                    this.forwardVelocity += speedIncrease;
                }

            }
        }
    }

    private void advanceCraft(int timeMilli) {
        final float posYAdded = this.forwardVelocity * ((float) timeMilli / SEC_TO_MILLISEC);
        this.pos.add(new Vector3f(0, posYAdded, 0));
    }

    @Override
    public void setCurrentPanVelocity(float x, float y) {
        this.currentPanVelocity.x = x;
        this.currentPanVelocity.y = y;
    }

    @Override
    public void action() {
        if (!this.powerUps.isEmpty()) {
            final IPowerUp activePowerUP = this.powerUps.get(this.powerUps.size() - 1);

            activePowerUP.activate(this);
            this.powerUps.remove(this.powerUps.size() - 1);
        }
    }

    @Override
    public void add(IPowerUp powerUp) {
        if (this.powerUps.size() <= 0) {
            this.powerUps.add(powerUp);
        } else if (
                this.powerUps.get(0).getClass().equals(powerUp.getClass())
                        && this.powerUps.size() < MAX_NUMBER_OF_POWER_UPS) {
            this.powerUps.add(powerUp);
        }
    }

    @Override
    public void add(List<IPowerUp> listOfPowerUps) {
        for (final IPowerUp powerUp : listOfPowerUps) {
            this.add(powerUp);
        }
    }

    @Override
    public void incHealth() {
        this.health++;
    }

    @Override
    public void decHealth() {
        this.health--;
    }

    @Override
    public SpaceCraftType getType() {
        return this.type;
    }

    @Override
    public Vector2f getCurrentPanVelocity() {
        return this.currentPanVelocity;
    }


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
        CollisionManager.getInstance().addCollisionCheck(this);
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.SPACECRAFT;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() == CollidableType.OBSTACLE || collidable.getCollidableType() == CollidableType.HARMFUL) {
            this.decHealth();
        } else if (collidable.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.CONTAINER) {
            final IContainer container = (IContainer) collidable;
            this.add(container.getPowerUp());
        }
    }

    private void rotateInCraftDirection() {
        final Vector3f rotationDirection = new Vector3f(
                this.currentPanVelocity.getX() / 2f,
                this.forwardVelocity,
                this.currentPanVelocity.getY() / 2f
        );
        this.rot = MathUtilities.vectorToQuat(rotationDirection);
    }
}
