package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;

import java.util.concurrent.Callable;

import fr.tommarx.gameengine.Easing.TweenManager;
import fr.tommarx.gameengine.IO.Keys;
import fr.tommarx.gameengine.IO.Touch;
import fr.tommarx.gameengine.Util.WaitAndDo;


public abstract class Game extends com.badlogic.gdx.Game {

    public static SpriteBatch batch;
    public static SpriteBatch HUDbatch;
    public static boolean debugging;

    private static String[] debugInfos;
    private BitmapFont font;
    private static Screen screen;
    public static TweenManager tweenManager;
    private static WaitAndDo waitAndDo;
    public static Vector2 center;

    public void create() {
        Box2D.init();
        debugging = false;
        debugInfos = new String[10];
        batch = new SpriteBatch();
        HUDbatch = new SpriteBatch();
        font = new BitmapFont();
        tweenManager = new TweenManager();
        waitAndDo = new WaitAndDo();
        center = new Vector2(Gdx.graphics.getWidth() / 2 / 100, Gdx.graphics.getHeight() / 2 / 100);
        init();
        Keys.init();
    }

    public abstract void init();

    public void render() {
        tweenManager.update();
        waitAndDo.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        super.render();
        batch.end();
        HUDbatch.begin();
        if (debugging) {
            for (int i = 0; i < debugInfos.length; i++) {
                if (debugInfos[i] != null) {
                    font.draw(HUDbatch, debugInfos[i], 2 , Gdx.graphics.getHeight() - (i + 1) * 15);
                }
            }
        }
        if (screen != null) {
            screen.renderHUD();
        }
        HUDbatch.end();

        debugInfos = new String[10];
        Keys.handleInputs();
        Touch.handleInputs();
    }

    protected void stop() {
        batch.dispose();
        HUDbatch.dispose();
    }


    public static void debug(int line, String text) {
        if (line > 0 && line < 11) {
            debugInfos[line - 1] = text;
        } else {
            System.out.println("Error with debug fonction ! Line must be > 0 and <= 10");
        }
    }

    public static void debug(int line, Vector2 v) {
        if (line > 0 && line < 11) {
            debugInfos[line - 1] = "X: " + v.x + "  , Y: " + v.y;
        } else {
            System.out.println("Error with debug fonction ! Line must be > 0 and <= 10");
        }
    }

    public static void debug(int line, Vector3 v) {
        if (line > 0 && line < 11) {
            debugInfos[line - 1] = "X: " + v.x + "  , Y: " + v.y;
        } else {
            System.out.println("Error with debug fonction ! Line must be > 0 and <= 10");
        }
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        super.setScreen(screen);
    }

    public static Screen getCurrentScreen() {
        return screen;
    }

    public static void waitAndDo (float time, Callable callable) {
        waitAndDo.WaitAndDo(time, callable);
    }

}
