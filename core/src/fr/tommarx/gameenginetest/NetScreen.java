package fr.tommarx.gameenginetest;

import java.util.HashMap;
import java.util.Map;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Net.HTTP;
import fr.tommarx.gameengine.Net.HTTPListener;

public class NetScreen extends Screen {

    public NetScreen(Game game) {
        super(game);
    }

    public void show() {
        HTTP.get("https://faceplusplus-faceplusplus.p.mashape.com/detection/detect?attribute=glass%2Cpose%2Cgender%2Cage%2Crace%2Csmiling&url=http%3A%2F%2Fwww.faceplusplus.com%2Fwp-content%2Fthemes%2Ffaceplusplus%2Fassets%2Fimg%2Fdemo%2F1.jpg", new HTTPListener() {
            public void onFinish(String result) {
                System.out.println("Finished ! Data = " + result);
            }

            public void onProgress(float percentage) {
                System.out.println("Percentage = " + percentage + "%");
            }

            public void onFail(String message) {
                System.out.println("Failed...\n" + message);
            }
        });
    }

    public void update() {

    }

}
