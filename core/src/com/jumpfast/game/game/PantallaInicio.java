package com.jumpfast.game.game;

import com.badlogic.gdx.Screen;

/**
 * Created by cprieto on 9/02/17.
 */

public abstract class PantallaInicio implements Screen {

    protected MainGame juego;

    public PantallaInicio(MainGame juego){
        this.juego = juego;
    }

    /**
     * Metodo que se invoca cuando se muestra una pantalla
     */
    @Override
    public void show() {

    }

    /**
     * Metodo para renderizar
     * @param delta
     */
    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    /**
     * Pantalla que se deja de mostrar
     */
    @Override
    public void hide() {

    }

    /**
     * Metodo que se llama cuando se cierra el juego
     */
    @Override
    public void dispose() {

    }
}
