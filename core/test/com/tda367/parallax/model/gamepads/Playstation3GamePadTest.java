package com.tda367.parallax.model.gamepads;

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
}