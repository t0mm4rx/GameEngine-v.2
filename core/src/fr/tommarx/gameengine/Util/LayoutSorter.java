package fr.tommarx.gameengine.Util;

import java.util.ArrayList;
import java.util.Collections;

import fr.tommarx.gameengine.Components.Component;
import fr.tommarx.gameengine.Game.Drawable;

public class LayoutSorter {

    public static ArrayList<Drawable> sortByLayout(ArrayList<Drawable> target) {
        ArrayList<Integer> zindexes = new ArrayList<>();
        ArrayList<Drawable> finalList = new ArrayList<>();

        for (Drawable d : target) {
            if (!zindexes.contains(d.getLayout())) {
                zindexes.add(d.getLayout());
            }
        }

        Collections.sort(zindexes);

        for (int z : zindexes) {
            for (Drawable d : target) {
                if (d.getLayout() == z) {
                    finalList.add(d);
                }
            }
        }

        zindexes.clear();
        return finalList;
    }

    public static ArrayList<Component> sortComponentsByLayout(ArrayList<Component> target) {
        ArrayList<Integer> zindexes = new ArrayList<>();
        ArrayList<Component> finalList = new ArrayList<>();

        for (Component d : target) {
            if (!zindexes.contains(d.getLayout())) {
                zindexes.add(d.getLayout());
            }
        }

        Collections.sort(zindexes);

        for (int z : zindexes) {
            for (Component d : target) {
                if (d.getLayout() == z) {
                    finalList.add(d);
                }
            }
        }

        zindexes.clear();
        return finalList;
    }

    public static ArrayList<Particle> sortParticlesByLayout(ArrayList<Particle> target) {
        ArrayList<Integer> zindexes = new ArrayList<>();
        ArrayList<Particle> finalList = new ArrayList<>();

        for (Particle d : target) {
            if (!zindexes.contains(d.getLayout())) {
                zindexes.add(d.getLayout());
            }
        }

        Collections.sort(zindexes);

        for (int z : zindexes) {
            for (Particle d : target) {
                if (d.getLayout() == z) {
                    finalList.add(d);
                }
            }
        }

        zindexes.clear();
        return finalList;
    }

}
