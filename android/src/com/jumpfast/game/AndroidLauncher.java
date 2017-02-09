package com.jumpfast.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jumpfast.game.game.MainGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		// Pone el juego en modo inmversive para ocultar la statusbar en Android 4.4+
		config.useImmersiveMode = true;

		initialize(new MainGame(), config);
	}
}
