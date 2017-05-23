package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public abstract class Bone extends AbstractGameObject {

    public Bone(Vector2 pos, float angle, AbstractGameObject go) {
        super(new Transform(go.getTransform().getPosition().cpy().add(pos.cpy())));
        getTransform().setRotation(angle);
    }
}
