package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.CircleRenderer;
import fr.tommarx.gameengine.Components.ConeLight;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Util.Math;

public class Enemy extends AbstractGameObject {

    Body body;
    ConeLight light;
    Vector2 vel;
    int phase = -1;
    float speed = 2f;
    Vector2[] points = new Vector2[] {
            new Vector2(1, 1),
            new Vector2(1, 5),
            new Vector2(8, 5),
            new Vector2(8, 1)
    };

    public Enemy(Transform transform) {
        super(transform);
        body = new CircleBody(this, 0.2f, BodyDef.BodyType.DynamicBody, false);
        light = new ConeLight(this, 1000, 4, Color.WHITE, Math.DegreeToRadian(90), 20);
        addComponent(body);
        addComponent(light);
        addComponent(new CircleRenderer(this, 0.5f, Color.BLUE));
        vel = new Vector2(0, 0);
    }

    protected void update(float delta) {
        body.getBody().setLinearVelocity(vel);
        Game.debug(1, "" + light.getDirection());
        light.setDirection(light.getDirection() + (vel.angle() - light.getDirection()) / 10);

        if (phase == -1) {
            phase++;
            vel = body.getBody().getPosition().cpy().sub(points[phase]).nor().scl(-speed);
        }


        if (body.getBody().getPosition().dst(points[phase]) < 0.1f) {
            phase++;
            if (phase == 4) {
                phase = 0;
            }
            vel = body.getBody().getPosition().cpy().sub(points[phase]).nor();
            vel = vel.scl(-speed);
        }

    }
}
