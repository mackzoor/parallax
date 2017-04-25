package com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePadsTest;

import com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.AndroidGamePad;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Markus on 2017-04-24.
 */
public class Xbox360GamePadTest {
    @Test
    public void XAxisValueConverter() throws Exception {
        float minY = -1f;
        float maxY = 1f;

        Random rand = new Random();

        com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad gamePad = new com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.Xbox360GamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.YAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void YAxisValueConverter() throws Exception {
        float minY = -1f;
        float maxY = 1f;

        Random rand = new Random();

        com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad gamePad = new com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.Xbox360GamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.XAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void equals() throws Exception {
        com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad gamePad1 = new com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.Xbox360GamePad();
        com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad gamePad2 = new com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.Xbox360GamePad();
        com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad gamePad3 = new com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.Playstation3GamePad();
        com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad gamePad4 = new AndroidGamePad();
        assertTrue(gamePad1.equals(gamePad2));
        assertFalse(gamePad1.equals(gamePad3));
        assertFalse(gamePad1.equals(gamePad4));
    }

}