package com.jumpfast.game.game.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by cprieto on 9/02/17.
 *
 * crea entidades usando FactoryMethods
 */

public class EntityFactory {

    private AssetManager manager;

    /**
     * Create a new entity factory using the provided asset manager.
     * @param manager   the asset manager used to generate things.
     */
    public EntityFactory(AssetManager manager) {
        this.manager = manager;
    }

    /**
     * Create a player using the default texture.
     * @param world     world where the player will have to live in.
     * @param position  initial position ofr the player in the world (meters,meters).
     * @return          a player.
     */
    public EntidadJugador createPlayer(World world, Vector2 position) {
        Texture playerTexture = manager.get("player.png");
        return new EntidadJugador(world, playerTexture, position);
    }

    /**
     * Create floor using the default texture set.
     * @param world     world where the floor will live in.
     * @param x         horizontal position for the spikes in the world (meters).
     * @param width     width for the floor (meters).
     * @param y         vertical position for the top of this floor (meters).
     * @return          a floor.
     */
    public EntidadSuelo createFloor(World world, float x, float width, float y) {
        Texture floorTexture = manager.get("floor.png");
        Texture overfloorTexture = manager.get("overfloor.png");
        return new EntidadSuelo(world, floorTexture, overfloorTexture, x, width, y);
    }

    /**
     * Create spikes using the default texture.
     * @param world     world where the spikes will live in.
     * @param x         horizontal position for the spikes in the world (meters).
     * @param y         vertical position for the base of the spikes in the world (meters).
     * @return          some spikes.
     */
    public EntidadPincho createSpikes(World world, float x, float y) {
        Texture spikeTexture = manager.get("spike.png");
        return new EntidadPincho(world, spikeTexture, x, y);
    }

}
