package com.tda367.parallax.parallaxCore.spaceCraft;


import com.tda367.parallax.model.parallaxCore.spaceCraft.Agelion;
import org.junit.Before;
import org.junit.Test;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by xoxLU on 2017-04-07.
 */
public class AgelionTest {

    Agelion agilion;

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

    //Test to make sure that the method setPanAbsoluteTarget() changes its value then added a new Vector.
    @Test
    public void setPanPoint() throws Exception {
        Vector2f vec = agilion.getPanAbsoluteTarget();

        agilion.setPanAbsoluteTarget(new Vector2f(1, 1));

        assertTrue(!agilion.getPanAbsoluteTarget().equals(vec));
    }

    @Test
    public void setPanVelocity() throws Exception {
    }

    //Test to check if addPanPoint(0, 1) adds one to the y coordinate.
    @Test
    public void addPanPoint() throws Exception {
        Vector2f vec = new Vector2f(agilion.getPanAbsoluteTarget().getX(), agilion.getPanAbsoluteTarget().getY());

        agilion.offsetAbsolutePanTarget(new Vector2f(0, 1));

        assertTrue(!(agilion.getPanAbsoluteTarget().getX() == vec.getX() && agilion.getPanAbsoluteTarget().getY() == vec.getY()));

        assertTrue(agilion.getPanAbsoluteTarget().equals(new Vector2f(vec.getX(), vec.getY() + 1)));
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
    public void updatePanning(){
        //Saving original position
        Vector3f pos = agilion.getPos();

        //Sets the position we want the ship to pan to. Also set that speed to 1.
        agilion.setMaxPanVelocity(1);
        agilion.setPanAbsoluteTarget(new Vector2f(10,10));

        //Calls the update method and says that 1 second has passed
        agilion.update(1000);

        //Check to see that the ship has panned in at all
        assertFalse(pos.getX() == agilion.getPos().getX() && pos.getZ() == agilion.getPos().getZ());

        //Checks that the ship has panned to the right position after another 9s
        agilion.update(9000);
        assertTrue(agilion.getPos().getX() == 10 && agilion.getPos().getZ() == 10);

        //Checks that it doesn't keep panning after reaching its target, adds another second
        agilion.update(1000);
        assertTrue(agilion.getPos().getX() == 10 && agilion.getPos().getZ() == 10);

        //testa add metoden

        /*
        agilion.addPanPoint(new Vector2f(10,10));
        agilion.setAccelerateTarget(1);
        agilion.setForwardSpeedTarget(4);
        */

        //agilion.setMaxPanVelocity(new Vector2f(1,1));
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