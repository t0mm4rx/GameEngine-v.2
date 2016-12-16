package fr.tommarx.gameengine.Collisions;

import fr.tommarx.gameengine.Game.AbstractGameObject;

public interface CollisionsListener {

    void collisionEnter(AbstractGameObject a, AbstractGameObject b);
    void collisionEnd(AbstractGameObject a, AbstractGameObject b);

}
