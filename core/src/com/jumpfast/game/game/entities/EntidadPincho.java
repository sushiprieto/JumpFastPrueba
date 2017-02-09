package com.jumpfast.game.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jumpfast.game.game.Constants;

/**
 * Created by cprieto on 9/02/17.
 *
 * Este es el actor que pierdes cuando lo tocas
 */

public class EntidadPincho extends Actor{

    //Textura pinchos
    private Texture texture;
    //Mundo
    private World world;
    //El cuerpo asignado a este pincho
    private Body body;
    //Fixture
    private Fixture fixture;

    /**
     * Creamos algunos pinchos
     * @param world
     * @param texture
     * @param x
     * @param y
     */
    public EntidadPincho(World world, Texture texture, float x, float y) {

        this.world = world;
        this.texture = texture;

        //Creamos el cuerpo
        BodyDef def = new BodyDef();                // (1) Le damos la definicion.
        def.position.set(x, y + 0.5f);              // (2) Posicion que tiene en el mundo.
        body = world.createBody(def);               // (3) Creamos el cuerpo.

        //Le damos forma
        PolygonShape box = new PolygonShape();      // (1) Hacemos un poligono.
        Vector2[] vertices = new Vector2[3];        // (2) Añadimos los vertices.
        vertices[0] = new Vector2(-0.5f, -0.5f);    // (3) Añadimos el triangulo.
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        box.set(vertices);                          // (4) Lo establecemos en la forma.
        fixture = body.createFixture(box, 1);       // (5) Creamos la fixture.
        fixture.setUserData("spike");               // (6) Establecemos el user data.
        box.dispose();                              // (7) Destruimos la forma (Para ahorrar recursos).

        //Posicion del actor en la pantalla
        setPosition((x - 0.5f) * Constants.PIXELS_IN_METER, y *
        Constants.PIXELS_IN_METER);
        setSize(Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
