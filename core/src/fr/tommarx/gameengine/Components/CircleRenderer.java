package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;

public class CircleRenderer extends Component{

    private Color color;
    private float radius;

    public CircleRenderer(AbstractGameObject go, float radius, Color color) {
        super(go);
        this.radius = radius;
        this.color = color;
    }

    public void render() {
        Game.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.getCurrentScreen().shapeRenderer.setColor(color);
        Game.getCurrentScreen().shapeRenderer.circle(getGameObject().getTransform().getPosition().x - radius / 2, getGameObject().getTransform().getPosition().y - radius / 2, radius);
        Game.getCurrentScreen().shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.batch.begin();
    }

    public void renderInHUD() {
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.HUDbatch.getProjectionMatrix());
        Game.HUDbatch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.getCurrentScreen().shapeRenderer.setColor(color);
        Game.getCurrentScreen().shapeRenderer.circle(getGameObject().getTransform().getPosition().x - radius / 2, getGameObject().getTransform().getPosition().y - radius / 2, radius);
        Game.getCurrentScreen().shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.HUDbatch.begin();
    }

    public void update() {

    }

    public void dispose() {

    }
}
