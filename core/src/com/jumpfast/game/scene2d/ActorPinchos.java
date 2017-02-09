package com.jumpfast.game.scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by cprieto on 9/02/17.
 */

public class ActorPinchos extends Actor {

    /** Texture for the spikes. */
    private TextureRegion spikes;

    /** Speed the spikes use to travel leftwards. */
    private float speed;

    /**
     * Create some spikes.
     * @param spikes
     * @param x     initial position
     * @param y     initial position
     * @param speed     speed for travelling
     */
    public ActorPinchos(TextureRegion spikes, float x, float y, float speed) {
        this.spikes = spikes;
        this.speed = speed;

        // Place the spike where you have been told to.
        setPosition(x, y);
        setSize(spikes.getRegionWidth(), spikes.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        // When acting, infinitesimally move the spikes to the left so that they look like if
        // they actually moved to the left. This is linear motion. It travels left as much as
        // it can using the speed and the time between this frame and the previous one.
        setX(getX() - speed * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(spikes, getX(), getY());
    }
}
