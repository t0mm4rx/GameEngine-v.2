package fr.tommarx.gameengine.Collisions;

import com.badlogic.gdx.physics.box2d.Contact;

import fr.tommarx.gameengine.Game.AbstractGameObject;

public interface CollisionsListener {

    void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact);
    void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact);

}
