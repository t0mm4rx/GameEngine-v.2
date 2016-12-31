package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;

public class BoxRenderer extends Component{

    private float width, height, offsetX, offsetY;
    private Color color;
    private ShapeRenderer shapeRenderer;

    public BoxRenderer(AbstractGameObject go, float width, float height, Color color) {
        super(go);
        this.width = width;
        this.height = height;
        this.color = color;
        offsetX = 0;
        offsetY = 0;
        shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        Game.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(
                getGameObject().getTransform().getPosition().x - width / 2 + offsetX,
                getGameObject().getTransform().getPosition().y - height / 2 + offsetY,
                width,
                height
                );
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.batch.begin();
    }

    public void renderInHUD() {
        shapeRenderer.setProjectionMatrix(Game.HUDbatch.getProjectionMatrix());
        Game.HUDbatch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(
                getGameObject().getTransform().getPosition().x - width / 2,
                getGameObject().getTransform().getPosition().y - height / 2,
                width,
                height
        );
        shapeRenderer.end();
        Game.HUDbatch.begin();
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setWidth (float width) {
        this.width = width;
    }

    public void setHeight (float height) {
        this.height = height;
    }

    public void setOffset(float x, float y) {
        this.offsetY = y;
        this.offsetX = x;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
    public void update() {

    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
