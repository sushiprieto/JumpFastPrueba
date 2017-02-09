package com.jumpfast.game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends Game {

	//Centralizamos los assets
	private AssetManager manager;

	//Pantallas que usamos en el juego
	public PantallaInicio pantallaCargando, pantallaMenu, pantallaJuego, pantallaGameOver, pantallaCreditos;

	@Override
	public void create () {

		manager = new AssetManager();
		manager.load("floor.png", Texture.class);
		manager.load("gameover.png", Texture.class);
		manager.load("overfloor.png", Texture.class);
		manager.load("logo.png", Texture.class);
		manager.load("spike.png", Texture.class);
		manager.load("player.png", Texture.class);
		manager.load("audio/die.ogg", Sound.class);
		manager.load("audio/jump.ogg", Sound.class);
		manager.load("audio/song.ogg", Music.class);

		// Enter the loading screen to load the assets.
		pantallaCargando = new PantallaCargando(this);
		setScreen(pantallaCargando);

	}

	/**
	 * This method is invoked by LoadingScreen when all the assets are loaded. Use this method
	 * as a second-step loader. You can load the rest of the screens here and jump to the main
	 * screen now that everything is loaded.
	 */
	public void finishLoading() {
		pantallaMenu = new PantallaMenu(this);
		pantallaJuego = new PantallaJuego(this);
		pantallaGameOver = new PantallaGameOver(this);
		pantallaCreditos = new PantallaCreditos(this);
		setScreen(pantallaMenu);
	}

	public AssetManager getManager() {
		return manager;
	}

}
