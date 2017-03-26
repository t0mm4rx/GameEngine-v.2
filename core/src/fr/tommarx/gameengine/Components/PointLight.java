package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.graphics.Color;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;

public class PointLight extends Component {

    private int power, length;
    private Color color;
    private box2dLight.PointLight light;
    private float lastX, lastY;
    private RayHandler rayHandler;

    public PointLight(AbstractGameObject go, int power, int length, Color color) {
        super(go);
        this.power = power;
        this.length = length;
        this.color = color;
        this.rayHandler = Game.getCurrentScreen().getRayHandler();
        light = new box2dLight.PointLight(rayHandler, power, color, length, go.getTransform().getPosition().x, go.getTransform().getPosition().y);
        lastX = go.getTransform().getPosition().x;
        lastY = go.getTransform().getPosition().y;

    }

    public box2dLight.PointLight getLight() {
        return light;
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        if (getGameObject().getTransform().getPosition().x != lastX || getGameObject().getTransform().getPosition().y != lastY) {
            light.setPosition(getGameObject().getTransform().getPosition().x, getGameObject().getTransform().getPosition().y);
            lastX = getGameObject().getTransform().getPosition().x;
            lastY = getGameObject().getTransform().getPosition().y;
        }
    }

    public void dispose() {
        //light.dispose();
        light.setDistance(0);
    }
}
