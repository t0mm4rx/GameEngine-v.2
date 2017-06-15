package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.UUID;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.Easing.TweenListener;
import fr.tommarx.gameengine.Util.LayoutSorter;
import fr.tommarx.gameengine.Util.LightsRenderer;
import fr.tommarx.gameengine.Util.Math;

public abstract class Screen implements com.badlogic.gdx.Screen {

    protected ArrayList<Drawable> drawables;
    protected ArrayList<Drawable> drawablesHUD;
    public ArrayList<Drawable> toDelete;
    public OrthographicCamera camera;
    public Game game;
    private RayHandler rayHandler;
    public ShapeRenderer shapeRenderer;
    public PolygonSpriteBatch polyBatch;
    public World world;
    private Box2DDebugRenderer colliderRenderer;
    public boolean lightsEnabled;
    private Vector2 lastCamPosition;
    protected String id;
    private GameObject overlay;
    //Shaking
    private float shakingDuration = 0, shakingElapsed = 0, shakingIntensity = 0;
    private Vector3 shakingLastCam = new Vector3();
    private boolean isShaking = false;
    private Stage stage;
    private LightsRenderer lr;

    public Screen (Game game) {
        this.game = game;
        camera = new OrthographicCamera(Game.size.x, Game.size.y);
        camera.position.set(Game.center.x, Game.center.y, 0);
        drawables = new ArrayList<Drawable>();
        drawablesHUD = new ArrayList<Drawable>();
        toDelete = new ArrayList<Drawable>();
        world = new World(new Vector2(0, -9.8f), true);
        colliderRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        polyBatch = new PolygonSpriteBatch();
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera);
        rayHandler.setBlur(true);
        lightsEnabled = false;
        lastCamPosition = new Vector2();
        overlay = new GameObject(new Transform(Game.center));
        overlay.addComponent(new BoxRenderer(overlay, Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2, new Color(0f, 0f, 0f, 0f)));
        overlay.setLayout(1000);
        addInHUD(overlay);
        stage = new Stage();
        stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        id = UUID.randomUUID().toString();
        lr = new LightsRenderer();
        add(lr);
    }

    public abstract void renderBefore();
    public abstract void renderAfter();

    public AbstractGameObject getGameObjectByClass(String className) {

        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    return (AbstractGameObject) go;
                }
            }
        }
        System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public AbstractGameObject getGameObjectByClassInHUD(String className) {

        for (Drawable go : drawablesHUD) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    return (AbstractGameObject) go;
                }
            }
        }
        System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public ArrayList<AbstractGameObject> getGameObjectsByClass(String className) {
        ArrayList<AbstractGameObject> gos = new ArrayList<AbstractGameObject>();
        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    gos.add((AbstractGameObject) go);
                }
            }
        }
        return gos;
    }

    public ArrayList<AbstractGameObject> getGameObjectsByClassInHUD(String className) {
        ArrayList<AbstractGameObject> gos = new ArrayList<AbstractGameObject>();
        for (Drawable go : drawablesHUD) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    gos.add((AbstractGameObject) go);
                }
            }
        }
        return gos;
    }

    public ArrayList<AbstractGameObject> getGameObjectsByTag(String tag) {
        ArrayList<AbstractGameObject> gos = new ArrayList<AbstractGameObject>();
        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (((AbstractGameObject) go).getTag().equals(tag)) {
                    gos.add((AbstractGameObject) go);
                }
            }
        }
        return gos;
    }

    public AbstractGameObject getGameObjectByTag(String tag) {
        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (((AbstractGameObject) go).getTag().equals(tag)) {
                    return ((AbstractGameObject) go);
                }
            }
        }

        return null;
    }

    public ArrayList<AbstractGameObject> getGameObjectsByTagInHUD(String tag) {
        ArrayList<AbstractGameObject> gos = new ArrayList<AbstractGameObject>();
        for (Drawable go : drawablesHUD) {
            if (go.isGameObject()) {
                if (((AbstractGameObject) go).getTag().equals(tag)) {
                    gos.add((AbstractGameObject) go);
                }
            }
        }
        return gos;
    }

    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }

    public void add(Drawable go) { this.drawables.add(go); }

    public void addInHUD(Drawable go) {
        this.drawablesHUD.add(go);
    }

    public void remove(Drawable go) {
        toDelete.add(go);
    }

    private void kill(Drawable go) {
        ArrayList<Drawable> _toDelete = new ArrayList<Drawable>();
        for (Drawable d : drawables) {
            if (d.equals(go)) {
                _toDelete.add(d);
            }
        }
        for (Drawable d : drawablesHUD) {
            if (d.equals(go)) {
                _toDelete.add(d);
            }
        }
        drawables.removeAll(_toDelete);
        drawablesHUD.removeAll(_toDelete);
        for (Drawable d : _toDelete) {
            d.dispose();
        }
    }

    public void render (float delta) {
        world.step(1/45f, 6, 2);
        camera.update();
        Game.batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        polyBatch.setProjectionMatrix(Game.batch.getProjectionMatrix());

        if (isShaking) {
            if (shakingElapsed > shakingDuration) {
                camera.position.set(shakingLastCam);
                shakingDuration = 0;
                shakingElapsed = 0;
                shakingIntensity = 0;
                isShaking = false;
            } else {
                //System.out.println(shakingIntensity + " ==> " + Math.random(-shakingIntensity, shakingIntensity));
                camera.position.set(shakingLastCam.x + Math.random(-shakingIntensity, shakingIntensity) * (1 - shakingElapsed / shakingDuration), shakingLastCam.y + Math.random(-shakingIntensity, shakingIntensity) * (1 - shakingElapsed / shakingDuration), shakingLastCam.z);
                shakingElapsed += Gdx.graphics.getDeltaTime() * 1000;
            }
        }

        Vector2 cam_move = new Vector2(camera.position.x - lastCamPosition.x, camera.position.y - lastCamPosition.y);
        for (Drawable d : drawables) {
            if (d.isGameObject()) {
                ((AbstractGameObject) d).getTransform().setPosition(new Vector2(((AbstractGameObject) d).getTransform().getPosition().x + cam_move.x * d.getScrollingSpeed(), ((AbstractGameObject) d).getTransform().getPosition().y));
            }
        }
        lastCamPosition = new Vector2(camera.position.x, camera.position.y);

        renderBefore();
        for (Drawable d : LayoutSorter.sortByLayout(drawables)) {
            d.render();
            d.update();
        }
        renderAfter();

        if (Game.debugging) {
            //Draw gride
            Game.batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
            Game.getCurrentScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Vector2 a = new Vector2(camera.position.x - Game.size.x / 2 - 1, camera.position.y - Game.size.y / 2 - 1);
            Game.getCurrentScreen().shapeRenderer.setColor(new Color(255, 255, 255, 0.1f - camera.zoom / 200));
            for (float x = ((float)Math.ceil(a.x)) - (((int) camera.zoom) + 1) * 10; x < ((float)Math.ceil(a.x)) + Game.size.x + camera.zoom * 10; x += 1) {
                Game.getCurrentScreen().shapeRenderer.line(x, ((float)Math.ceil(a.y)) - (((int) camera.zoom) + 1) * 10, x, ((float)Math.ceil(a.y)) + Game.size.y + camera.zoom * 10);
            }
            for (float y = ((float)Math.ceil(a.y)) - (((int) camera.zoom) + 1) * 10; y < ((float)Math.ceil(a.y)) + Game.size.y + camera.zoom * 10; y += 1) {
                Game.getCurrentScreen().shapeRenderer.line(((float)Math.ceil(a.x)) - (((int) camera.zoom) + 1) * 10, y, ((float)Math.ceil(a.x)) + Game.size.x + camera.zoom * 10, y);
            }
            Game.getCurrentScreen().shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            Game.batch.begin();
            colliderRenderer.render(world, Game.batch.getProjectionMatrix().cpy().scale(1, 1, 0));
            stage.setDebugAll(true);
        } else {
            stage.setDebugAll(false);
            /**** Dafuq part *****/
            Game.batch.end();
            Game.batch.begin();
        }



        ArrayList<Drawable> toDelete2 = new ArrayList<Drawable>();
        for (Drawable d : toDelete) {
            kill(d);
            toDelete2.add(d);
        }
        toDelete.removeAll(toDelete2);

        update();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    public void renderHUD() {
        for (Drawable d : LayoutSorter.sortByLayout(drawablesHUD)) {
            d.renderInHUD();
            d.update();
        }
    }

    public ArrayList<AbstractGameObject> getGameObjects() {
        ArrayList<AbstractGameObject> gos = new ArrayList<AbstractGameObject>();
        for (Drawable d : drawables) {
            if (d.isGameObject()) {
                gos.add((AbstractGameObject) d);
            }
        }
        return gos;
    }

    public abstract void update();

    public void resize(int width, int height) {
        Game.center = new Vector2(new Float(width) / 2 / 100, new Float(height) / 2 / 100);
        Game.size = new Vector2(new Float(width) / 100, new Float(height) / 100);
        stage.getViewport().update(width, height);
        camera.setToOrtho(false, new Float(width) / 100, new Float(height) / 100);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {
        rayHandler.dispose();
        stage.dispose();
    }


    public void areLightsEnabled(boolean b) {
        lightsEnabled = b;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public void fadeIn(float time) {
        new Tween(Tween.LINEAR_EASE_NONE, time, 0, false, new TweenListener() {
            public void onValueChanged(float v) {
                ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, 1 - v));
            }
            public void onFinished() {
                ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, 0));
            }
        });
    }

    public void fadeOut(float time) {
        new Tween(Tween.LINEAR_EASE_NONE, time, 0, false, new TweenListener() {
            public void onValueChanged(float v) {
                ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, v));
            }
            public void onFinished() {
                ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, 1));
            }
        });
    }

    public Array<Body> getBodies() {
        Array<com.badlogic.gdx.physics.box2d.Body> bodies = new Array<com.badlogic.gdx.physics.box2d.Body>();
        Game.getCurrentScreen().world.getBodies(bodies);
        return bodies;
    }

    public void shake(float intensity, float duration) {
        shakingDuration = duration;
        shakingElapsed = 0;
        shakingIntensity = intensity;
        if (!isShaking) {
            shakingLastCam = camera.position.cpy();
        }
        isShaking = true;
    }

    public Stage getStage() {
        return stage;
    }

    public void setLightsLayout(int z) {
        lr.setLayout(z);
    }
}
