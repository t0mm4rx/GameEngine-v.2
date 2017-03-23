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
import fr.tommarx.gameengine.Util.Keys;
import fr.tommarx.gameengine.Util.MapInterface;
import fr.tommarx.gameengine.Util.MapReader;

import static com.badlogic.gdx.Gdx.input;

public class TestScreen extends Screen{

    public TestScreen(Game game) {
        super(game);
    }

    public void show() {
        GameObject background = new GameObject(new Transform(new Vector2(0, Game.center.y)));
        background.addComponent(new BoxRenderer(background, Gdx.graphics.getWidth() / 100, Gdx.graphics.getHeight() / 10, Color.LIGHT_GRAY));
        background.setScrollingSpeed(1);
        add(background);

        add(new Player(new Transform(Game.center)));

        MapReader.parse(Gdx.files.internal("level.png"), 64, new MapInterface() {
            public void onPixel(Color color, float x, float y) {
                if (color.r == 0f && color.g == 0f && color.b == 0f) {
                    add(new Wall(new Transform(new Vector2(x, y)), 0.64f, 0.64f));
                }
                if (color.r == 1f && color.g == 1f && color.b == 0f) {
                    add(new Coin(new Transform(new Vector2(x, y))));
                }
            }
        });
        //add(new Coin(new Transform(new Vector2(5, 3))));

        fadeIn(1);
    }

    public void update() {
        Game.debug(1, Gdx.graphics.getFramesPerSecond() + "");
        Game.debug(2, camera.position.x + " : " + camera.position.y);
        Game.debug(3, this.getGameObjects().size() + "");
        Game.debug(4, "Is up pressed ? " + Keys.isKeyPressed(19));
        if (input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }
        if (input.isKeyJustPressed(Input.Keys.NUM_9)) {
            camera.zoom -= 0.1f;
        }
        if (input.isKeyJustPressed(Input.Keys.NUM_7)) {
            camera.zoom += 0.1f;
        }
    }

}
