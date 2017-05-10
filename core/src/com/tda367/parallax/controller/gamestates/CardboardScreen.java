package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Screen;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;


public interface CardboardScreen extends Screen {

    void onNewFrame(HeadTransform headTransform);
    void onDrawEye(Eye eye);
}


