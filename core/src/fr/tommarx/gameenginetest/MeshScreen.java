package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import fr.tommarx.gameengine.Game.Draw;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MeshScreen extends Screen {

    ShaderProgram shader;
    Texture image;
    float frames = 0;

    public MeshScreen(Game game) {
        super(game);
    }

    public void show() {
        Game.debugging = true;
        shader = new ShaderProgram(Gdx.files.internal("shaders/test.vertex").readString(), Gdx.files.internal("shaders/test.fragment").readString());
        if (!shader.isCompiled())
            System.out.println(shader.getLog());
        image = new Texture("badlogic.jpg");
    }

    public void renderBefore() {
        shader.setAttributef("a_offset", (float) Math.sin(frames/60 * Math.PI) * 0.5f, (float) Math.sin(frames/60 * Math.PI) * 0.5f, 0, 0);
        Game.batch.setShader(shader);
        Draw.texture(image, Game.center.x, Game.center.y);
        Game.batch.setShader(null);
    }

    public void renderAfter() {

    }

    public void update() {
        frames++;
        if (frames > 60)
            frames = 0;
    }

}
