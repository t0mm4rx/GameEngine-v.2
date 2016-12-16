package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collections;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.UI.UICanvas;
import fr.tommarx.gameengine.Util.LayoutSorter;

public abstract class Screen implements com.badlogic.gdx.Screen {

    protected ArrayList<Drawable> drawables;
    protected ArrayList<Drawable> drawablesHUD;
    public ArrayList<Drawable> toDelete;
    public OrthographicCamera camera;
    protected Game game;
    public RayHandler rayHandler;
    public World world;
    private Box2DDebugRenderer colliderRenderer;
    private boolean lightsEnabled;
    private Vector2 lastCamPosition;

    public Screen (Game game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        //gameObjects = new ArrayList<GameObject>();
        drawables = new ArrayList<Drawable>();
        drawablesHUD = new ArrayList<Drawable>();
        toDelete = new ArrayList<Drawable>();
        //hud = new ArrayList<GameObject>();
        world = new World(new Vector2(0, -98f), true);
        colliderRenderer = new Box2DDebugRenderer();
        rayHandler = new RayHandler(world);
        rayHandler.setCombinedMatrix(camera);
        rayHandler.setBlur(true);
        lightsEnabled = false;
        lastCamPosition = new Vector2();
    }

    public GameObject getGameObjectByClass(String className) {

        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    return (GameObject) go;
                }
            }
        }
        System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public GameObject getGameObjectByClassInHUD(String className) {

        for (Drawable go : drawablesHUD) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    return (GameObject) go;
                }
            }
        }
        System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public ArrayList<GameObject> getGameObjectsByClass(String className) {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    gos.add((GameObject) go);
                }
            }
        }
        return gos;
    }

    public ArrayList<GameObject> getGameObjectsByClassInHUD(String className) {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (Drawable go : drawablesHUD) {
            if (go.isGameObject()) {
                if (go.getClass().getSimpleName().equals(className)) {
                    gos.add((GameObject) go);
                }
            }
        }
        return gos;
    }

    public ArrayList<GameObject> getGameObjectsByTag(String tag) {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (((GameObject) go).getTag().equals(tag)) {
                    gos.add((GameObject) go);
                }
            }
        }
        return gos;
    }

    public GameObject getGameObjectByTag(String tag) {
        for (Drawable go : drawables) {
            if (go.isGameObject()) {
                if (((GameObject) go).getTag().equals(tag)) {
                    return ((GameObject) go);
                }
            }
        }

        return null;
    }

    public ArrayList<GameObject> getGameObjectsByTagInHUD(String tag) {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (Drawable go : drawablesHUD) {
            if (go.isGameObject()) {
                if (((GameObject) go).getTag().equals(tag)) {
                    gos.add((GameObject) go);
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
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        camera.update();
        Game.batch.setProjectionMatrix(camera.combined);

        Vector2 cam_move = new Vector2(camera.position.x - lastCamPosition.x, camera.position.y - lastCamPosition.y);
        for (Drawable d : drawables) {
            if (d.isGameObject()) {
                ((GameObject) d).getTransform().setPosition(new Vector2(((GameObject) d).getTransform().getPosition().x + cam_move.x * d.getScrollingSpeed(), ((GameObject) d).getTransform().getPosition().y));
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
            colliderRenderer.render(world, Game.batch.getProjectionMatrix().cpy());
        }

        for (Drawable d : toDelete) {
            kill(d);
        }

        update();

    }

    public void renderHUD() {
        for (Drawable d : LayoutSorter.sortByLayout(drawablesHUD)) {
            d.renderInHUD();
            d.update();
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (Drawable d : drawables) {
            if (d.isGameObject()) {
                gos.add((GameObject) d);
            }
        }
        return gos;
    }

    public abstract void update();

    public void resize(int width, int height) {

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


}
