package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class Coin extends AbstractGameObject{

    public Coin(Transform transform) {
        super(transform);
        addComponent(new BoxRenderer(this, 0.1f, 0.1f, Color.YELLOW));
        addComponent(new CircleBody(this, 0.1f, BodyDef.BodyType.KinematicBody, true));
        setTag("Coin");

    }

    protected void update(float delta) {
    }
}
