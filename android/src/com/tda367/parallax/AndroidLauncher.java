package com.tda367.parallax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AndroidLauncher extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_launcher_layout);
	}

	public void launchCardboard(View view) {
		Intent launchCardboardIntent = new Intent(this, CardboardLauncher.class);
		startActivity(launchCardboardIntent);
	}

	public void launchNormal(View view) {
		Intent launchNormalIntent = new Intent(this, NormalLauncher.class);
		startActivity(launchNormalIntent);
	}
}