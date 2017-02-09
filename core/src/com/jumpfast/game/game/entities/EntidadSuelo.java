package com.jumpfast.game.game.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jumpfast.game.game.Constants;

/**
 * Created by cprieto on 9/02/17.
 *
 * Representa el suelo
 */

public class EntidadSuelo extends Actor{


    private Texture floor, overfloor;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    public EntidadSuelo(World world, Texture floor, Texture overfloor, float x, float width, float y) {

        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        // Create the floor body.
        BodyDef def = new BodyDef();                // (1) Provide some definition.
        def.position.set(x + width / 2, y - 0.5f);  // (2) Center the floor in the coordinates given
        body = world.createBody(def);               // (3) Create the floor. Easy.

        // Give it a box shape.
        PolygonShape box = new PolygonShape();      // (1) Create the polygon shape.
        box.setAsBox(width / 2, 0.5f);              // (2) Give it some size.
        fixture = body.createFixture(box, 1);       // (3) Create a fixture.
        fixture.setUserData("floor");               // (4) Set the user data for the fixture.
        box.dispose();                              // (5) Destroy the shape.

        //Creamos el cuerpo izquierdo
        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x, y - 0.55f);
        leftBody = world.createBody(leftDef);

        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.02f, 0.45f);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("spike");
        leftBox.dispose();

        //Situamos el actor en la escena
        setSize(width * Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER);
        setPosition(x * Constants.PIXELS_IN_METER, (y - 1) * Constants.PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Render both textures.
        batch.draw(floor, getX(), getY(), getWidth(), getHeight());
        batch.draw(overfloor, getX(), getY() + 0.9f * getHeight(), getWidth(), 0.1f * getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
