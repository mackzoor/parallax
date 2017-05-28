package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.spacecraft.Agelion;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.core.world.courseobstacles.ObstacleFactory;
import com.tda367.parallax.model.core.world.courseobstacles.WallObstacle;
import org.junit.Before;
import org.junit.Test;

import javax.vecmath.Vector3f;

import static org.junit.Assert.*;


public class CannonTest {

    Cannon cannon = PowerUpFactory.createCannon();
    Cannon cannon2 = PowerUpFactory.createCannon();
    Agelion agelion = SpaceCraftFactory.getAgelionInstance(10);
    WallObstacle wallObstacle = ObstacleFactory.getRandomWallInstance(new Vector3f());

    @Before
    public void activate() throws Exception {
        cannon.activate(agelion);
        assertTrue(cannon.isActive());
        assertFalse(cannon.isCollisionEnabled());
        assertTrue(cannon.getVelocity().y != 0);
    }


    @Test
    public void update() throws Exception {
        cannon.activate(agelion);
        Vector3f pos = cannon2.getPos();
        cannon2.activate(agelion);
        cannon2.setVelocity(new Vector3f(1,1,1));
        cannon2.update(4000);
        Vector3f pos2 = cannon2.getPos();
        assertFalse(pos.equals(pos2));
        assertTrue(!cannon2.isDead());
        cannon.update(4001);
        assertTrue(cannon.isDead());
    }

    @Test
    public void handleCollision() throws Exception {
        cannon.activate(agelion);
        cannon.handleCollision(wallObstacle);
        assertFalse(cannon.isDead());
        cannon.update(251);
        cannon.handleCollision(wallObstacle);
        assertTrue(cannon.isDead());

    }

}