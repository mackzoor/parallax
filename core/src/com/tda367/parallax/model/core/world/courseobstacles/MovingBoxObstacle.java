package com.tda367.parallax.model.core.world.courseobstacles;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * A {@link BoxObstacle} that moves and rotates.
 */
public class MovingBoxObstacle extends BoxObstacle {

    private static final float TRANS_SPEED_MODIFIER = 0.01f;
    private static final float ROTATION_SPEED_MODIFIER = 0.95f;

    private Vector3f velocity;
    private Quat4f angularRotation;

    MovingBoxObstacle(Vector3f pos, Quat4f rot, Vector3f velocity, Quat4f angularRotation) {
        super(pos, rot);

        this.velocity = velocity;
        this.angularRotation = angularRotation;
    }

    MovingBoxObstacle(Vector3f pos, Quat4f rot, boolean random) {
        super(pos, rot);

        if (random) {
            final Random rand = new Random();
            this.velocity = new Vector3f(
                    rand.nextFloat() * 2 - 1,
                    rand.nextFloat() * 2 - 1,
                    rand.nextFloat() * 2 - 1
            );

            this.angularRotation = new Quat4f(
                    rand.nextFloat(),
                    rand.nextFloat(),
                    rand.nextFloat(),
                    rand.nextFloat()
            );
            this.angularRotation.normalize();

            this.velocity.scale(TRANS_SPEED_MODIFIER);

            this.angularRotation.interpolate(new Quat4f(0, 0, 0, 1), ROTATION_SPEED_MODIFIER);

        } else {
            this.velocity = new Vector3f();
            this.angularRotation = new Quat4f();
        }
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        super.update(milliSinceLastUpdate);

        super.getRot().mul(this.angularRotation);
        super.getPos().add(this.velocity);
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT) {
            super.handleCollision(collidable);
        } else {
            this.velocity.scale(-1);
        }
    }
}
