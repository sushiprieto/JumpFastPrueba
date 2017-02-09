package com.jumpfast.game.game.entities;

import com.badlogic.gdx.Gdx;
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
 */

public class EntidadJugador extends Actor{

    //La textura del jugador
    private Texture texture;
    //Instancia del mundo en donde se encuentra el jugador
    private World world;
    //El cuerpo del jugador
    private Body body;
    //La fixtura del jugador
    private Fixture fixture;
    //Instancia para ver si esta vivo o no
    private boolean alive = true;
    //Instancia para ver si esta saltando o no
    private boolean jumping = false;
    //Instancia para ver si el jugador tiene que saltar o no
    private  boolean mustJump = false;

    public EntidadJugador(World world, Texture texture, Vector2 position){

        this.world = world;
        this.texture = texture;

        //Creamos el cuerpo del jugador
        BodyDef def = new BodyDef();
        //Ponemos el cuerpo en la posicion inicial
        def.position.set(position);
        //Lo hacemos dinamico
        def.type = BodyDef.BodyType.DynamicBody;
        //Creamos el cuerpo
        body = world.createBody(def);

        //Le damos forma
        PolygonShape box = new PolygonShape();
        //1x1
        box.setAsBox(0.5f, 0.5f);
        //Creamos la fixture
        fixture = body.createFixture(box, 3);
        //Establecemos el userdata
        fixture.setUserData("player");
        //Destruimos la forma para ahorrar recursos
        box.dispose();

        //Establecemos el tamaño a un valor que es suficientemente grande para ser renderizado en la pantalla
        setSize(Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //Siempre actualiza la posicion del actor cuando lo vas a dibujar
        setPosition((body.getPosition().x - 0.5f) * Constants.PIXELS_IN_METER,
                (body.getPosition().y - 0.5f) * Constants.PIXELS_IN_METER);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {

        //Salta cuando tocas la pantalla
        if (Gdx.input.justTouched()){
            jump();
        }

        //Salta si se requiere saltar durante una colision
        if (mustJump){
            mustJump= false;
            jump();
        }

        //Si el jugador esta vivo, cambia la velocidad
        if (alive){
            //solo cambia la velocidad del eje y
            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(Constants.PLAYER_SPEED, speedY);
        }

        //Si el jugador esta saltando aplicamos fuerza opuesta para que caiga mas rapido
        if (jumping){
            body.applyForceToCenter(0, Constants.IMPULSE_JUMP * 0.55f, true);
        }

    }

    public void jump() {

        //El jugador no esta saltando y esta vivo para saltar
        if (!jumping && alive){
            jumping = true;

            //Aplicamos un impulso al jugador
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, Constants.IMPULSE_JUMP, position.x, position.y, true);
        }

    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

}
