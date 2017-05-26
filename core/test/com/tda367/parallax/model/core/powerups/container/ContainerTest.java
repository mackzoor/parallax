package com.tda367.parallax.model.core.powerups.container;

import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import com.tda367.parallax.model.core.spacecraft.Agelion;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import org.junit.Test;

import static org.junit.Assert.*;


public class ContainerTest {

    Container container = (Container)ContainerFactory.createContainer(PowerUpFactory.createCannon());
    Agelion agelion = SpaceCraftFactory.getAgelionInstance(10);

    @Test
    public void handleCollision() throws Exception {
        container.handleCollision(agelion);
        assertTrue(container.isCollected());
        assertFalse(container.collisionActivated());
    }

}