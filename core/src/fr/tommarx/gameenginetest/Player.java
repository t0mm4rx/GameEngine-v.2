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
import fr.tommarx.gameengine.Game.Game;

public class Player extends AbstractGameObject {

    Body body;
    final float ACCELERATION = 30, JUMP = 70;

    public Player(Transform transform) {
        super(new Transform(transform.getPosition(), new Vector2(.2f, .2f), 0));
        addComponent(new SpriteRenderer(this, Gdx.files.internal("badlogic.jpg")));
        body = new BoxBody(this, 256, 256, BodyDef.BodyType.DynamicBody, false);
        setTag("Player");
        addComponent(body);
    }

    protected void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.getBody().applyForceToCenter(new Vector2(-ACCELERATION * 100000, 0), false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.getBody().applyForceToCenter(new Vector2(ACCELERATION * 100000, 0), false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            body.getBody().applyForceToCenter(new Vector2(0, JUMP * 100000), false);
        }

        if (getTransform().getPosition().y < Game.center.y) {
            Game.getCurrentScreen().camera.position.set(getTransform().getPosition().x, Game.getCurrentScreen().camera.position.y, 0);
        } else {
            Game.getCurrentScreen().camera.position.set(getTransform().getPosition().x, getTransform().getPosition().y, 0);
        }

    }
}
