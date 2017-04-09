package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.JSON.JSONObject;
import fr.tommarx.gameengine.Net.HTTP;
import fr.tommarx.gameengine.Net.HTTPListener;
import fr.tommarx.gameengine.Util.Keys;

public class NetScreen extends Screen {

    public NetScreen(Game game) {
        super(game);
    }

    public void show() {
        TextField city = new TextField("", new Skin(Gdx.files.internal("skin/uiskin.json")));
        city.setMessageText("City name");
        city.setPosition(Gdx.graphics.getWidth() / 2 / 100, Gdx.graphics.getHeight() / 2 / 100);
        getStage().addActor(city);

        Button button = new TextButton("Search", new Skin(Gdx.files.internal("skin/uiskin.json")));
        button.setPosition(Gdx.graphics.getWidth() / 2 + 300, Gdx.graphics.getHeight() / 2);
        button.addListener(new EventListener() {
            public boolean handle(Event event) {
                getPos(city.getText());
                return false;
            }
        });
        getStage().addActor(button);

        GameObject box = new GameObject(new Transform(Game.center));
        box.addComponent(new BoxBody(box, 1, 1, BodyDef.BodyType.StaticBody, false));
        add(box);

    }

    public void getPos(String city) {
        HTTP.get("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=e8101be5026a00cd099f42056cd940a3", new HTTPListener() {
            public void onFinish(String result) {
                JSONObject json = new JSONObject(result);
                String lon = json.getJSONObject("coord").get("lon").toString();
                String lat = json.getJSONObject("coord").get("lat").toString();
                Vector2 v = new Vector2(Float.parseFloat(lon), Float.parseFloat(lat));
                System.out.println("Lat : " + lat + " && lon : " + lon);
            }

            public void onProgress(float percentage) {}

            public void onFail(String message) {
                System.err.println("Failed...\n" + message);
            }
        });
    }

    public void update() {
        Game.debug(1, camera.position.cpy());
        if (Keys.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }
        if (Keys.isKeyPressed(Input.Keys.P)) {
            camera.zoom += 0.01f;
        }
        if (Keys.isKeyPressed(Input.Keys.O)) {
            camera.zoom -= 0.01f;
        }
        if (Keys.isKeyPressed(Input.Keys.UP)) {
            camera.position.y += 0.1f;
        }
        if (Keys.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y -= 0.1f;
        }
        if (Keys.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x -= 0.1f;
        }
        if (Keys.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x += 0.1f;
        }
    }

}
