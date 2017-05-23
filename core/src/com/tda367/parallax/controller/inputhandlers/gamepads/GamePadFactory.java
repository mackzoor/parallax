package com.tda367.parallax.controller.inputhandlers.gamepads;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public final class GamePadFactory {

    private GamePadFactory() {}

    //Returns a game pad class with the correct key codes for the connected controller
    public static GamePad getGamePad(String gamePadName){
        String gamePadNameLowerCase = gamePadName.toLowerCase();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            return new AndroidGamePad();
        } else if (gamePadNameLowerCase.contains("playstation") && gamePadNameLowerCase.contains("3")) {
            return new Playstation3GamePad();
        } else if (gamePadNameLowerCase.contains("xbox") && gamePadNameLowerCase.contains("360")) {
            return new Xbox360GamePad();
        } else {
            return null;
            //TODO Implement support for unrecognised devices. Special setup?
        }
    }
}
