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

    public Player(Transform transform) {
        super(new Transform(transform.getPosition(), new Vector2(.2f, .2f), 0));
        addComponent(new SpriteRenderer(this, Gdx.files.internal("badlogic.jpg")));
        addComponent(new BoxBody(this, 256, 256, BodyDef.BodyType.DynamicBody));
    }

    protected void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

        }
    }
}
