package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Util.Math;

public class Particle extends fr.tommarx.gameengine.Util.Particle{

    Vector2 force;

    public Particle(Transform transform, int lifetime) {
        super(transform.cpy(), lifetime);
        force = Math.randomVector2(0.01f);
    }

    public void render() {
        getTransform().getPosition().add(force);
        Draw.circle(getTransform().getPosition().x, getTransform().getPosition().y, 0.03f, new Color(Math.random(150, 255) / 255, 0.2f, 0.2f, 1));
    }
}
