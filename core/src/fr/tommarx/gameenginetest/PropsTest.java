package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;

import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.Props;

public class PropsTest extends Screen {

    public PropsTest(Game game) {
        super(game);
    }

    public void show() {

    }

    public void renderBefore() {
        Draw.rect(Props.getWP(75), Props.getHP(50), Props.getWP(50), Props.getHP(50), Color.GREEN);
    }

    public void renderAfter() {

    }

    public void update() {

    }

}
