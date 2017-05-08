package com.tda367.parallax.platform.inputControllers.gamePads;

import com.tda367.parallax.controller.inputControllers.gamePads.AndroidGamePad;
import com.tda367.parallax.controller.inputControllers.gamePads.GamePad;
import com.tda367.parallax.controller.inputControllers.gamePads.Playstation3GamePad;
import com.tda367.parallax.controller.inputControllers.gamePads.Xbox360GamePad;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Markus on 2017-04-24.
 */
public class AndroidGamePadTest {
    @Test
    public void XAxisValueConverter() throws Exception {
        float minX = -1f;
        float maxX = 1f;

        Random rand = new Random();

        GamePad gamePad = new AndroidGamePad();

        float value = rand.nextFloat() * (maxX - minX) + minX;

        float convertedValue = gamePad.XAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void YAxisValueConverter() throws Exception {
        float minY = -1f;
        float maxY = 1f;

        Random rand = new Random();

        GamePad gamePad = new AndroidGamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.YAxisValueConverter(value);

        assertTrue(convertedValue == -1f * value);
    }

    @Test
    public void equals() throws Exception {
        GamePad gamePad1 = new AndroidGamePad();
        GamePad gamePad2 = new AndroidGamePad();
        GamePad gamePad3 = new Playstation3GamePad();
        GamePad gamePad4 = new Xbox360GamePad();
        assertTrue(gamePad1.equals(gamePad2));
        assertFalse(gamePad1.equals(gamePad3));
        assertFalse(gamePad1.equals(gamePad4));
    }

}