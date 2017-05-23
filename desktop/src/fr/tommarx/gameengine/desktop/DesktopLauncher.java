package fr.tommarx.gameengine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.tommarx.gameenginetest.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GameEngine v.2 - Testing";
		config.width = 1080;
		config.height = 720;
		//config.resizable = false;
		new LwjglApplication(new GameClass(), config);
	}
}
