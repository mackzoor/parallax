package com.tda367.parallax.model.core.world.courseobstacles;

import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.powerups.arsenal.Cannon;
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
    IPowerUp powerUp = PowerUpFactory.createRandom();
    @Test
    public void handleCollision() throws Exception {
        courseObstacle.handleCollision(powerUp);
        powerUp.handleCollision(courseObstacle);
        assertTrue(courseObstacle.isDestroyed());
        assertTrue(!courseObstacle.collisionActivated());

        System.out.println(agelion.getHealth());
        int health = agelion.getHealth();
        courseObstacle.handleCollision(agelion);
        agelion.handleCollision(courseObstacle);
        System.out.println(agelion.getHealth());
        assertTrue(!courseObstacle.collisionActivated());
        assertTrue(agelion.getHealth() < health);
    }

}