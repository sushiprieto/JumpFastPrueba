package com.jumpfast.game.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jumpfast.game.game.PantallaInicio;
import com.jumpfast.game.game.MainGame;

/**
 * Created by cprieto on 9/02/17.
 */

public class PantallaScene2D extends PantallaInicio {

    /** The Scene2D stage where all the actors are added. */
    private Stage stage;

    /** The actor that represents the player. */
    private ActorJugador player;

    /** The actor that represent spikes. */
    private ActorPinchos spikes;

    /** Textures used in this screen. */
    private Texture playerTexture, spikeTexture;

    /** Regions used in this screen. */
    private TextureRegion spikeRegion;

    public PantallaScene2D(MainGame game) {
        super(game);

        // Load assets using new Texture instead of asset manager.
        playerTexture = new Texture("player.png");
        spikeTexture = new Texture("spike.png");
        spikeRegion = new TextureRegion(spikeTexture, 0, 64, 128, 64);
    }

    @Override
    public void show() {
        // Create a new stage.
        stage = new Stage(new FitViewport(640, 400));

        // Load the actors.
        player = new ActorJugador(playerTexture);
        spikes = new ActorPinchos(spikeRegion, 2100, 100, 500);
        player.setPosition(20, 100);

        // Add the actors to the screen. They won't be visible if you don't add them.
        stage.addActor(player);
        stage.addActor(spikes);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        checkCollisions();
        stage.draw();
    }

    /**
     * This method checks collisions between the player and the spikes using the bounding box
     * method. There is a collision if the bounding boxes that represent both actors overlap.
     */
    private void checkCollisions() {
        if (player.isAlive() &&  (player.getX() + player.getWidth()) > spikes.getX()) {
            System.out.println("A collision has happened.");
            player.setAlive(false);
        }
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        spikeTexture.dispose();
        stage.dispose();
    }
}
