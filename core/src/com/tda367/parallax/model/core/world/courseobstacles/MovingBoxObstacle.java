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
            Random r = new Random();
            this.velocity = new Vector3f(
                    r.nextFloat() * 2 - 1,
                    r.nextFloat() * 2 - 1,
                    r.nextFloat() * 2 - 1
            );

            this.angularRotation = new Quat4f(
                    r.nextFloat(),
                    r.nextFloat(),
                    r.nextFloat(),
                    r.nextFloat()
            );
            this.angularRotation.normalize();

            this.velocity.scale(0.01f);

            //TODO Lower the ludicrous speed on rotations
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
