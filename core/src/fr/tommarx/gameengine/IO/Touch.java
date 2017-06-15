package fr.tommarx.gameengine.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import fr.tommarx.gameengine.Game.Game;

public class Touch {

    private static boolean hasNotified = false;

    public static boolean isTouched() {
        return Gdx.input.isTouched();
    }

    public static boolean isTouched(float x, float y, float width, float height) {
        if (isTouched()) {
            Vector2 pos = getProjectedPosition();
            if (pos.x > x - width / 2 && pos.x < x + width / 2 && pos.y > y - height / 2 && pos.y < y + height / 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isJustTouched() {
        if (!hasNotified && isTouched()) {
            hasNotified = true;
            return true;
        } else {
            return false;
        }
    }

    public static boolean isJustTouched(float x, float y, float width, float height) {
        if (isJustTouched()) {
            Vector2 pos = getProjectedPosition();
            if (pos.x > x - width / 2 && pos.x < x + width / 2 && pos.y > y - height / 2 && pos.y < y + height / 2) {
                return true;
            }
        }
        return false;
    }

    public static void handleInputs() {
        if (hasNotified && !isTouched()) {
            hasNotified = false;
        }
    }

    public static Vector2 getPosition() {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }

    public static Vector2 getProjectedPosition() {
        Vector3 v = Game.getCurrentScreen().camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Vector2(v.x, v.y);
    }


}
