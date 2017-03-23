package fr.tommarx.gameenginetest;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.Keys;

public class PhysicsScene extends Screen {


    public PhysicsScene(Game game) {
        super(game);
    }

    public void show() {
        Game.debugging = true;
        GameObject go = new GameObject(new Transform(new Vector2(Game.center.x, Game.center.y - 3)));
        go.addComponent(new BoxBody(go, 6, 1, BodyDef.BodyType.StaticBody, false));
        add(go);

        for (float i = 0; i < 10; i += 0.5f) {


        }
    }

    public void update() {
        if (Keys.isKeyPressed(62)) {
            add(new Particle(new Transform(new Vector2(5, 5))));
        }
    }


}
