package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.Map;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;

public class Tile {

    private int id, width, height;
    private String image, name;
    private boolean isSolid;
    private Map<String, String> properties;

    public Tile(int id, String image, String name, int width, int height, Map<String, String> properties) {
        this.id = id;
        this.image = image;
        this.width = width;
        this.height = height;
        this.name = name;
        this.properties = properties;
        this.isSolid = Boolean.parseBoolean(properties.get("isSolid"));
    }

    public void generateGameObject(float x, float y) {
        EmptyGameObject go = new EmptyGameObject(new Transform(new Vector2(x, y)));
        go.addComponent(new SpriteRenderer(go, Gdx.files.internal(image)));
        if (isSolid) {
            go.addComponent(new BoxBody(go, width, height, BodyDef.BodyType.StaticBody));
        }
        go.setLayout(id);
        go.setTag(name);
        Game.getCurrentScreen().add(go);
    }

}
