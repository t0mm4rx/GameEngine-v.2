package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.math.Vector2;

public class Transform extends Component {

    private Vector2 position, scale;
    private float rotation;

    public Transform (Vector2 position, Vector2 scale, float rotation) {
        super(null);
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Transform (Vector2 position) {
        super(null);
        this.position = position;
        this.scale = new Vector2(1, 1);
        this.rotation = 0;
    }

    public Transform () {
        super(null);
        this.position = new Vector2(0, 0);
        this.scale = new Vector2(1, 1);
        this.rotation = 0;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getScale() {
        return scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void render() {}

    public void renderInHUD() {}

    public void update() {}

    public void dispose() {}

    public Transform cpy() {
        return new Transform(this.getPosition().cpy(), this.getScale().cpy(), this.getRotation());
    }
}
