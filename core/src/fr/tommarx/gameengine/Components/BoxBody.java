package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.tommarx.gameengine.Game.AbstractGameObject;

public class BoxBody extends Body {

    public BoxBody(AbstractGameObject go, float width, float height, BodyDef.BodyType bodyType, boolean isSensor) {
        super(go);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2 * go.getTransform().getScale().x), (height / 2 * go.getTransform().getScale().y));
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
