package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

public class Keys {

    //IsPressed
    private static Map<Integer, Integer> keys;
    //IsJustPressed
    private static Map<Integer, Integer> keys1;
    //Number of frames without pressing the key, due to LibGDX bug...
    private final static int THREESHOLD = 2;

    public static void init() {
        keys = new HashMap<>();
        keys1 = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            keys.put(i, THREESHOLD);
            keys1.put(i, 0);
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
            if (keys1.get(i).equals(1)) {
                keys1.replace(i, 2);
            }
            if (Gdx.input.isKeyJustPressed(i) && keys1.get(i).equals(0)) {
                keys1.replace(i, 1);
            }
            if (!Keys.isKeyPressed(i) && keys1.get(i).equals(2)) {
                keys1.replace(i, 0);
            }
        }
    }

    public static boolean isKeyPressed(int i) {
        return (keys.get(i) < THREESHOLD ? true : false);
    }
    public static boolean isKeyJustPressed(int i) {
        return (keys1.get(i) == 1 ? true : false);
    }

}
