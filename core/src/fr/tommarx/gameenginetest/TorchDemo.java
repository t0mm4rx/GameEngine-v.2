package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.ParticleManager;
import fr.tommarx.gameengine.Components.PointLight;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.Util.*;
import fr.tommarx.gameengine.Util.Math;

public class TorchDemo extends Screen {

    GameObject torch;
    Texture wall;
    PointLight light;
    ParticleManager pm;

    public TorchDemo(Game game) {
        super(game);
    }

    public void show() {
        torch = new GameObject(new Transform(Game.center));
        light = new PointLight(torch, 100, 5, new Color(0.9f, 0.4f, 0.1f, 0.8f));
        pm = new ParticleManager(torch);
        torch.addComponent(pm);
        torch.addComponent(light);
        torch.addComponent(new SpriteRenderer(torch, Gdx.files.internal("torch.png"), 0, 0, 0.5f, 0.5f));
        add(torch);

        wall = new Texture("wall.png");

        areLightsEnabled(true);
        //Game.debugging = true;
        getRayHandler().setAmbientLight(new Color(0, 0, 0.2f, 0.1f));
        changeLight();
        handleParticles();
    }

    public void renderBefore() {
        for (int x = 0; x < Gdx.graphics.getWidth() / 100 + 1; x++) {
            for (int y = 0; y < Gdx.graphics.getHeight() / 100 + 1; y++) {
                Draw.texture(wall, x, y, 1, 1);
            }
        }

    }

    public void renderAfter() {
    }

    public void update() {
        if (Keys.isKeyPressed(Input.Keys.P)) {
            camera.zoom -= 0.1f;
        }
        if (Keys.isKeyPressed(Input.Keys.O)) {
            camera.zoom += 0.1f;
        }
        //torch.getTransform().getPosition().set(Touch.getProjectedPosition());
    }

    private void changeLight() {
        Game.waitAndDo(100, () -> {
            if (light.getLength() > 4.5f && light.getLength() < 5.5f) {
                light.getLight().setDistance(light.getLength() + Math.random(-0.25f, 0.25f));
            } else {
                light.getLight().setDistance(5);
            }
            changeLight();
            return false;
        });
    }

    private void handleParticles() {
        Game.waitAndDo(100, () -> {
            pm.addParticle(new FireParticle(torch.getTransform(), 1200));
            handleParticles();
            return false;
        });
    }
}

class FireParticle extends fr.tommarx.gameengine.Util.Particle {

    Vector2 force;
    Color color;
    float size;

    public FireParticle(Transform transform, int lifetime) {
        super(transform.cpy(), lifetime);
        force = new Vector2(Math.random(-0.5f, 0.5f), 1).scl(0.015f);
        color = new Color(Math.random(0.5f, 0.9f), Math.random(0.1f, 0.3f), 0.1f, Math.random(0.2f, 1));
        size = Math.random(0.02f, 0.05f);
        getTransform().getPosition().y += 0.3f;
        getTransform().getPosition().x += Math.random(-0.1f, 0.1f);
        handlePosition();
    }

    public void render() {
        Draw.rect(getTransform().getPosition().x, getTransform().getPosition().y, size, size, color);
        getTransform().getPosition().add(force);
    }

    private void handlePosition() {
        Game.waitAndDo(300, () -> {
            force = new Vector2(Math.random(-0.5f, 0.5f), 1).scl(0.015f);
            handlePosition();
            return false;
        });
    }
}