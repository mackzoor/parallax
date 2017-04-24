package com.tda367.parallax.platform.gamePadController.gamePads.GamePadsTest;

import com.tda367.parallax.platform.gamePadController.gamePads.AndroidGamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.GamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.Playstation3GamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.Xbox360GamePad;

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

        GamePad gamePad = new Xbox360GamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.XAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void YAxisValueConverter() throws Exception {
        float minY = -1f;
        float maxY = 1f;

        Random rand = new Random();

        GamePad gamePad = new Xbox360GamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.XAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void equals() throws Exception {
        GamePad gamePad1 = new Xbox360GamePad();
        GamePad gamePad2 = new Xbox360GamePad();
        GamePad gamePad3 = new Playstation3GamePad();
        GamePad gamePad4 = new AndroidGamePad();
        assertTrue(gamePad1.equals(gamePad2));
        assertFalse(gamePad1.equals(gamePad3));
        assertFalse(gamePad1.equals(gamePad4));
    }

}