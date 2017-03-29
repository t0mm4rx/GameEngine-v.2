package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Touch {

    public static boolean isTouched() {
        return Gdx.input.isTouched();
    }

    public static boolean isTouched(float x, float y, float width, float height) {
        if (isTouched()) {
            if (Gdx.input.getX() > x && Gdx.input.getX() < x + width || Gdx.input.getY() > y && Gdx.input.getY() < y + height) {
                return true;
            }
        }
        return false;
    }

    public static Vector2 getPosition() {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }


}
