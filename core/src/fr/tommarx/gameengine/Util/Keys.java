package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

public class Keys {

    private static Map<Integer, Integer> keys;
    //Number of frames without pressing the key, due to LibGDX bug...
    private final static int THREESHOLD = 2;

    public static void init() {
        keys = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            keys.put(i, THREESHOLD);
        }
    }

    public static void handleInputs() {
        for (int i = 0; i < 256; i++) {
            if (Gdx.input.isKeyJustPressed(i)) {
                keys.replace(i, 0);
            }
            if (!Gdx.input.isKeyPressed(i) && keys.get(i) < THREESHOLD) {
                keys.replace(i, keys.get(i) + 1);
            }
        }
    }

    public static boolean isKeyPressed(int i) {
        return (keys.get(i) < THREESHOLD ? true : false);
    }

}
