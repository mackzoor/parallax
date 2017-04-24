package com.tda367.parallax.platform.gamePadController.gamePads.GamePadsTest;

import com.tda367.parallax.platform.gamePadController.gamePads.AndroidGamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.GamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.GamePadFactory;
import com.tda367.parallax.platform.gamePadController.gamePads.Playstation3GamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.Xbox360GamePad;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Markus on 2017-04-24.
 */
public class GamePadFactoryTest {
    @Test
    public void getGamePad() throws Exception {
        GamePad factoryPlaystation3GamePad = GamePadFactory.getGamePad("PLAYSTATION_DUALSHOCK_3");
        GamePad factoryXbox360GamePad = GamePadFactory.getGamePad("XBOX360");
        GamePad factoryAndroidGamePad = GamePadFactory.getGamePad("RL_android_bluetooth_game_pad");
        GamePad playstation3GamePad = new Playstation3GamePad();
        GamePad xbox360GamePad = new Xbox360GamePad();
        GamePad androidGamePad = new AndroidGamePad();
        assertTrue(playstation3GamePad.equals(factoryPlaystation3GamePad));
        assertTrue(xbox360GamePad.equals(factoryXbox360GamePad));
        assertTrue(androidGamePad.equals(factoryAndroidGamePad));
    }

}