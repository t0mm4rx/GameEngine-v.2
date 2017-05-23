package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Skeleton;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;

public class SkeletonScreen extends Screen {

    GameObject sm;
    Skeleton s;

    public SkeletonScreen(Game game) {
        super(game);
    }

    public void show() {

        Game.debugging = true;
        world.setGravity(new Vector2(0, 0));

        sm = new GameObject(new Transform(Game.center));
        s = new Skeleton(sm);
        s.addBone(new RecBone(new Vector2(-.15f, 0), .5f, -15, sm));
        s.addBone(new RecBone(new Vector2(.15f, 0), .5f, 15, sm));

        s.addBone(new RecBone(new Vector2(0f, -.10f), .7f, 0, sm));
        s.addBone(new RoundBone(new Vector2(0f, .5f), .2f, 0, sm));

        s.addBone(new RecBone(new Vector2(-.15f, -.7f), .5f, -10, sm));
        s.addBone(new RecBone(new Vector2(.15f, -.7f), .5f, 10, sm));

        sm.addComponent(s);
        add(sm);
    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    public void update() {
        Game.debug(1, getGameObjects().size() + " objects");
    }

}
