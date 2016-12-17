package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class Player extends AbstractGameObject {

    Body body;
    final float ACCELERATION = 30, MAX_SPEED = 300, JUMP = 70;
    private boolean grounded, jumping;

    public Player(Transform transform) {
        super(new Transform(transform.getPosition(), new Vector2(.2f, .2f), 0));
        addComponent(new SpriteRenderer(this, Gdx.files.internal("badlogic.jpg")));
        body = new BoxBody(this, 256, 256, BodyDef.BodyType.DynamicBody, false);
        setTag("Player");
        addComponent(body);
        grounded = false;
        jumping = false;
    }

    protected void update(float delta) {

        if (!grounded) {
            if (body.getBody().getLinearVelocity().y < 0) {
                jumping = false;
            }
            if (body.getBody().getLinearVelocity().y == 0 && jumping == false) {
                grounded = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (body.getBody().getLinearVelocity().x > -MAX_SPEED) {
                body.getBody().setLinearVelocity(new Vector2(body.getBody().getLinearVelocity().x - ACCELERATION, body.getBody().getLinearVelocity().y));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (body.getBody().getLinearVelocity().x < MAX_SPEED) {
                body.getBody().setLinearVelocity(new Vector2(body.getBody().getLinearVelocity().x + ACCELERATION, body.getBody().getLinearVelocity().y));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (grounded) {
                grounded = false;
                jumping = true;
                body.getBody().setLinearVelocity(new Vector2(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y - JUMP));
            }
        }
    }
}
