package com.tda367.parallax.controller.screens.cardboardadapter;

import com.badlogic.gdx.Screen;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;

/**
 * Merges the functionality from the Cardboard extension with the LibGDX {@link Screen} class.
 */

public interface CardboardScreen extends Screen {

    void onNewFrame(HeadTransform headTransform);

    void onDrawEye(Eye eye);
}


