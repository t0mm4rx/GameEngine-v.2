package fr.tommarx.gameengine.Components;

import java.util.ArrayList;

import fr.tommarx.gameengine.Game.AbstractGameObject;
import fr.tommarx.gameengine.Util.LayoutSorter;
import fr.tommarx.gameengine.Util.Particle;

public class ParticleManager extends Component {

    private ArrayList<Particle> particles, toDelete;

    public ParticleManager(AbstractGameObject go) {
        super(go);
        particles = new ArrayList<Particle>();
        toDelete = new ArrayList<Particle>();
    }

    public void addParticle(Particle p) {
        particles.add(p);
    }

    public void render() {
        for (Particle p : LayoutSorter.sortParticlesByLayout(particles)) {
            p.render();
        }
    }

    public void renderInHUD() {

    }

    public void update() {
        for (Particle p : LayoutSorter.sortParticlesByLayout(particles)) {
            p.update();
            if (p.hasRequestedDelete()) {
                toDelete.add(p);
            }
        }
        if (toDelete.size() > 0) {
            for (int i = 0; i < toDelete.size(); i++) {
                if (i < particles.size()) {
                    particles.get(i).dispose();
                    particles.remove(i);
                }
            }
            for (int i = 0; i < toDelete.size(); i++) {
                toDelete.remove(i);
            }
        }
    }

    public void dispose() {
        for (Particle p : particles) {
            p.dispose();
        }
    }
}
