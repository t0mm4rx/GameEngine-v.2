package fr.tommarx.gameengine.Collisions;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;

public class CollisionsManager {

    public CollisionsManager (final CollisionsListener collisionsListener) {
        Game.getCurrentScreen().world.setContactListener(new ContactListener() {
            public void beginContact(Contact contact) {
                GameObject a = getGameObjectByBody(contact.getFixtureA().getBody());
                GameObject b = getGameObjectByBody(contact.getFixtureB().getBody());
                collisionsListener.collisionEnter(a, b);
            }

            public void endContact(Contact contact) {
                GameObject a = getGameObjectByBody(contact.getFixtureA().getBody());
                GameObject b = getGameObjectByBody(contact.getFixtureB().getBody());
                collisionsListener.collisionEnd(a, b);
            }

            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public GameObject getGameObjectByBody (Body body) {
        for (GameObject go : Game.getCurrentScreen().getGameObjects()) {
            if (go.getComponentByClass("CircleBody") != null) {
                if (((CircleBody)go.getComponentByClass("CircleBody")).getBody().equals(body)) {
                    return go;
                }
            }
            if (go.getComponentByClass("BoxBody") != null) {
                if (((BoxBody)go.getComponentByClass("BoxBody")).getBody().equals(body)) {
                    return go;
                }
            }
        }
        return null;
    }

}
