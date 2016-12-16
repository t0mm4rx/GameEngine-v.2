package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class Coin extends AbstractGameObject{

    public Coin(Transform transform) {
        super(transform);
        addComponent(new BoxRenderer(this, 16, 16, Color.YELLOW));
    }

    protected void update(float delta) {

    }
}
