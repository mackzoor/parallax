package com.tda367.parallax.controller.inputControllers.gamePads;

/**
 * Created by Markus on 2017-04-22.
 */

public class GamePadFactory {

    //Returns a game pad class with the correct key codes for the connected controller
    public static GamePad getGamePad(String gamePadName){
        gamePadName = gamePadName.toLowerCase();
        if(gamePadName.contains("playstation") && gamePadName.contains("3")) {
            return new Playstation3GamePad();
        } else if (gamePadName.contains("xbox") && gamePadName.contains("360")) {
            return new Xbox360GamePad();
        } else if (gamePadName.contains("android")) {
            return new AndroidGamePad();
        } else {
            return null;
            //TODO Implement support for unrecognised devices. Special setup?
        }
    }
}
