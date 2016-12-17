package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class Wall extends AbstractGameObject{

    public Wall(Transform transform, float width, float height) {
        super(transform);
        setTag("Wall");
        addComponent(new BoxRenderer(this, width, height, Color.BLACK));
        addComponent(new BoxBody(this, width, height, BodyDef.BodyType.StaticBody, false));
    }

    protected void update(float delta) {

    }
}
