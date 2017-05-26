package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.Agelion;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.core.world.courseobstacles.ObstacleFactory;
import com.tda367.parallax.model.core.world.courseobstacles.WallObstacle;
import org.junit.Before;
import org.junit.Test;

import javax.vecmath.Vector3f;

import static org.junit.Assert.*;


public class MissileTest {
    Missile missile = PowerUpFactory.createMissile();
    WallObstacle wall = ObstacleFactory.getRandomWallInstance(new Vector3f());
    Agelion agelion = SpaceCraftFactory.getAgelionInstance(10);

    @Test
    public void activate() throws Exception {
        missile.activate(agelion);
        assertTrue(missile.isActive());
        assertFalse(missile.isDead());
    }

    @Test
    public void handleCollision() throws Exception {
        missile.activate(agelion);
        missile.handleCollision(wall);
        assertFalse(missile.isDead());
        assertTrue(missile.isCollisionEnabled());
        assertTrue(missile.isActive());
        assertTrue(CollisionManager.getInstance().getCollidables().contains(missile));

        missile.update(1000);
        missile.handleCollision(wall);
        assertTrue(missile.isDead());
        System.out.println(missile.isCollisionEnabled());
        assertFalse(missile.isCollisionEnabled());
        assertFalse(missile.isActive());
        assertFalse(CollisionManager.getInstance().getCollidables().contains(missile));


    }

    @Test
    public void update() throws Exception {
        missile.setVelocity(10);
        missile.activate(agelion);
        CollidableType type = missile.getCollidableType();
        Vector3f pos = missile.getPos();
        Vector3f underPos = missile.getPos();

        missile.update(901);
        CollidableType type1 = missile.getCollidableType();
        Vector3f pos1 = missile.getPos();
        assertTrue(type != type1);
        assertTrue(pos.x != pos1.x && pos.y != pos1.y);


    }


}