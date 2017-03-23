package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class ShapeBody extends Body {
    public ShapeBody(AbstractGameObject go, Vector2[] vertices, BodyDef.BodyType bodyType, boolean isSensor) {
        super(go);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        if (isSensor) {
            fixtureDef.isSensor = true;
        }
        this.initBody(bodyType, fixtureDef);
        shape.dispose();
    }
}
