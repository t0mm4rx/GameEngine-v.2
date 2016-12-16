package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import fr.tommarx.gameengine.Game.AbstractGameObject;

public class CircleBody extends Body{

    public CircleBody(AbstractGameObject go, float radius, BodyDef.BodyType bodyType, boolean isSensor) {
        super(go);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        if (isSensor) {
            fixtureDef.isSensor = true;
        }
        initBody(bodyType, fixtureDef);
        shape.dispose();
    }

}
