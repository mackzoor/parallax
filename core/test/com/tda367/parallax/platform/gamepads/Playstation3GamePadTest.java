package com.tda367.parallax.platform.gamepads;

import com.tda367.parallax.model.gamepads.AndroidGamePad;
import com.tda367.parallax.model.gamepads.GamePad;
import com.tda367.parallax.model.gamepads.Playstation3GamePad;
import com.tda367.parallax.model.gamepads.Xbox360GamePad;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class Playstation3GamePadTest {

    @Test
    public void xAxisValueConverter() throws Exception {
        float minX = -1f;
        float maxX = 1f;

        Random rand = new Random();

        GamePad gamePad = new Playstation3GamePad();

        float value = rand.nextFloat() * (maxX - minX) + minX;

        float convertedValue = gamePad.xAxisValueConverter(value);

        assertTrue(convertedValue == value);
    }

    @Test
    public void yAxisValueConverter() throws Exception {
        float minY = -1f;
        float maxY = 1f;

        Random rand = new Random();

        GamePad gamePad = new Playstation3GamePad();

        float value = rand.nextFloat() * (maxY - minY) + minY;

        float convertedValue = gamePad.yAxisValueConverter(value);

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