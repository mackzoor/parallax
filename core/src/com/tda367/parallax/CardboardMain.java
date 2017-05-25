package com.tda367.parallax;

import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.CardboardScreenManager;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.screens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;


public class CardboardMain extends CardboardGame {

    @Override
    public void create(){
        Player player = new Player();
        DeviceManager.setDevice(this);
        CardboardScreenManager.setCardboardGame(this);
        CardboardScreenManager.setGameState(ScreenState.MAIN_MENU, player);
    }

    @Override
    public void onDrawEye(Eye paramEye){
        cardboardScreen.onDrawEye(paramEye);
    }

    @Override
    public void onNewFrame(HeadTransform headTransform){
        cardboardScreen.onNewFrame(headTransform);
    }
}
