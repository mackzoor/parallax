package com.tda367.parallax.parallaxcore.spacecraft;

import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import org.junit.Before;
import org.junit.Test;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AgelionTest {

    private Agelion agilion;

    @Before
    public void setUp() throws Exception {
        agilion = new Agelion(5, 0, 0, new Vector3f(0,0,0), new Quat4f());
    }

    //Test to make sure that the setForwardSpeedTarget() method does what it should (setting the speed to a float value) and is not being interfered with at a different location.
    @Test
    public void setSpeedTarget() throws Exception {
        float previousSpeed = agilion.getForwardTargetSpeed();

        float addedSpeed = 50;

        agilion.setForwardSpeedTarget(addedSpeed);

        assertTrue(previousSpeed + addedSpeed == agilion.getForwardTargetSpeed());
    }

    //Test to make sure that the setAccelerationTarget() method does what it should (setting the Acceleration to a float value) and is not being interfered with at a different location.
    @Test
    public void setAccelerateTarget() throws Exception {/*
        float previousAcceleration = agilion.getForwardAcceleration();

        float addedAcc = 50;

        agilion.setForwardAcceleration(addedAcc);

        assertTrue(previousAcceleration + addedAcc == agilion.getForwardAcceleration());
*/    }


    @Test
    public void setPanVelocity() throws Exception {
    }

    @Test
    public void incHealth(){
        int health = agilion.getHealth();

        agilion.incHealth();

        assertTrue(agilion.getHealth() == health + 1);
    }

    @Test
    public void action() {
    }

    @Test
    public void updateAdvancing(){
        //Saving original position
        Vector3f pos = new Vector3f(agilion.getPos().getX(), agilion.getPos().getY(), agilion.getPos().getZ() );

        //Sets the speed we want to achieve to 4, and the acceleration to 1.
        agilion.setForwardAcceleration(1);
        agilion.setForwardSpeedTarget(4);

        //Calls the update method and says that 1 second has passed
        agilion.update(1000);

        //Checks if the ship moves at all
        assertTrue(!(pos.getY() == agilion.getPos().getY()));

        //Checks that the ship has advanced to the right position after another 9s
        agilion.update(9000);
        assertTrue(agilion.getPos().getY() == 37);
    }

}