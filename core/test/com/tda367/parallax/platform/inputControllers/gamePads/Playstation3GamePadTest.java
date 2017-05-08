package com.tda367.parallax.platform.inputControllers.gamePads;

import com.tda367.parallax.controller.inputcontrollers.gamepads.AndroidGamePad;
import com.tda367.parallax.controller.inputcontrollers.gamepads.GamePad;
import com.tda367.parallax.controller.inputcontrollers.gamepads.Playstation3GamePad;
import com.tda367.parallax.controller.inputcontrollers.gamepads.Xbox360GamePad;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class Playstation3GamePadTest {

    @Test
    public void XAxisValueConverter() throws Exception {
        float minX = -1f;
        float maxX = 1f;

        Random rand = new Random();

        GamePad gamePad = new Playstation3GamePad();

        float value = rand.nextFloat() * (maxX - minX) + minX;

        float convertedValue = gamePad.XAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void YAxisValueConverter() throws Exception {
        float minY = -1f;
        float maxY = 1f;

        Random rand = new Random();

        GamePad gamePad = new Playstation3GamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.YAxisValueConverter(value);

        assertTrue(convertedValue == -1f * value);
    }

    @Test
    public void equals() throws Exception {
        GamePad gamePad1 = new Playstation3GamePad();
        GamePad gamePad2 = new Playstation3GamePad();
        GamePad gamePad3 = new Xbox360GamePad();
        GamePad gamePad4 = new AndroidGamePad();
        assertTrue(gamePad1.equals(gamePad2));
        assertFalse(gamePad1.equals(gamePad3));
        assertFalse(gamePad1.equals(gamePad4));
    }
}