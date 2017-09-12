package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;

public class Props {

    public static float getWP(float percentage) {
        return percentage * Gdx.graphics.getWidth() / 100 / 100;
    }

    public static float getHP(float percentage) {
        return percentage * Gdx.graphics.getHeight() / 100 / 100;
    }

}
