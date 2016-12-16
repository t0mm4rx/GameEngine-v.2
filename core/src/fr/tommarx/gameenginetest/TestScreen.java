package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
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

        fadeIn(1);

        GameObject background = new GameObject(new Transform(Game.center));
        background.addComponent(new BoxRenderer(background, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Color.LIGHT_GRAY));
        background.setScrollingSpeed(0);
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

    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }
    }

}
