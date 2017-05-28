package com.tda367.parallax.model.core.world.courseobstacles;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import com.tda367.parallax.model.core.spacecraft.Agelion;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import org.junit.Test;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.*;


public class CourseObstacleBaseTest {

    CourseObstacleBase courseObstacle = new WallObstacle(new Vector3f(),new Quat4f());
    Agelion agelion = SpaceCraftFactory.getAgelionInstance(10);
    IPowerUp powerUp = PowerUpFactory.createRandomPowerUp();
    @Test

    public void handleCollision() throws Exception {
        courseObstacle.handleCollision(agelion);
        assertTrue(courseObstacle.isDestroyed());
        assertTrue(!courseObstacle.collisionActivated());

        courseObstacle.handleCollision(powerUp);
        assertFalse(courseObstacle.collisionActivated());
        assertTrue(courseObstacle.isDestroyed());
    }

}