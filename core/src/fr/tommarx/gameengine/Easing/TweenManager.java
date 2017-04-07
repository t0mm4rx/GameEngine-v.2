package fr.tommarx.gameengine.Easing;

import java.util.ArrayList;

public class TweenManager {

    ArrayList<Tween> tweens;

    public TweenManager () {
        tweens = new ArrayList<>();
    }

    public void addTween(Tween tween) {
        tweens.add(tween);
    }

    public void update() {
        ArrayList<Tween> toDelete = new ArrayList<>();
        for (int i = 0; i < tweens.size(); i++) {
            Tween tween = tweens.get(i);
            if (tween.isFinished()) {
                toDelete.add(tween);
            } else {
                tween.update();
            }
        }
        tweens.removeAll(toDelete);
    }

}
