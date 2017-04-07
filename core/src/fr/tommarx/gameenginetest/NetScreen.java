package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.JSON.JSONObject;
import fr.tommarx.gameengine.Net.HTTP;
import fr.tommarx.gameengine.Net.HTTPListener;

public class NetScreen extends Screen {

    public NetScreen(Game game) {
        super(game);
    }

    public void show() {
        HTTP.get("http://api.openweathermap.org/data/2.5/weather?q=Caluire&APPID=e8101be5026a00cd099f42056cd940a3", new HTTPListener() {
            public void onFinish(String result) {
                //System.out.println("Finished ! Data = " + result);
                JSONObject json = new JSONObject(result);
                String lon = json.getJSONObject("coord").get("lon").toString();
                String lat = json.getJSONObject("coord").get("lat").toString();
                System.out.println("Lon : " + lon);
                System.out.println("Lat : " + lat);
            }

            public void onProgress(float percentage) {}

            public void onFail(String message) {
                System.err.println("Failed...\n" + message);
            }
        });
    }

    public void update() {

    }

}
