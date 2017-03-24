package fr.tommarx.gameengine.Util;


import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;

public class Gamepad {

    public static Array<Controller> getGamepads() {
        return Controllers.getControllers();
    }

    public static Controller getController(int i) {
        return Controllers.getControllers().get(i);
    }

}
