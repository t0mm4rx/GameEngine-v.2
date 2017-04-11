package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Components.ParticleManager;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;

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
    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    public void update() {

    }

}
