package fr.tommarx.gameengine.Util;

import fr.tommarx.gameengine.Game.Drawable;
import fr.tommarx.gameengine.Game.Game;

public class LightsRenderer extends Drawable {

    public LightsRenderer() {
        isGameObject = false;
    }

    public void render() {
        if (Game.getCurrentScreen().lightsEnabled) {
            Game.batch.end();
            Game.getCurrentScreen().getRayHandler().setCombinedMatrix(Game.getCurrentScreen().camera);
            Game.getCurrentScreen().getRayHandler().updateAndRender();
            Game.batch.begin();
        }
    }

    public void renderInHUD() {

    }

    public void update() {

    }

    public void dispose() {

    }
}
