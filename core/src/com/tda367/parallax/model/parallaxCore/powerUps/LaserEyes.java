
package com.tda367.parallax.model.parallaxCore.powerUps;
import com.tda367.parallax.model.CoreAbstraction.Model;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


/**
 * LaserEyes fires a laserbeam that is controlled by the player using google cardboard.
 */

public class LaserEyes implements IPowerUp {


    public void usePU(Vector3f pos, Quat4f rot) {

    }

    @Override
    public void activate() {

    }

    @Override
    public void update(int milliSinceLastUpdate) {

    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void disableCollision() {

    }

    @Override
    public void enableCollision() {

    }

    @Override
    public Model getCollisionModel() {
        return null;
    }

    @Override
    public Vector3f getPos() {
        return null;
    }

    @Override
    public Quat4f getRot() {
        return null;
    }

    @Override
    public void addToRenderManager() {

    }

    @Override
    public void removeFromRenderManager() {

    }
}
