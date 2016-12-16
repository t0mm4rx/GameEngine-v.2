package fr.tommarx.gameengine.Collisions;

import fr.tommarx.gameengine.Game.GameObject;

public interface CollisionsListener {

    void collisionEnter(GameObject a, GameObject b);
    void collisionEnd(GameObject a, GameObject b);

}
