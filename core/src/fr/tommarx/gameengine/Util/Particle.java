package fr.tommarx.gameengine.Util;

import fr.tommarx.gameengine.Game.Drawable;

public abstract class Particle extends Drawable {

    public abstract void show();

    private boolean deleteRequest = false;

    protected void askDelete() {
        deleteRequest = true;
    }

    public boolean hasRequestedDelete() {
        return deleteRequest;
    }

}
