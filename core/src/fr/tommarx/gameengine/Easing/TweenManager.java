package fr.tommarx.gameengine.Easing;

import java.util.ArrayList;

public class TweenManager {

    ArrayList<Tween> tweens;

    public TweenManager () {
        tweens = new ArrayList<Tween>();
    }

    public void addTween(Tween tween) {
        tweens.add(tween);
    }

    public void update() {
        ArrayList<Tween> toDelete = new ArrayList<Tween>();
        for (Tween tween : tweens) {
            if (tween.isFinished()) {
                toDelete.add(tween);
            }
            tween.update();
        }
        tweens.removeAll(toDelete);
    }

}
