package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.AbstractGameObject;

public class SpriteRenderer extends Component {

    private TextureRegion texture;
    private float width;
    private float height;
    private boolean flippedX, flippedY;

    public SpriteRenderer (AbstractGameObject go, FileHandle texture) {
        super(go);
        this.texture = new TextureRegion(new Texture(texture));
        width = getTexture().getWidth() / 100;
        height = getTexture().getHeight() / 100;
        flippedX = false;
        flippedY = false;
    }

    public SpriteRenderer (AbstractGameObject go, FileHandle texture, float offsetX, float offsetY) {
        super(go);
        this.texture = new TextureRegion(new Texture(texture));
        width = getTexture().getWidth() / 100;
        height = getTexture().getHeight() / 100;
        flippedX = false;
        flippedY = false;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public SpriteRenderer (AbstractGameObject go, FileHandle texture, float offsetX, float offsetY, float width, float height) {
        super(go);
        this.texture = new TextureRegion(new Texture(texture));
        this.width = width;
        this.height = height;
        flippedX = false;
        flippedY = false;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }


    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void render() {
        Game.batch.draw(texture,
                getGameObject().getTransform().getPosition().x - width / 2 + offsetX,
                getGameObject().getTransform().getPosition().y - height / 2 + offsetY,
                width / 2,
                height / 2,
                width,
                height,
                getGameObject().getTransform().getScale().x,
                getGameObject().getTransform().getScale().y,
                getGameObject().getTransform().getRotation());

    }

    public void renderInHUD() {
        Game.HUDbatch.draw(texture,
                getGameObject().getTransform().getPosition().x - width / 2 + offsetX,
                getGameObject().getTransform().getPosition().y - height / 2 + offsetY,
                width / 2,
                height / 2,
                width,
                height,
                getGameObject().getTransform().getScale().x,
                getGameObject().getTransform().getScale().y,
                getGameObject().getTransform().getRotation());
    }

    public Texture getTexture() {
        return texture.getTexture();
    }

    public void setSize(float w, float h) {
        width = w;
        height = h;
    }

    public void scaleWidth(float w) {
        width = w * Gdx.graphics.getWidth() / 100;
        height = (getTexture().getWidth() / getTexture().getHeight()) * w * Gdx.graphics.getHeight() / 100;
    }

    public void scaleHeight(float h) {
        height = h * Gdx.graphics.getHeight() / 100;
        width = (getTexture().getWidth() / getTexture().getHeight()) * h * Gdx.graphics.getWidth() / 100;
    }

    public void flip(boolean x, boolean y) {
        if ((!texture.isFlipX() && x) || (texture.isFlipX() && !x)) {
            texture.flip(true, texture.isFlipY());
        }
        if ((!texture.isFlipY() && y) || (texture.isFlipY() && !y)) {
            texture.flip(texture.isFlipX(), true);
        }
    }

    public void update() {
        getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
    }

    public void dispose() {
    }
}
