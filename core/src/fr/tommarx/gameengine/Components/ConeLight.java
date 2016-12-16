package fr.tommarx.gameengine.Components;


import com.badlogic.gdx.graphics.Color;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class ConeLight extends Component{
    private int power, length;
    private Color color;
    private box2dLight.ConeLight light;
    private float lastX, lastY, lastAngle, angle;
    private RayHandler rayHandler;

    public ConeLight(AbstractGameObject go, int power, int length, Color color, RayHandler rayHandler, float angle) {
        super(go);
        this.power = power;
        this.length = length;
        this.color = color;
        this.angle = angle;
        this.rayHandler = rayHandler;
        light = new box2dLight.ConeLight(rayHandler, power, color, length, go.getTransform().getPosition().x, go.getTransform().getPosition().y, go.getTransform().getRotation() + 90, angle );
        lastX = go.getTransform().getPosition().x;
        lastY = go.getTransform().getPosition().y;
        lastAngle = go.getTransform().getRotation();
    }

    public box2dLight.ConeLight getLight() {
        return light;
    }

    public void setAngle(float angle) {
        light.setConeDegree(angle);
    }

    public float getAngle() {
        return light.getConeDegree();
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        if (getGameObject().getTransform().getPosition().x != lastX || getGameObject().getTransform().getPosition().y != lastY || getGameObject().getTransform().getRotation() != lastAngle) {
            light.setPosition(getGameObject().getTransform().getPosition().x, getGameObject().getTransform().getPosition().y);
            light.setDirection(getGameObject().getTransform().getRotation());
            lastX = getGameObject().getTransform().getPosition().x;
            lastY = getGameObject().getTransform().getPosition().y;
            lastAngle = getGameObject().getTransform().getRotation();
        }
    }

    public void dispose() {
        light.setDistance(0);
    }
}
