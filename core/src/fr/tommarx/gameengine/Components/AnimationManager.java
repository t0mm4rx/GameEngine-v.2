package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Util.Animation;

public class AnimationManager extends Component {

    ArrayList<fr.tommarx.gameengine.Util.Animation> anims;
    fr.tommarx.gameengine.Util.Animation currentAnimation;
    boolean isRendering;
    float stateTime;

    public AnimationManager(AbstractGameObject go) {
        super(go);
        isRendering = false;
        anims = new ArrayList<fr.tommarx.gameengine.Util.Animation>();
        stateTime = 0f;
    }

    public void addAnimation(fr.tommarx.gameengine.Util.Animation anim) {
        anims.add(anim);
    }

    public void setCurrentAnimation(int id) {
        stateTime = 0f;
        isRendering = true;
        if (id == -1) {
            isRendering = false;
            return;
        }
        for (Animation a : anims) {
            if (a.getId() == id) {
                currentAnimation = a;
                return;
            }
        }
        currentAnimation = anims.get(0);
    }

    public void render() {
        if (isRendering) {
            if (getGameObject().getSpriteRenderer() != null) {
                getGameObject().getSpriteRenderer().setTexture((TextureRegion) currentAnimation.getAnimation().getKeyFrame(stateTime, currentAnimation.isLooping()));
            } else {
                System.err.println("Game object has no sprite renderer !");
            }
        }
    }

    public int getCurrentAnimation() {
        if (!isRendering) {
            return -1;
        }
        if (currentAnimation != null) {
            return currentAnimation.getId();
        }
        return -1;
    }

    public void renderInHUD() {
        render();
    }

    public void update() {
        if (currentAnimation != null) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
    }

    public void dispose() {

    }
}
