package com.tda367.parallax.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;

/**
 * Created by Markus on 2017-04-26.
 */

public class GameModeFactory {
    public static GameModeState getGameModeState(ApplicationListener application) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            return new DesktopMode();
        } else if (application instanceof CardBoardApplicationListener) {
            return new CardboardMode();
        } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
            return new AndroidMode();
        } else {
            return null;
        }
    }
}
