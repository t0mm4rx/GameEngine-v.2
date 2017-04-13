package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.IO.Touch;
import fr.tommarx.gameengine.JSON.JSONObject;
import fr.tommarx.gameengine.Net.HTTP;
import fr.tommarx.gameengine.Net.HTTPListener;
import fr.tommarx.gameengine.Util.Math;
import fr.tommarx.gameengine.Util.Util;

public class NetScreen extends Screen {

    public NetScreen(Game game) {
        super(game);
    }

    BitmapFont font1, font2;
    GlyphLayout glyphLayout;
    Texture texture;
    String lat = "loading...", lon = "loading...", weather = "loading...";

    public void show() {
        Game.debugging = true;
        font1 = new BitmapFont();
        font2 = Util.ttfToBitmap(Gdx.files.internal("font.ttf"), 22);
        glyphLayout = new GlyphLayout();
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        TextField city = new TextField("", new Skin(Gdx.files.internal("skin/uiskin.json")));
        city.setMessageText("City name");
        city.setPosition(Gdx.graphics.getWidth() / 2 / 100, Gdx.graphics.getHeight() / 2 / 100);
        getStage().addActor(city);

        Button button = new TextButton("Search", new Skin(Gdx.files.internal("skin/uiskin.json")));
        button.setPosition(Gdx.graphics.getWidth() / 2 / 100 + 300, Gdx.graphics.getHeight() / 2 / 100);
        button.addListener(new EventListener() {
            public boolean handle(Event event) {
                getPos(city.getText());
                return false;
            }
        });
        getStage().addActor(button);
        //camera.zoom = 5;
    }

    public void getPos(String city) {
        HTTP.get("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=e8101be5026a00cd099f42056cd940a3", new HTTPListener() {
            public void onFinish(String result) {
                JSONObject json = new JSONObject(result);
                lon = json.getJSONObject("coord").get("lon").toString();
                lat = json.getJSONObject("coord").get("lat").toString();
                weather = new JSONObject(json.getJSONArray("weather").get(0).toString()).getString("main").toString();
            }

            public void onProgress(float percentage) {}

            public void onFail(String message) {
                System.err.println("Failed...\n" + message);
            }
        });
    }

    public void update() {
        if (Keys.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -0.5f);
        }
        if (Keys.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 0.5f);
        }
        if (Keys.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-0.5f, 0);
        }
        if (Keys.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(0.5f, 0);
        }
        if (Keys.isKeyPressed(Input.Keys.NUMPAD_9)) {
            camera.zoom -= 0.1f;
        }
        if (Keys.isKeyPressed(Input.Keys.NUMPAD_8)) {
            camera.zoom += 0.1f;
        }
        if (Keys.isKeyJustPressed(Input.Keys.R)) {
            shake(1, 500);
        }
        if (Touch.isJustTouched()) {
            System.out.println(Touch.getPosition());
        }
        Game.debug(1, "FPS : " + Gdx.graphics.getFramesPerSecond());
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
        Draw.text("Weather : " + weather, 5, 2.6f, Color.WHITE, font2, glyphLayout);
    }

}
