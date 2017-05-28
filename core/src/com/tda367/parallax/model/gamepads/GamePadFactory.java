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
        GamePad returnGamePad;
        final String lowerCaseName = gamePadName.toLowerCase(Locale.ENGLISH);
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            returnGamePad = new AndroidGamePad();
        } else if (lowerCaseName.contains("playstation") && lowerCaseName.contains("3")) {
            returnGamePad = new Playstation3GamePad();
        } else if (lowerCaseName.contains("xbox") && lowerCaseName.contains("360")) {
            returnGamePad = new Xbox360GamePad();
        } else {
            //TODO should cast exception
            returnGamePad = null;
        }
        return returnGamePad;
    }
}
