package fr.tommarx.gameengine.Components;


import com.badlogic.gdx.graphics.Color;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;

public class ConeLight extends Component{
    private int power, length;
    private Color color;
    private box2dLight.ConeLight light;
    private float direction;
    private float lastX, lastY;
    private RayHandler rayHandler;

    public ConeLight(AbstractGameObject go, int power, int length, Color color, float direction, float field) {
        super(go);
        this.power = power;
        this.length = length;
        this.color = color;
        this.direction = direction;
        this.rayHandler = Game.getCurrentScreen().getRayHandler();
        light = new box2dLight.ConeLight(rayHandler, power, color, length, go.getTransform().getPosition().x, go.getTransform().getPosition().y, direction, field);
        lastX = go.getTransform().getPosition().x;
        lastY = go.getTransform().getPosition().y;
    }

    public box2dLight.ConeLight getLight() {
        return light;
    }

    public void setField(float field) {
        light.setConeDegree(field);
    }

    public void setDirection(float angle) {
        light.setDirection(angle);
        this.direction = angle;
    }

    public float getDirection() { return direction; }

    public float getAngle() {
        return light.getConeDegree();
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
        light.setDistance(0);
        light.dispose();
    }
}
