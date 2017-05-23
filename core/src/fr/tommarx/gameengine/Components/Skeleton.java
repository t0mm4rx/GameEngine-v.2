package fr.tommarx.gameengine.Components;

import java.util.ArrayList;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Util.Bone;

public class Skeleton extends Component {

    ArrayList<Bone> bones;

    public Skeleton(AbstractGameObject go) {
        super(go);
        bones = new ArrayList<Bone>();
    }

    public void addBone(Bone bone) {
        Game.getCurrentScreen().add(bone);
        bones.add(bone);
    }

    public void removeBone(Bone bone) {
        bone.dispose();
        bones.remove(bone);
    }

    public void render() {
        for (Bone b : bones) {
            b.render();
        }
    }

    public void renderInHUD() {}

    public void update() {
        for (Bone b : bones) {
            b.update();
        }
    }

    public void dispose() {
        for (Bone b : bones) {
            b.dispose();
        }
    }
}
