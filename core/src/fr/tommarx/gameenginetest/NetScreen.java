package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.JSON.JSONObject;
import fr.tommarx.gameengine.Net.HTTP;
import fr.tommarx.gameengine.Net.HTTPListener;
import fr.tommarx.gameengine.Util.Util;

public class NetScreen extends Screen {

    public NetScreen(Game game) {
        super(game);
    }

    BitmapFont font1, font2;
    GlyphLayout glyphLayout;
    Texture texture;
    String lat = "loading...", lon = "loading...";

    public void show() {
        Game.debugging = true;
        font1 = new BitmapFont();
        font2 = Util.ttfToBitmap(Gdx.files.internal("font.ttf"), 22);
        glyphLayout = new GlyphLayout();
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        HTTP.get("http://api.openweathermap.org/data/2.5/weather?q=Caluire&APPID=e8101be5026a00cd099f42056cd940a3", new HTTPListener() {
            public void onFinish(String result) {
                //System.out.println("Finished ! Data = " + result);
                JSONObject json = new JSONObject(result);
                lon = json.getJSONObject("coord").get("lon").toString();
                lat = json.getJSONObject("coord").get("lat").toString();
            }

            public void onProgress(float percentage) {}

            public void onFail(String message) {
                System.err.println("Failed...\n" + message);
            }
        });
    }

    public void update() {

    }

    public void renderBefore() {
        Draw.texture(texture, 1, 1, 1, 1);
        Draw.rect(5.5f, 5, 1, 0.5f, Color.BLUE);
        Draw.circle(9, 1, 1, Color.WHITE);
        Draw.ellipse(9, 4, 1, 2, Color.WHITE);
    }

    public void renderAfter() {
        Draw.text("FPS : " + Gdx.graphics.getFramesPerSecond(), 5, 5, Color.WHITE, font1, glyphLayout);
        Draw.text("Lat : " + lat, 5, 3, Color.WHITE, font2, glyphLayout);
        Draw.text("Lon : " + lon, 5, 2.8f, Color.WHITE, font2, glyphLayout);
    }

}
