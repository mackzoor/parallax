package com.tda367.parallax.model.core.collision;

import javax.vecmath.Vector3f;

/**
 * Object to store relevant data from a collision.
 */

public class CollisionResult {

    private final Collidable first;
    private final Collidable second;

    private final boolean collided;
    private final Vector3f contactPoint;
    private final Vector3f firstResultingImpulse;
    private final Vector3f secondResultingImpulse;

    public CollisionResult(Collidable first,
                           Collidable second,
                           boolean collided,
                           Vector3f contactPoint,
                           Vector3f firstResultingImpulse,
                           Vector3f secondResultingImpulse) {
        this.first = first;
        this.second = second;
        this.collided = collided;
        this.contactPoint = contactPoint;
        this.firstResultingImpulse = firstResultingImpulse;
        this.secondResultingImpulse = secondResultingImpulse;
    }


    public Collidable getFirst() {
        return this.first;
    }

    public Collidable getSecond() {
        return this.second;
    }

    public boolean isCollided() {
        return this.collided;
    }

    public Vector3f getContactPoint() {
        return this.contactPoint;
    }

    public Vector3f getFirstResultingImpulse() {
        return this.firstResultingImpulse;
    }

    public Vector3f getSecondResultingImpulse() {
        return this.secondResultingImpulse;
    }
}
