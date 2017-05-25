package com.tda367.parallax.platform.gamepads;

import com.tda367.parallax.model.gamepads.AndroidGamePad;
import com.tda367.parallax.model.gamepads.GamePad;
import com.tda367.parallax.model.gamepads.GamePadFactory;
import com.tda367.parallax.model.gamepads.Playstation3GamePad;
import com.tda367.parallax.model.gamepads.Xbox360GamePad;

import org.junit.Test;
import static org.junit.Assert.*;

public class GamePadFactoryTest {
    @Test
    public void getGamePad() throws Exception {
        GamePad factoryPlaystation3GamePad = GamePadFactory.getGamePad("Sony PLAYSTATION(R)3 Controller");
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