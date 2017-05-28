package com.tda367.parallax.model.gamepads;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import java.util.Locale;

/**
 * A factory class for the {@link GamePad} interface. Takes in a string and returns the
 * corresponding GamePad. Will always return an {@link com.tda367.parallax.model.gamepads.AndroidGamePad} when connected to an
 * Android device
 */

public final class GamePadFactory {

    private GamePadFactory() {
    }

    public static GamePad getGamePad(String gamePadName) {
        final String lowerCaseName = gamePadName.toLowerCase(Locale.ENGLISH);
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            return new com.tda367.parallax.model.gamepads.AndroidGamePad();
        } else if (lowerCaseName.contains("playstation") && lowerCaseName.contains("3")) {
            return new com.tda367.parallax.model.gamepads.Playstation3GamePad();
        } else if (lowerCaseName.contains("xbox") && lowerCaseName.contains("360")) {
            return new com.tda367.parallax.model.gamepads.Xbox360GamePad();
        } else {
            return null;
            //TODO Implement support for unrecognised devices. Special setup?
        }
    }
}
