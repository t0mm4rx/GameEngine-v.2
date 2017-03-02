package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.MapInterface;
import fr.tommarx.gameengine.Util.MapReader;

public class TestScreen extends Screen{

    BoxBody playerBody;
    GameObject testPhysique;
    int jumpState = 0;

    public TestScreen(Game game) {
        super(game);
    }

    public void show() {
        /*GameObject background = new GameObject(new Transform(new Vector2(0, Game.center.y)));
        background.addComponent(new BoxRenderer(background, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 10, Color.LIGHT_GRAY));
        background.setScrollingSpeed(1);
        add(background);

        add(new Player(new Transform(Game.center)));

        MapReader.parse(Gdx.files.internal("level.png"), 64, new MapInterface() {
            public void onPixel(Color color, float x, float y) {
                if (color.r == 0f && color.g == 0f && color.b == 0f) {
                    add(new Wall(new Transform(new Vector2(x, y)), 64, 64));
                }
                if (color.r == 1f && color.g == 1f && color.b == 0f) {
                    add(new Coin(new Transform(new Vector2(x, y))));
                }
            }
        });*/


        testPhysique = new GameObject(new Transform(Game.center, new Vector2(.5f, .5f), 0));
        testPhysique.addComponent(new SpriteRenderer(testPhysique, Gdx.files.internal("badlogic.jpg")));
        playerBody = new BoxBody(testPhysique, 2.0f, 2.0f, BodyDef.BodyType.DynamicBody, false);
        testPhysique.addComponent(playerBody);
        add(testPhysique);

        GameObject box = new GameObject(new Transform(new Vector2(Game.center.x, Game.center.y - 3)));
        box.addComponent(new BoxRenderer(box, 10, .1f, Color.WHITE));
        box.addComponent(new BoxBody(box, 10, .1f, BodyDef.BodyType.StaticBody, false));
        add(box);

        System.out.println(Game.center.x);

        fadeIn(1);
    }

    public void update() {
        Game.debug(1, Gdx.graphics.getFramesPerSecond() + "");
        Game.debug(2, "XGameObject : " + testPhysique.getTransform().getPosition().x);
        Game.debug(3, "XBody : " + playerBody.getBody().getLinearVelocity().x);
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }

        if (jumpState == 1 && playerBody.getBody().getLinearVelocity().y < 0) {
            jumpState = 2;
        }

        if (jumpState == 2 && playerBody.getBody().getLinearVelocity().y > -0.01f && playerBody.getBody().getLinearVelocity().y < 0.01f) {
            jumpState = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerBody.getBody().setLinearVelocity(-1, playerBody.getBody().getLinearVelocity().y);
            playerBody.getBody().applyForceToCenter(new Vector2(-80f, 0), false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerBody.getBody().setLinearVelocity(1, playerBody.getBody().getLinearVelocity().y);
            playerBody.getBody().applyForceToCenter(new Vector2(80f, 0), false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && jumpState == 0) {
            playerBody.getBody().setLinearVelocity(playerBody.getBody().getLinearVelocity().x, 1);
            playerBody.getBody().applyForceToCenter(new Vector2(0, 200), false);
            jumpState = 1;
        }
    }

}
