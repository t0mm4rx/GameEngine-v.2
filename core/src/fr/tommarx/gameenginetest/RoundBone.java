package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.CircleRenderer;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Util.Bone;

public class RoundBone extends Bone {
    Body b;

    public RoundBone(Vector2 pos, float radius, float angle, AbstractGameObject go) {
        super(pos, angle, go);
        b = new CircleBody(this, radius, BodyDef.BodyType.DynamicBody, false);
        b.getBody().getTransform().setRotation(angle);
        addComponent(b);
        addComponent(new CircleRenderer(this, radius, new Color(1, 1, 1, 1)));
    }

    protected void update(float delta) {
        b.getBody().setAwake(true);
    }

    public void dispose() {

    }
}
