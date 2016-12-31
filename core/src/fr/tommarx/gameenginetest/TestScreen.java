package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;

import fr.tommarx.gameengine.Collisions.CollisionsListener;
import fr.tommarx.gameengine.Collisions.CollisionsManager;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.MapInterface;
import fr.tommarx.gameengine.Util.MapReader;

public class TestScreen extends Screen{

    public TestScreen(Game game) {
        super(game);
    }

    public void show() {
        GameObject background = new GameObject(new Transform(new Vector2(0, Game.center.y)));
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
        });

        fadeIn(1);
    }

    public void update() {
        Game.debug(1, Gdx.graphics.getFramesPerSecond() + "");
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }
    }

}
