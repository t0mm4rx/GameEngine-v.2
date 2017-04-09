package fr.tommarx.gameenginetest;

import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class LightScreen extends Screen {

    public LightScreen(Game game) {
        super(game);
    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    public void show() {
        areLightsEnabled(true);
        getRayHandler().setAmbientLight(0.7f, 0.7f, 1f, .03f);
        world.setGravity(new Vector2(0, 0));
        //Game.debugging = true;

        add(new Enemy(new Transform(Game.center)));
    }

    public void update() {

    }
}
