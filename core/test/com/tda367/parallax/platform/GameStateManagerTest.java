package com.tda367.parallax.platform;

import org.junit.Test;

import static com.tda367.parallax.platform.GameStateManager.State.MAIN_MENU;
import static org.junit.Assert.*;

public class GameStateManagerTest {

    private GameStateManager.State state;

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