package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.UUID;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.UI.UICanvas;
import fr.tommarx.gameengine.Util.LayoutSorter;

public abstract class Screen implements com.badlogic.gdx.Screen {

    protected ArrayList<Drawable> drawables;
    protected ArrayList<Drawable> drawablesHUD;
    public ArrayList<Drawable> toDelete;
    public OrthographicCamera camera;
    public Game game;
    private RayHandler rayHandler;
    public World world;
    private Box2DDebugRenderer colliderRenderer;
    private boolean lightsEnabled;
    private Vector2 lastCamPosition;
    protected String id;
    private GameObject overlay;

    public Screen (Game game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        drawables = new ArrayList<Drawable>();
        drawablesHUD = new ArrayList<Drawable>();
        toDelete = new ArrayList<Drawable>();
        world = new World(new Vector2(0, -9.8f), true);
        colliderRenderer = new Box2DDebugRenderer();
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera);
        rayHandler.setBlur(true);
        lightsEnabled = false;
        lastCamPosition = new Vector2();
        overlay = new GameObject(new Transform(Game.center));
        overlay.addComponent(new BoxRenderer(overlay, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Color(0f, 0f, 0f, 0f)));
        overlay.setLayout(1000);
        addInHUD(overlay);
        id = UUID.randomUUID().toString();
    }

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

    public void add(Drawable go) {
        this.drawables.add(go);
    }

    public void addInHUD(Drawable go) {
        this.drawablesHUD.add(go);
    }

    public void remove(Drawable go) {
        toDelete.add(go);
    }

    public void kill(Drawable go) {
        ArrayList<Drawable> toDelete = new ArrayList<Drawable>();
        for (Drawable d : drawables) {
            if (d.equals(go)) {
                toDelete.add(d);
            }
        }
        for (Drawable d : drawablesHUD) {
            if (d.equals(go)) {
                toDelete.add(d);
            }
        }
        for (Drawable d : toDelete) {
            d.dispose();
        }
        drawables.removeAll(toDelete);
        drawablesHUD.removeAll(toDelete);
    }

    public void render (float delta) {
        world.step(1/45f, 6, 2);
        camera.update();
        Game.batch.setProjectionMatrix(camera.combined);

        Vector2 cam_move = new Vector2(camera.position.x - lastCamPosition.x, camera.position.y - lastCamPosition.y);
        for (Drawable d : drawables) {
            if (d.isGameObject()) {
                ((AbstractGameObject) d).getTransform().setPosition(new Vector2(((AbstractGameObject) d).getTransform().getPosition().x + cam_move.x * d.getScrollingSpeed(), ((AbstractGameObject) d).getTransform().getPosition().y));
            }
        }
        lastCamPosition = new Vector2(camera.position.x, camera.position.y);

        for (Drawable d : LayoutSorter.sortByLayout(drawables)) {
            d.render();
            d.update();
        }

        if (lightsEnabled) {
            Game.batch.end();
            rayHandler.setCombinedMatrix(camera);
            rayHandler.updateAndRender();
            Game.batch.begin();
        }

        if (Game.debugging) {
            colliderRenderer.render(world, Game.batch.getProjectionMatrix().cpy().scale(100, 100, 0));
        }

        for (Drawable d : toDelete) {
            kill(d);
        }

        ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, Game.tweenManager.getValue("Fade:" + id)));

        update();

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
        Game.center = new Vector2(width / 2, height / 2);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {
        rayHandler.dispose();
        for (Drawable canvas : drawables) {
            if (canvas.getClass().getSimpleName().equals("UICanvas")) {
                ((UICanvas) canvas).dispose();
            }
        }
        for (Drawable canvas : drawablesHUD) {
            if (canvas.getClass().getSimpleName().equals("UICanvas")) {
                ((UICanvas) canvas).dispose();
            }
        }
    }


    public void areLightsEnabled(boolean b) {
        lightsEnabled = b;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public void fadeIn(float time) {
        Game.tweenManager.goTween(new Tween("Fade:" + id, Tween.LINEAR_EASE_NONE, 1f, -1f, time, 0f, false));
    }

    public void fadeOut(float time) {
        Game.tweenManager.goTween(new Tween("Fade:" + id, Tween.LINEAR_EASE_NONE, 0f, 1f, time, 0f, false));
    }


}
