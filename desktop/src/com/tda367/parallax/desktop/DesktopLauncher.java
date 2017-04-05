package com.tda367.parallax.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tda367.parallax.platform.ParallaxLibGdxLayer;
import com.tda367.parallax.platform.SpaceCraftDisplay;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ParallaxLibGdxLayer(), config);
	}
}
