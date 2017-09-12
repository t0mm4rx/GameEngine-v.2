package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.awt.Font;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;

public class Text extends Component {

    String text;
    BitmapFont font;
    GlyphLayout glyphLayout;
    Color color;
    float offsetX, offsetY;

    public Text(AbstractGameObject go, String text, Color color) {
        super(go);
        this.text = text;
        font = new BitmapFont();
        glyphLayout = new GlyphLayout();
        this.color = color;
        offsetX = 0;
        offsetY = 0;
    }

    public Text(AbstractGameObject go, BitmapFont font, String text, Color color) {
        super(go);
        this.text = text;
        this.font = font;
        glyphLayout = new GlyphLayout();
        this.color = color;
        offsetX = 0;
        offsetY = 0;
    }

    public Text(AbstractGameObject go, FileHandle fontFile, int size, String text, Color color) {
        super(go);
        this.text = text;
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = fontGenerator.generateFont(parameter);
        glyphLayout = new GlyphLayout();
        this.color = color;
    }

    public void render() {
        Game.batch.setProjectionMatrix(Game.getCurrentScreen().camera.combined.cpy().scale(0.01f, 0.01f, 1));
        glyphLayout.setText(font, text);
        font.getData().setScale(getGameObject().getTransform().getScale().x, getGameObject().getTransform().getScale().y);
        font.setColor(color);
        font.draw(Game.batch,
                text,
                getGameObject().getTransform().getPosition().x * 100 - glyphLayout.width / 2 + offsetX * 100,
                getGameObject().getTransform().getPosition().y * 100 - glyphLayout.height / 2 + offsetY * 100
        );
        Game.batch.setProjectionMatrix(Game.getCurrentScreen().camera.combined.cpy().scale(1f, 1f, 1f));
    }

    public void renderInHUD() {
        glyphLayout.setText(font, text);
        font.getData().setScale(getGameObject().getTransform().getScale().x, getGameObject().getTransform().getScale().y);
        font.setColor(color);
        font.draw(Game.HUDbatch,
                text,
                getGameObject().getTransform().getPosition().x - glyphLayout.width / 2 / 100 + offsetX,
                getGameObject().getTransform().getPosition().y - glyphLayout.height / 2 / 100 + offsetY
        );
    }

    public void setOffset(float x, float y) {
        this.offsetX = x;
        this.offsetY = y;
    }

    public String getText() {
        return text;
    }

    public Color getColor () {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public void dispose(){
        font.dispose();
    }
}
