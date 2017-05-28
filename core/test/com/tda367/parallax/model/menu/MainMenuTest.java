package com.tda367.parallax.model.menu;

import com.tda367.parallax.model.core.powerups.arsenal.Cannon;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import org.junit.Test;

import static org.junit.Assert.*;


public class MainMenuTest {

    MainMenu mainMenu = new MainMenu();

    @Test
    public void action() throws Exception {
        mainMenu.action();
        assertTrue(mainMenu.getPowerUps().size() == 1);
        assertTrue(mainMenu.getPowerUps().get(0) instanceof Cannon);
        assertTrue(mainMenu.getPowerUps().get(0).isActive());
    }

    @Test
    public void update() throws Exception {
        mainMenu.getStartButton().handleCollision(PowerUpFactory.createCannon());
        assertTrue(mainMenu.getStartButton().isCollided());
    }

    @Test
    public void respondToCollision() throws Exception {
    }

}