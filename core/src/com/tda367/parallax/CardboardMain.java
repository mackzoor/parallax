package com.tda367.parallax;

import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;


public class CardboardMain extends CardboardGame {

    @Override
    public void create(){
        DeviceManager.setDevice(this);
        GameStateManager.setCardboardGameScreen(this);

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
