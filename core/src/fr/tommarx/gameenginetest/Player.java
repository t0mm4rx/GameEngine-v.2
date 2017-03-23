package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import com.badlogic.gdx.physics.box2d.Contact;
import fr.tommarx.gameengine.Collisions.CollisionsListener;
import fr.tommarx.gameengine.Collisions.CollisionsManager;
import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Drawable;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Util.Keys;

public class Player extends AbstractGameObject {

    Body body;
    final float ACCELERATION = 30, JUMP = 70;
    int jumpState = 0;

    public Player(Transform transform) {
        super(new Transform(transform.getPosition(), new Vector2(.2f, .2f), 0));
        addComponent(new SpriteRenderer(this, Gdx.files.internal("badlogic.jpg")));
        body = new BoxBody(this, 2.0f, 2.0f, BodyDef.BodyType.DynamicBody, false);
        body.getBody().setFixedRotation(true);
        setTag("Player");
        addComponent(body);
        Drawable _this = this;

        new CollisionsManager(new CollisionsListener() {
            public void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact) {
                if (a.getTag() == "Player" && b.getTag() == "Coin") {
                    Game.getCurrentScreen().remove(b);
                }
                if (b.getTag() == "Player" && a.getTag() == "Coin") {
                    Game.getCurrentScreen().remove(a);
                }
            }

            public void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact) {}
        });
    }

    protected void update(float delta) {

        if (jumpState == 1 && body.getBody().getLinearVelocity().y < -1) {
            jumpState = 2;
        }

        if (jumpState == 2 && body.getBody().getLinearVelocity().y > -0.1f) {
            jumpState = 0;
        }

        if (Keys.isKeyPressed(Input.Keys.LEFT)) {
            body.getBody().setLinearVelocity(-1, body.getBody().getLinearVelocity().y);
            body.getBody().applyForceToCenter(new Vector2(-ACCELERATION, 0), false);
        }
        if (Keys.isKeyPressed(Input.Keys.RIGHT)) {
            body.getBody().setLinearVelocity(1, body.getBody().getLinearVelocity().y);
            body.getBody().applyForceToCenter(new Vector2(ACCELERATION, 0), false);
        }
        if (Keys.isKeyPressed(Input.Keys.UP) && jumpState == 0) {
            jumpState = 1;
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, 1);
            body.getBody().applyForceToCenter(new Vector2(0, JUMP), false);
        }

        Game.getCurrentScreen().camera.position.set(getTransform().getPosition().x, getTransform().getPosition().y, 0);
        if (getTransform().getPosition().y < Game.center.y) {
            Game.getCurrentScreen().camera.position.set(getTransform().getPosition().x, Game.getCurrentScreen().camera.position.y, 0);
        } else {
            Game.getCurrentScreen().camera.position.set(getTransform().getPosition().x, getTransform().getPosition().y, 0);
        }

    }
}
