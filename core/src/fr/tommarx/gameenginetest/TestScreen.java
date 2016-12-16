package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;

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
        add(new Wall(new Transform(new Vector2(Game.center.x, 64)), 400, 64));
    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }
    }

}
