package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.Body;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Util.Bone;

public class RecBone extends Bone {

    Body b;

    public RecBone(Vector2 pos, float length, float angle, AbstractGameObject go) {
        super(pos, angle, go);
        b = new BoxBody(this, .1f, length, BodyDef.BodyType.DynamicBody, false);
        b.getBody().getTransform().setRotation(angle);
        addComponent(b);
        go.addComponent(new BoxRenderer(this, .1f, length, new Color(1, 1, 1, 1)));
    }

    public void render() {

    }

    protected void update(float delta) {
        b.getBody().setAwake(true);
    }

    public void dispose() {

    }

}
