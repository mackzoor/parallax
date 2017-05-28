package com.tda367.parallax.model.core;

import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.core.util.Updatable;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A virtual camera with capabilities to track.
 * {@link Transformable}
 */

public class Camera implements Updatable, Transformable {

    private static final float DEFAULT_FOV = 90;
    private static final Vector3f DEFAULT_POSITION = new Vector3f(0, 0, 1);
    private static final Quat4f DEFAULT_ROTATION = new Quat4f();
    private static final float DISTANCE_BEHIND_TRACKING_TARGET = 4;
    private static final float DISTANCE_ABOVE_TRACKING_TARGET = 1;

    private Vector3f pos;
    private Quat4f rot;
    @Getter
    private float fov;

    private Transformable trackingTarget;

    public Camera(Vector3f pos, Quat4f rot, float fov) {
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
    }

    public Camera() {
        this(new Vector3f(DEFAULT_POSITION), DEFAULT_ROTATION, DEFAULT_FOV);
    }


    void trackTo(Transformable transformable) {
        this.trackingTarget = transformable;
    }

    private void updatePosition() {
        this.pos.set(this.trackingTarget.getPos().getX() / 2,
                this.trackingTarget.getPos().getY() - DISTANCE_BEHIND_TRACKING_TARGET,
                this.trackingTarget.getPos().getZ() / 2 + DISTANCE_ABOVE_TRACKING_TARGET);
    }


    @Override
    public Vector3f getPos() {
        return this.pos;
    }

    @Override
    public Quat4f getRot() {
        return this.rot;
    }


    @Override
    public void update(int milliSinceLastUpdate) {
        this.updatePosition();
    }
}
