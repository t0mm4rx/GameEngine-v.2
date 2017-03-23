package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.Keys;
import fr.tommarx.gameengine.Util.Math;

public class PhysicsScene extends Screen {


    public PhysicsScene(Game game) {
        super(game);
    }

    public void show() {
        Game.debugging = true;
        GameObject go = new GameObject(new Transform(new Vector2(Game.center.x, Game.center.y - 3)));
        go.addComponent(new BoxBody(go, 6, 1, BodyDef.BodyType.StaticBody, false));
        add(go);


        GameObject go2 = new GameObject(new Transform(new Vector2(Game.center.x, Game.center.y + 2)));
        CircleBody a = new CircleBody(go2, 0.5f, BodyDef.BodyType.StaticBody, false);
        go2.addComponent(a);
        add(go2);

        GameObject go1 = new GameObject(new Transform(new Vector2(Game.center.x - 1, Game.center.y + 3)));
        CircleBody b = new CircleBody(go1, 0.1f, BodyDef.BodyType.DynamicBody, false);
        DistanceJointDef defJoint = new DistanceJointDef();
        defJoint.length = 2;
        defJoint.initialize(a.getBody(), b.getBody(), new Vector2(Game.center.x, Game.center.y + 2), new Vector2(Game.center.x - 1, Game.center.y + 3));
        DistanceJoint joint = (DistanceJoint) world.createJoint(defJoint);
        go1.addComponent(b);
        add(go1);


        for (float i = 0; i < 10; i += 0.5f) {


        }
    }

    public void update() {
        Game.debug(1, "FPS : " + Gdx.graphics.getFramesPerSecond());
        Game.debug(2, "Bodies : " + Game.getCurrentScreen().getGameObjects().size());
        if (Keys.isKeyPressed(62)) {
            add(new Particle(new Transform(new Vector2(5, 5))));
        }
    }


}