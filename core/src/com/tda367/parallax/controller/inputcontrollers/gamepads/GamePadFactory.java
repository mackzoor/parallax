package com.tda367.parallax.controller.inputcontrollers.gamepads;

public final class GamePadFactory {

    private GamePadFactory() {}

    //Returns a game pad class with the correct key codes for the connected controller
    public static GamePad getGamePad(String gamePadName){
        String gamePadNameLowerCase = gamePadName.toLowerCase();
        if(gamePadNameLowerCase.contains("playstation") && gamePadNameLowerCase.contains("3")) {
            return new Playstation3GamePad();
        } else if (gamePadNameLowerCase.contains("xbox") && gamePadNameLowerCase.contains("360")) {
            return new Xbox360GamePad();
        } else if (gamePadNameLowerCase.contains("android")) {
            return new AndroidGamePad();
        } else {
            return null;
            //TODO Implement support for unrecognised devices. Special setup?
        }
    }
}
