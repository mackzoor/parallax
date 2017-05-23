package com.tda367.parallax.platform.gamepads;

import org.junit.Test;
import static org.junit.Assert.*;

public class GamePadFactoryTest {
    @Test
    public void getGamePad() throws Exception {
        com.tda367.parallax.controller.inputhandlers.gamepads.GamePad factoryPlaystation3GamePad = com.tda367.parallax.controller.inputhandlers.gamepads.GamePadFactory.getGamePad("Sony PLAYSTATION(R)3 Controller");
        com.tda367.parallax.controller.inputhandlers.gamepads.GamePad factoryXbox360GamePad = com.tda367.parallax.controller.inputhandlers.gamepads.GamePadFactory.getGamePad("XBOX360");
        com.tda367.parallax.controller.inputhandlers.gamepads.GamePad factoryAndroidGamePad = com.tda367.parallax.controller.inputhandlers.gamepads.GamePadFactory.getGamePad("RL_android_bluetooth_game_pad");
        com.tda367.parallax.controller.inputhandlers.gamepads.GamePad playstation3GamePad = new com.tda367.parallax.controller.inputhandlers.gamepads.Playstation3GamePad();
        com.tda367.parallax.controller.inputhandlers.gamepads.GamePad xbox360GamePad = new com.tda367.parallax.controller.inputhandlers.gamepads.Xbox360GamePad();
        com.tda367.parallax.controller.inputhandlers.gamepads.GamePad androidGamePad = new com.tda367.parallax.controller.inputhandlers.gamepads.AndroidGamePad();
        assertTrue(playstation3GamePad.equals(factoryPlaystation3GamePad));
        assertTrue(xbox360GamePad.equals(factoryXbox360GamePad));
        assertTrue(androidGamePad.equals(factoryAndroidGamePad));
    }

}