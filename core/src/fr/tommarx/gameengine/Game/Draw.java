package fr.tommarx.gameengine.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Draw {

    public static void text(String text, float x, float y, Color color, BitmapFont font, GlyphLayout glyphLayout) {
        Game.batch.setProjectionMatrix(Game.getCurrentScreen().camera.combined.cpy().scale(0.01f, 0.01f, 1));
        glyphLayout.setText(font, text);
        font.setColor(color);
        font.draw(Game.batch,
                text,
                x * 100 - glyphLayout.width / 2,
                y * 100 - glyphLayout.height / 2
        );
        Game.batch.setProjectionMatrix(Game.getCurrentScreen().camera.combined.cpy().scale(1f, 1f, 1f));
    }

    public static void texture(Texture texture, float x, float y) {
        Game.batch.draw(texture, x - texture.getWidth() / 2 / 100, y - texture.getHeight() / 2 / 100, texture.getWidth() / 100, texture.getHeight() / 100);
    }

    public static void texture(Texture texture, float x, float y, float width, float height) {
        Game.batch.draw(texture, x - height / 2, y - width / 2, width, height);
    }

    public static void rect(float x, float y, float width, float height, Color color) {
        Game.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.getCurrentScreen().shapeRenderer.setColor(color);
        Game.getCurrentScreen().shapeRenderer.rect(
                x - width / 2,
                y - height / 2,
                width,
                height
        );
        Game.getCurrentScreen().shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.batch.begin();
    }

    public static void ellipse(float x, float y, float width, float height, Color color) {
        Game.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.getCurrentScreen().shapeRenderer.setColor(color);
        Game.getCurrentScreen().shapeRenderer.ellipse((x - width / 2), (y - height / 2), width, height, 100);
        Game.getCurrentScreen().shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.batch.begin();
    }

    public static void circle(float x, float y, float radius, Color color) {
        Game.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Game.getCurrentScreen().shapeRenderer.setColor(color);
        Game.getCurrentScreen().shapeRenderer.circle(x - radius / 2, y - radius / 2, radius, 100);
        Game.getCurrentScreen().shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.batch.begin();
    }

    public static void line(float x1, float y1, float x2, float y2, Color color) {
        Game.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Game.getCurrentScreen().shapeRenderer.setColor(color);
        Game.getCurrentScreen().shapeRenderer.line(x1, y1, x2, y2);
        Game.getCurrentScreen().shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        Game.batch.begin();
    }

    public static void line(Vector2 p1, Vector2 p2, Color color) {
        line(p1.x, p1.y, p2.x, p2.y, color);
    }

}
