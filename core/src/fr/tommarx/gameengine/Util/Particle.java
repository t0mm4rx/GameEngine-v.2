package fr.tommarx.gameengine.Util;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.GameObject;

public abstract class Particle extends GameObject {

    private int lifetime;
    private double timeB;

    public Particle(Transform transform, int lifetime) {
        super(transform);
        this.lifetime = lifetime;
        this.timeB = System.currentTimeMillis();
    }


    public abstract void render();

    public void update() {
        if (System.currentTimeMillis() - timeB > lifetime) {
            askDelete();
        }
    }

    private boolean deleteRequest = false;

    protected void askDelete() {
        deleteRequest = true;
    }

    public boolean hasRequestedDelete() {
        return deleteRequest;
    }

}
