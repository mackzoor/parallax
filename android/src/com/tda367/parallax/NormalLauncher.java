package com.tda367.parallax;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tda367.parallax.platform.ParallaxLibGdx;
import com.tda367.parallax.platform.ParallaxLibGdxPlayState;

/**
 * Created by Markus on 2017-04-11.
 */

public class NormalLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.numSamples = 2;
        initialize(new ParallaxLibGdx(), config);
    }
}
