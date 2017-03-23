package fr.tommarx.gameenginetest;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import fr.tommarx.gameengine.Util.Math;
import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class Particle extends AbstractGameObject{
    public Particle(Transform transform) {
        super(transform);
        Body b = new CircleBody(this, 0.1f, BodyDef.BodyType.DynamicBody, false);
        Vector2 v = Math.randomVector2(2);
        b.getBody().applyForceToCenter(v, false);
        addComponent(b);
    }

    protected void update(float delta) {}
}
