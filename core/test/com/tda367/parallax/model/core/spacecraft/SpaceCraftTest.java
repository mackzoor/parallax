package com.tda367.parallax.model.core.spacecraft;

import com.tda367.parallax.model.core.powerups.arsenal.Cannon;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import com.tda367.parallax.model.core.powerups.container.Container;
import com.tda367.parallax.model.core.world.courseobstacles.ObstacleFactory;
import org.junit.Test;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.*;


public class SpaceCraftTest {

    SpaceCraft spaceCraft = SpaceCraftFactory.getAgelionInstance(10);


    @Test
    public void update() throws Exception {
        spaceCraft.getPos().set(500,0,500);
        spaceCraft.update(1000);
        assertTrue(spaceCraft.getPos().z < 5 && spaceCraft.getPos().x < 5);

        spaceCraft.setForwardAcceleration(10);
        spaceCraft.update(1000);
        assertTrue(spaceCraft.getForwardVelocity() == 20);

        spaceCraft.getPos().set(500,0,0);
    }

    @Test
    public void action() throws Exception {
        spaceCraft.add(PowerUpFactory.createCannon());
        spaceCraft.add(PowerUpFactory.createCannon());
        int size1 = spaceCraft.getPowerUps().size();
        IPowerUp pu = spaceCraft.getPowerUps().get(1);
        spaceCraft.action();
        assertTrue(pu.isActive());
        int size2 = spaceCraft.getPowerUps().size();
        assertTrue(size1 > size2);

    }

    @Test
    public void add() throws Exception {
        spaceCraft.add(PowerUpFactory.createCannon());
        spaceCraft.add(PowerUpFactory.createMissile());
        spaceCraft.add(PowerUpFactory.createMissile());
        spaceCraft.add(PowerUpFactory.createCannon());
        assertTrue(spaceCraft.getPowerUps().size() == 2);

    }

    @Test
    public void handleCollision() throws Exception {
        int health = spaceCraft.getHealth();
        spaceCraft.handleCollision(ObstacleFactory.getRandomWallInstance(new Vector3f()));
        int health2 = spaceCraft.getHealth();
        assertTrue(health > health2);

        spaceCraft.add(PowerUpFactory.createCannon());
        int puSize = spaceCraft.getPowerUps().size();
        spaceCraft.handleCollision(new Container(PowerUpFactory.createCannon()));
        int puSize2 = spaceCraft.getPowerUps().size();
        assertTrue(puSize2 > puSize);
        assertTrue(spaceCraft.getPowerUps().get(1) instanceof Cannon);
    }

    @Test
    public void setDesiredPanVelocity() throws Exception {
        spaceCraft.getPos().set(500,0,0);
        Vector2f vector2f = new Vector2f(1,1);
        spaceCraft.setDesiredPanVelocity(vector2f);
        System.out.println(vector2f);
        assertTrue(vector2f.x <= 0);

    }

}