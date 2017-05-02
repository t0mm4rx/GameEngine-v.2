package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class Menu extends Screen {

    public Menu(Game game) {
        super(game);
    }

    Texture test;

    public void show() {
        test = new Texture("image.jpg");
    }

    public void renderBefore() {
        //Draw.rect(Game.center.x, Game.center.y, Gdx.graphics.getWidth() / 100, Gdx.graphics.getHeight() / 100, new Color(1, 1, 1, 0.9f));
        Draw.texture(test, Game.center.x, Game.center.y);
    }

    public void renderAfter() {
    }

    public void update() {

    }

}
