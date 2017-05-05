package com.tda367.parallax.platform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.tda367.parallax.platform.GameStateManager.State.MAIN_MENU;
import static org.junit.Assert.*;


public class GameStateManagerTest {

    GameStateManager.State state;
    GameStateManager gameStateManager;

    @Test
    public void getState() throws Exception {
        assertTrue(MAIN_MENU == GameStateManager.getInstance().getState());
    }

    @Test
    public void render() throws Exception {
    }

    @Test
    public void setState() throws Exception {
        state = MAIN_MENU;
        GameStateManager.getInstance().setState(MAIN_MENU);
        assertTrue(GameStateManager.getInstance().getState() == state);

    }

    @Test
    public void dispose() throws Exception {
    }

}