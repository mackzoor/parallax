package com.tda367.parallax.platform;

import org.junit.Test;

import static com.tda367.parallax.platform.GameStateManager.State.MAIN_MENU;
import static org.junit.Assert.*;


public class GameStateManagerTest {


    GameStateManager gameStateManager = GameStateManager.getInstance();

    @Test
    public void getState() throws Exception {
        gameStateManager.setState(MAIN_MENU);
        assertTrue(MAIN_MENU == gameStateManager.getState());
    }

    @Test
    public void render() throws Exception {
    }

    @Test
    public void setState() throws Exception {
        gameStateManager.setState(MAIN_MENU);
        System.out.println(gameStateManager.getState().toString());
        assertTrue(gameStateManager.getState() == MAIN_MENU);

    }

    @Test
    public void dispose() throws Exception {
    }

}