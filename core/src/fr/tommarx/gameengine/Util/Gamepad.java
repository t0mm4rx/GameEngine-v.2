package fr.tommarx.gameengine.Util;


import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

public class Gamepad {

    public static Array<Controller> getGamepads() {
        return Controllers.getControllers();
    }

    public static boolean isController() {
        return (getGamepads().size > 0 ? true : false);
    }

    public static Controller getController(int i) {
        if (getGamepads().size > 0)
            return Controllers.getControllers().get(i);
        return null;
    }

}
