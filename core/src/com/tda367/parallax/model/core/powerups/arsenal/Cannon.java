package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * The cannon PowerUp fires a cannon shot towards the direction it is pointed at.
 */

public class Cannon extends PowerUpBase {
    private static final String COLLISION_MODEL = "3dModels/box/hitbox.obj";
    private static final String SOUND_DIRECTORY = "sounds/effects";
    private static final int LIFE_LENGTH = 4000;

    private final Random rand = new Random();

    @Setter
    @Getter
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

        this.velocity = MathUtilities.rotateVectorByQuat(new Vector3f(0, 1, 0),
                new Quat4f(-1 * transformable.getRot().x
                        , transformable.getRot().y
                        , -1 * transformable.getRot().z
                        , transformable.getRot().w));
        this.velocity.scale(30);

        playCannonSound();
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        if (super.isActive()) {

            if (this.timeAlive > 150) {
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
                this.velocity.getX() * ((float) milliSinceLastUpdate / 1000),
                this.velocity.getY() * ((float) milliSinceLastUpdate / 1000),
                this.velocity.getZ() * ((float) milliSinceLastUpdate / 1000)
        ));
        this.setRot(MathUtilities.vectorToQuat(this.velocity));
    }

    private void playCannonSound() {
        final int randomSong = this.rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots, [Disabled]
        if (randomSong > 200) {
            AudioQueue.getInstance().playSound("cannonLow.mp3", SOUND_DIRECTORY, 0.3f);
        } else {
            AudioQueue.getInstance().playSound("cannon.mp3", SOUND_DIRECTORY, 0.8f);
        }
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.LAZER;
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (this.timeAlive > 250 && CollidableType.OBSTACLE == collidable.getCollidableType()) {
            this.die();
        }
    }
}

