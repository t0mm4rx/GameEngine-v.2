package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Input;

import fr.tommarx.gameengine.Components.ParticleManager;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.IO.File;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.IO.Touch;
import fr.tommarx.gameengine.Util.WaitAndDo;

public class ParticleScene extends Screen {

    GameObject go;
    ParticleManager pm;

    public ParticleScene(Game game) {
        super(game);
    }

    public void show() {
        Game.debugging = true;
        go = new GameObject(new Transform(Game.center));
        pm = new ParticleManager(go);
        go.addComponent(pm);
        add(go);
        addParticle();
    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    public void update() {
        if (Keys.isKeyPressed(Input.Keys.PLUS)) {
            camera.zoom -= 0.1f;
        }
        if (Keys.isKeyPressed(Input.Keys.MINUS)) {
            camera.zoom += 0.1f;
        }
        go.getTransform().setPosition(Touch.getProjectedPosition());
    }

    public void addParticle() {
        Game.waitAndDo(30, () -> {
            pm.addParticle(new Particle(go.getTransform(), 1000));
            addParticle();
            return false;
        });
    }

}
