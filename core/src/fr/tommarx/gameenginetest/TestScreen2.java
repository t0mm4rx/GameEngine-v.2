package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Texture;

import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class TestScreen2 extends Screen{

    Texture text;

    public TestScreen2(Game game) {
        super(game);
    }

    public void show() {
        text = new Texture("badlogic.jpg");
        Game.debugging = true;
    }

    public void update() {
        Game.debug(1, Game.size);
        Game.debug(2, Game.center);
    }

    public void renderBefore() {
        Draw.texture(text, Game.center.x, Game.center.y);
    }

    public void renderAfter() {
    }

}
