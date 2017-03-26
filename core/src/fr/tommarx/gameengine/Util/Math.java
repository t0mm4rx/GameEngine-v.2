package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Math {

    public static float DegreeToRadian(float degree) {
        return degree * 0.0174533f;
    }

    public static float RadianToDegree(float rad) {
        return rad / 0.0174533f;
    }

    public static float random(float min, float max) {
        return (float) (java.lang.Math.random() * ((max - min) + 1)) + min;
    }

    public static int randomInt(int min, int max) {
        return (int) Math.floor(Math.random(min, max));
    }

    public static Vector2 randomVector2(float range) {
        return new Vector2(random(-1, 1), random(-1, 1)).nor().scl(range);
    }

    public static float floor(float a) {
        return (float) java.lang.Math.floor(a);
    }
    public static float ceil(float a) {
        return (float) java.lang.Math.ceil(a);
    }

    public static float sqrt(float a) {
        return (float) java.lang.Math.sqrt(a);
    }

}
